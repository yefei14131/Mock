package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.common.IMappingJobDao;
import org.yefei.qa.mock.dao.common.IMappingRulesDetailDao;
import org.yefei.qa.mock.dao.common.IMappingTaskDao;
import org.yefei.qa.mock.dao.rest.IRestGroupDao;
import org.yefei.qa.mock.dao.rest.IRestRequestMappingDao;
import org.yefei.qa.mock.dao.rest.IRestRequestScriptDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroup;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMappingExample;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;
import org.yefei.qa.mock.service.IRestMappingService;
import org.yefei.qa.mock.utils.AgentMappingTransfer;
import org.yefei.qa.mock.websocket.WebSocketMappingPusher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:07
 */
@Service
public class RestMappingServiceImpl implements IRestMappingService {

    @Autowired
    private IRestGroupDao restGroupDao;

    @Autowired
    private IRestRequestMappingDao restMappingDao;

    @Autowired
    private IRestRequestScriptDao restRequestScriptDao;

    @Autowired
    private IMappingJobDao mappingJobDao;

    @Autowired
    private IMappingTaskDao mappingTaskDao;

    @Autowired
    private IMappingRulesDetailDao mappingRulesDetailDao;

    @Autowired
    private BeanScanner beanScanner;

    private final ProtocolEnum protocolEnum = ProtocolEnum.HTTP;

    @Autowired
    private WebSocketMappingPusher webSocketMappingPusher;

    @Override
    public List<TblRestRequestMapping> queryRestMappingList(int groupID){
        return restMappingDao.queryRestRequestMappingList(groupID);
    }

    @Override
    public long countRestMapping(int groupID){
        return restMappingDao.countRestRequestMapping(groupID);
    }

    @Override
    public int saveRestMapping(TblRestRequestMapping restRequestMapping) {
        restRequestMapping.setUpdateTime(new Date());
        if (restRequestMapping.getRequestID() != null && restRequestMapping.getRequestID() > 0){
            return updateRestRequestMapping(restRequestMapping);
        }else{
            if ( restMappingDao.insertRestRequestMapping(restRequestMapping) == 1){
//                webSocketPusher.pushPatchRestMapping(Lists.newArrayList(restRequestMapping), null);
                webSocketMappingPusher.pushRestMapping();
                return restRequestMapping.getRequestID();
            }else{
                return 0;
            }
        }
    }

    @Override
    public boolean deleteRestMapping(int requestID){
        TblRestRequestMapping mapping = restMappingDao.getMapping(requestID);
        if (restMappingDao.deleteRestRequestMaster(requestID) > 0 ){
            restRequestScriptDao.deleteUnRelationMappingScript();
            mappingRulesDetailDao.deleteUnRelationRestMappingRulesDetail();
            mappingJobDao.deleteUnRelationRestMappingJob();
            mappingTaskDao.deleteUnRelationMappingTask();
        }

//        webSocketPusher.pushPatchRestMapping(null, Lists.newArrayList(mapping));
        webSocketMappingPusher.pushRestMapping();
        return true;
    }

    @Override
    public TblRestRequestMapping getMapping(int requestID){
        return restMappingDao.getMapping(requestID);
    }


    private int updateRestRequestMapping(TblRestRequestMapping restRequestMapping){
        TblRestRequestMapping mapping = restMappingDao.getMapping(restRequestMapping.getRequestID());
        String orgPath = mapping.getPath();

        if (restMappingDao.updateRestRequestMapping(restRequestMapping) > 0){
            if ( !orgPath.equals(restRequestMapping.getPath())){
                //查询记录中是否还存在 原path,如果存在，则复制一份script，如果不存在则修改script
                TblRestRequestMappingExample example = new TblRestRequestMappingExample();
                example.createCriteria().andGroupIDEqualTo(restRequestMapping.getGroupID()).andPathEqualTo(orgPath);

                if ( restMappingDao.countMapping(example) == 0) {
                    restRequestScriptDao.updatePath(restRequestMapping.getGroupID(), restRequestMapping.getPath(), orgPath);
                } else {
                    List<BeanScanner.BeanField> mappingScriptFields = beanScanner.getBeanFields("TblRestRequestScript", "scriptID", "path", "updateTime");
                    restRequestScriptDao.clone(mapping.getGroupID(), orgPath, restRequestMapping.getPath(), mappingScriptFields);
                }
            }

//            webSocketPusher.pushPatchRestMapping(Lists.newArrayList(restRequestMapping), Lists.newArrayList(mapping));
            webSocketMappingPusher.pushRestMapping();

            return 1;
        }

        return 0;
    }

    @Override
    public boolean clone(int sourceRequestID) {
        List<BeanScanner.BeanField> restMappingFields = beanScanner.getBeanFields("TblRestRequestMapping", "requestID", "updateTime");
        List<BeanScanner.BeanField> mappingJobFields = beanScanner.getBeanFields("TblMappingJob", "requestID", "jobID", "updateTime");
        List<BeanScanner.BeanField> mappingTaskFields = beanScanner.getBeanFields("TblMappingTask", "jobID", "taskID", "updateTime");
        List<BeanScanner.BeanField> mappingRulesDetailFields = beanScanner.getBeanFields("TblMappingRulesDetail", "requestID", "rulesDetailID", "updateTime");

        int destRequestID = restMappingDao.cloneRestMapping(sourceRequestID, restMappingFields);

        if (destRequestID == 0) {
            return false;
        }

        List<TblMappingJob> tblMappingJobList = mappingJobDao.queryTblMappingJobList(sourceRequestID, protocolEnum.getProtocol());

        tblMappingJobList.forEach(tblMappingJob -> {
            int destJobID = mappingJobDao.cloneJob(tblMappingJob.getJobID(), destRequestID, mappingJobFields);
            if (destJobID > 0) {
                mappingTaskDao.cloneTask(tblMappingJob.getJobID(), destJobID, mappingTaskFields);
            }
        });


        mappingRulesDetailDao.cloneMappingRules(sourceRequestID, protocolEnum.getProtocol(), destRequestID, mappingRulesDetailFields);

        return true;
    }

    @Override
    public List<RestMappingAgentSimple> queryAllActiveMapping() {
        List<TblRestRequestMapping> requestMappings = restMappingDao.queryAllActiveMapping();
        if (requestMappings.isEmpty()) {
            return new ArrayList<>();
        }

        List<TblRestRequestGroup> tblRestRequestGroups = restGroupDao.queryActiveGroupHost();
        HashMap<String, String> groupHostMap = new HashMap<>();
        tblRestRequestGroups.forEach(restGroup -> {
            groupHostMap.put(restGroup.getGroupCode(), restGroup.getSourceHost());
        });

        List<RestMappingAgentSimple> mappingList = new ArrayList<>();
        requestMappings.forEach(mapping -> {
            if (groupHostMap.containsKey(mapping.getGroupCode())) {
                RestMappingAgentSimple item = new RestMappingAgentSimple();
                mappingList.add(item);
//                item.setHost(groupHostMap.get(mapping.getGroupCode()));
//                item.setUri(mapping.getPath());
//                item.setUriPrefix("/" + mapping.getGroupCode());
                AgentMappingTransfer.restMapping2AgentMapping(mapping, item, groupHostMap.get(mapping.getGroupCode()));
            }
        });

        return mappingList;
    }
}
