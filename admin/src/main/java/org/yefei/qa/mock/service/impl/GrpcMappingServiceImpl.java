package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.common.IMappingJobDao;
import org.yefei.qa.mock.dao.common.IMappingRulesDetailDao;
import org.yefei.qa.mock.dao.common.IMappingTaskDao;
import org.yefei.qa.mock.dao.grpc.IGrpcRequestMappingDao;
import org.yefei.qa.mock.dao.grpc.IGrpcRequestScriptDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMappingExample;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.network.mapping.GrpcMappingAgentSimple;
import org.yefei.qa.mock.service.IGrpcMappingService;
import org.yefei.qa.mock.websocket.WebSocketMappingPusher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:07
 */
@Service
public class GrpcMappingServiceImpl implements IGrpcMappingService {

    @Autowired
    private IGrpcRequestMappingDao grpcMappingDao;

    @Autowired
    private IGrpcRequestScriptDao grpcRequestScriptDao;

    @Autowired
    private IMappingJobDao mappingJobDao;

    @Autowired
    private IMappingTaskDao mappingTaskDao;

    @Autowired
    private IMappingRulesDetailDao mappingRulesDetailDao;

    @Autowired
    private BeanScanner beanScanner;

    @Autowired
    private WebSocketMappingPusher webSocketMappingPusher;

    private final ProtocolEnum protocolEnum = ProtocolEnum.GRPC;

    @Override
    public List<TblGrpcRequestMapping> queryGrpcMappingList(int groupID) {
        return grpcMappingDao.queryGrpcRequestMappingList(groupID);
    }

    @Override
    public long countGrpcMapping(int groupID) {
        return grpcMappingDao.countGrpcRequestMapping(groupID);
    }

    @Override
    public int saveGrpcMapping(TblGrpcRequestMapping grpcRequestMapping) {
        grpcRequestMapping.setUpdateTime(new Date());
        if (grpcRequestMapping.getRequestID() != null && grpcRequestMapping.getRequestID() > 0) {
            return updateGrpcRequestMapping(grpcRequestMapping);
        } else {
            if (grpcMappingDao.insertGrpcRequestMapping(grpcRequestMapping) == 1) {
                // 推送给agent
                webSocketMappingPusher.pushGrpcMapping();

                return grpcRequestMapping.getRequestID();
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean deleteGrpcMapping(int requestID) {
        if (grpcMappingDao.deleteGrpcRequestMaster(requestID) > 0) {
            grpcRequestScriptDao.deleteUnRelationMappingScript();
            mappingRulesDetailDao.deleteUnRelationGrpcMappingRulesDetail();
            mappingJobDao.deleteUnRelationGrpcMappingJob();
            mappingTaskDao.deleteUnRelationMappingTask();

            // 推送给agent
            webSocketMappingPusher.pushGrpcMapping();
        }

        return true;
    }

    @Override
    public TblGrpcRequestMapping getMapping(int requestID) {
        return grpcMappingDao.getMapping(requestID);
    }


    private int updateGrpcRequestMapping(TblGrpcRequestMapping grpcRequestMapping) {
        TblGrpcRequestMapping mapping = grpcMappingDao.getMapping(grpcRequestMapping.getRequestID());
        String orgMethodName = mapping.getMethodName();

        if (grpcMappingDao.updateGrpcRequestMapping(grpcRequestMapping) > 0) {
            if (!orgMethodName.equals(grpcRequestMapping.getMethodName())) {
                //查询记录中是否还存在 原methodName,如果存在，则复制一份script，如果不存在则修改script
                TblGrpcRequestMappingExample example = new TblGrpcRequestMappingExample();
                example.createCriteria().andGroupIDEqualTo(grpcRequestMapping.getGroupID()).andMethodNameEqualTo(orgMethodName);

                if (grpcMappingDao.countMapping(example) == 0) {
                    grpcRequestScriptDao.updateMethodName(grpcRequestMapping.getGroupID(), grpcRequestMapping.getMethodName(), orgMethodName);
                } else {
                    List<BeanScanner.BeanField> mappingScriptFields = beanScanner.getBeanFields("TblGrpcRequestScript", "scriptID", "path", "updateTime");
                    grpcRequestScriptDao.clone(mapping.getGroupID(), orgMethodName, grpcRequestMapping.getMethodName(), mappingScriptFields);
                }
            }

            // 推送给agent
            webSocketMappingPusher.pushGrpcMapping();

            return 1;
        }

        return 0;
    }

    @Override
    public boolean clone(int sourceRequestID) {
        List<BeanScanner.BeanField> grpcMappingFields = beanScanner.getBeanFields("TblGrpcRequestMapping", "requestID", "updateTime");
        List<BeanScanner.BeanField> mappingJobFields = beanScanner.getBeanFields("TblMappingJob", "requestID", "jobID", "updateTime");
        List<BeanScanner.BeanField> mappingTaskFields = beanScanner.getBeanFields("TblMappingTask", "jobID", "taskID", "updateTime");
        List<BeanScanner.BeanField> mappingRulesDetailFields = beanScanner.getBeanFields("TblMappingRulesDetail", "requestID", "rulesDetailID", "updateTime");

        int destRequestID = grpcMappingDao.cloneGrpcMapping(sourceRequestID, grpcMappingFields);

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

        mappingRulesDetailDao.cloneMappingRules(sourceRequestID, ProtocolEnum.HTTP.getProtocol(), destRequestID, mappingRulesDetailFields);

        return true;
    }

    @Override
    public List<GrpcMappingAgentSimple> queryAllActiveMapping() {
        List<TblGrpcRequestMapping> mappings = grpcMappingDao.queryAllActiveMethod();
        List<GrpcMappingAgentSimple> mappingList = new ArrayList<>();
        mappings.forEach(mapping -> {
            GrpcMappingAgentSimple item = new GrpcMappingAgentSimple();
            mappingList.add(item);
            item.setServiceName(mapping.getServiceName());
            item.setMethodName(mapping.getMethodName());
        });

        return mappingList;
    }
}
