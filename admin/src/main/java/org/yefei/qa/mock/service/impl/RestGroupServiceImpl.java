package org.yefei.qa.mock.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.bean.rest.MappingJob;
import org.yefei.qa.mock.bean.rest.RestGroup;
import org.yefei.qa.mock.bean.rest.RestRequestMapping;
import org.yefei.qa.mock.bean.rest.RestRequestPath;
import org.yefei.qa.mock.dao.common.IMappingJobDao;
import org.yefei.qa.mock.dao.common.IMappingRulesDetailDao;
import org.yefei.qa.mock.dao.common.IMappingTaskDao;
import org.yefei.qa.mock.dao.rest.IRestGroupDao;
import org.yefei.qa.mock.dao.rest.IRestRequestMappingDao;
import org.yefei.qa.mock.dao.rest.IRestRequestScriptDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.pojo.*;
import org.yefei.qa.mock.service.IRestGroupService;
import org.yefei.qa.mock.websocket.WebSocketMappingPusher;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:03
 */
@Service
public class RestGroupServiceImpl implements IRestGroupService {

    @Autowired
    private IRestGroupDao restGroupDao;

    @Autowired
    private IRestRequestMappingDao restRequestMappingDao;

    @Autowired
    private IMappingJobDao mappingJobDao;

    @Autowired
    private IMappingTaskDao mappingTaskDao;

    @Autowired
    private IMappingRulesDetailDao mappingRulesDetailDao;

    @Autowired
    private IRestRequestScriptDao restRequestScriptDao;

    @Autowired
    private WebSocketMappingPusher webSocketMappingPusher;

    private static final String protocol = ProtocolEnum.HTTP.getProtocol();

    @Override
    public void importRestGroupData( List<RestGroup> restGroupList) {
        for (RestGroup restGroup : restGroupList) {
            importRestGroup(restGroup);
        }
    }

    private void importRestGroup(RestGroup restGroup) {
        TblRestRequestGroup tblRestRequestGroup = restGroup.getTblRestRequestGroup();
        List<TblRestRequestGroup> tblRestRequestGroups = restGroupDao.queryRestGroupByCode(tblRestRequestGroup.getGroupCode());

        if (tblRestRequestGroups.size() > 0) {
            tblRestRequestGroup.setGroupID(tblRestRequestGroups.get(0).getGroupID());
        } else {
            tblRestRequestGroup.setUpdateTime(new Date());
            restGroupDao.insertRestRequestGroup(tblRestRequestGroup);
        }

        for (RestRequestPath restRequestPath : restGroup.getRestRequestPathList()) {
            for (RestRequestMapping restRequestMapping : restRequestPath.getRestRequestMappingList()) {
                //设置更新后的groupID
                restRequestMapping.getTblRestRequestMapping().setGroupID(tblRestRequestGroup.getGroupID());
                importRestMapping(restRequestMapping);
            }

            for (TblRestRequestScript tblRestRequestScript : restRequestPath.getTblRestRequestScriptList()) {
                //设置更新后的groupID
                tblRestRequestScript.setGroupID(tblRestRequestGroup.getGroupID());
                imporRestRequestScript(tblRestRequestScript);
            }
        }

    }

    private void importRestMapping(RestRequestMapping restRequestMapping) {
        TblRestRequestMapping tblRestRequestMapping = restRequestMapping.getTblRestRequestMapping();
        tblRestRequestMapping.setUpdateTime(new Date());
        restRequestMappingDao.insertRestRequestMapping(tblRestRequestMapping);

        for (TblMappingRulesDetail tblMappingRulesDetail : restRequestMapping.getTblMappingRulesDetails()) {
            //设置更新后的RequestID
            tblMappingRulesDetail.setRequestID(tblRestRequestMapping.getRequestID());
            importMappingRulesDetail(tblMappingRulesDetail);
        }

        for (MappingJob mappingJob : restRequestMapping.getMappingJobList()) {
            //设置更新后的RequestID
            mappingJob.getTblMappingJob().setRequestID(tblRestRequestMapping.getRequestID());
            importMappingJob(mappingJob);
        }

    }

    private void importMappingRulesDetail(TblMappingRulesDetail tblMappingRulesDetail) {
        tblMappingRulesDetail.setUpdateTime(new Date());
        mappingRulesDetailDao.insertMappingRulesDetail(tblMappingRulesDetail);
    }

    private void importMappingJob(MappingJob mappingJob) {
        TblMappingJob tblMappingJob = mappingJob.getTblMappingJob();
        tblMappingJob.setUpdateTime(new Date());
        mappingJobDao.insertMappingJob(tblMappingJob);

        for (TblMappingTask tblMappingTask : mappingJob.getTblMappingTaskList()) {
            //设置更新后的jobID
            tblMappingTask.setJobID(tblMappingJob.getJobID());
            importMappingTask(tblMappingTask);
        }
    }

    private void importMappingTask(TblMappingTask tblMappingTask){
        tblMappingTask.setUpdateTime(new Date());
        mappingTaskDao.insertMappingTask(tblMappingTask);
    }

    private void imporRestRequestScript(TblRestRequestScript tblRestRequestScript){
        tblRestRequestScript.setUpdateTime(new Date());
        restRequestScriptDao.insertRestScript(tblRestRequestScript);
    }

    @Override
    public List<RestGroup> makeExportRestGroup(String groupIDs){
        String[] split = groupIDs.split(",");
        List<Integer> groupIDList = new ArrayList<>();

        for (String s : split) {
            groupIDList.add(Integer.valueOf(s));
        }

        List<TblRestRequestGroup> tblRestRequestGroups = restGroupDao.queryRestGroupByIDS(groupIDList);

        return genRestGroupList(tblRestRequestGroups);
    }

    private List<RestGroup> genRestGroupList(List<TblRestRequestGroup> tblRestRequestGroups) {
        List<RestGroup> restGroupList = new ArrayList<>();
        tblRestRequestGroups.forEach(tblRestRequestGroup -> {
            RestGroup restGroup = new RestGroup();
            restGroupList.add(restGroup);
            restGroup.setTblRestRequestGroup(tblRestRequestGroup);

            // 生成path
            genRestRequestPath(restGroup);

            // 移除主键ID，避免导入时主键冲突
            tblRestRequestGroup.setGroupID(null);

        });
        return restGroupList;

    }



    private void genRestRequestPath(RestGroup restGroup) {
        HashMap<String, RestRequestPath> restRequestPathHashMap = new HashMap<>();

        List<TblRestRequestMapping> tblRestRequestMappingList = restRequestMappingDao.queryRestRequestMappingList(restGroup.getTblRestRequestGroup().getGroupID());

        tblRestRequestMappingList.forEach(tblRestRequestMapping -> {

            String path = tblRestRequestMapping.getPath();
            // 按照path聚合
            RestRequestPath restRequestPath;
            if (restRequestPathHashMap.containsKey(path)) {
                restRequestPath = restRequestPathHashMap.get(path);
            } else {
                restRequestPath = new RestRequestPath();
                restRequestPath.setPath(path);
                restRequestPathHashMap.put(path, restRequestPath);
            }

            RestRequestMapping restRequestMapping = new RestRequestMapping();
            restRequestPath.getRestRequestMappingList().add(restRequestMapping);
            restRequestMapping.setTblRestRequestMapping(tblRestRequestMapping);

            //设置匹配规则
            genMappingRulesDetails(restRequestMapping);

            //设置延时job
            genMappingJob(restRequestMapping);

            // 移除主键ID，避免导入时主键冲突
            tblRestRequestMapping.setRequestID(null);

        });

        // 生成执行脚本
        restRequestPathHashMap.values().forEach(restRequestPath -> {
            restGroup.getRestRequestPathList().add(restRequestPath);
            genMappingScript(restGroup.getTblRestRequestGroup().getGroupID(), restRequestPath);

        });

    }


    /**
     * 生成执行脚本
     * @param groupID
     * @param restRequestPath
     */
    private void genMappingScript(Integer groupID, RestRequestPath restRequestPath) {
        List<TblRestRequestScript> tblRestRequestScripts = restRequestScriptDao.queryScriptList(groupID, restRequestPath.getPath());
        restRequestPath.setTblRestRequestScriptList(tblRestRequestScripts);

        tblRestRequestScripts.forEach(tblRestRequestScript -> {
            // 移除主键ID，避免导入时主键冲突
            tblRestRequestScript.setScriptID(null);
        });
    }


    /**
     * 设置延时任务
     * @param restRequestMapping
     */
    private void genMappingJob(RestRequestMapping restRequestMapping) {

        Integer requestID = restRequestMapping.getTblRestRequestMapping().getRequestID();

        List<TblMappingJob> tblMappingJobList = mappingJobDao.queryTblMappingJobList(requestID, protocol);

        tblMappingJobList.forEach(tblMappingJob -> {
            MappingJob mappingJob = new MappingJob();
            restRequestMapping.getMappingJobList().add(mappingJob);
            mappingJob.setTblMappingJob(tblMappingJob);

            //设置延时task
            genMappingTask(mappingJob);

            // 移除主键ID，避免导入时主键冲突
            tblMappingJob.setJobID(null);

        });

    }


    /**
     * 设置具体的延时任务
     * @param mappingJob
     */
    private void genMappingTask(MappingJob mappingJob) {
        List<TblMappingTask> tblMappingTasks = mappingTaskDao.queryMappingTaskList(mappingJob.getTblMappingJob().getJobID());
        mappingJob.getTblMappingTaskList().addAll(tblMappingTasks);

        tblMappingTasks.forEach(tblMappingTask -> {

            // 移除主键ID，避免导入时主键冲突
            tblMappingTask.setTaskID(null);
        });
    }



    /**
     * 设置匹配规则
     * @param restRequestMapping
     */
    private void genMappingRulesDetails(RestRequestMapping restRequestMapping) {
        List<TblMappingRulesDetail> tblMappingRulesDetails = mappingRulesDetailDao.queryMappingRulesDetail(restRequestMapping.getTblRestRequestMapping().getRequestID(), protocol);
        restRequestMapping.getTblMappingRulesDetails().addAll(tblMappingRulesDetails);
        tblMappingRulesDetails.forEach(tblMappingRulesDetail -> {

            // 移除主键ID，避免导入时主键冲突
            tblMappingRulesDetail.setRulesDetailID(null);
        });
    }




    @Override
    public List<TblRestRequestGroup> queryRestGroupList(){
        return restGroupDao.queryRestRequestGroupList();
    }

    @Override
    public long countRestGroup(){
        return restGroupDao.countRestRequestGroup();
    }

    @Override
    public List<TblRestRequestGroup> queryRestGroupByCode(String groupCode){
        return restGroupDao.queryRestGroupByCode(groupCode);
    }

    @Override
    public long countRestGroupByCode(String groupCode){
        return restGroupDao.countRestGroupByCode(groupCode);
    }


    @Override
    public boolean saveRestGroup(TblRestRequestGroup restRequestGroup) {
        restRequestGroup.setUpdateTime(new Date());
        if (restRequestGroup.getGroupID() != null && restRequestGroup.getGroupID() > 0){
            return updateRestGroup(restRequestGroup);
        }else{
            return restGroupDao.insertRestRequestGroup(restRequestGroup) > 0 ? true : false;
        }
    }

    @Override
    public boolean deleteRestGroup(int groupID){
        if (restGroupDao.deleteRestRequestGroup(groupID) > 0){
            int effect = restRequestMappingDao.deleteUnRelationMapping();
            restRequestScriptDao.deleteUnRelationMappingScript();
            mappingRulesDetailDao.deleteUnRelationRestMappingRulesDetail();
            mappingJobDao.deleteUnRelationRestMappingJob();
            mappingTaskDao.deleteUnRelationMappingTask();

            // 有匹配被删除，需要通知agent
            if (effect > 0) {
                webSocketMappingPusher.pushRestMapping();
            }
        }
        return true;
    }


    @Override
    public TblRestRequestGroup getGroup(int groupID){
        return restGroupDao.getGroup(groupID);
    }



    private boolean updateRestGroup(TblRestRequestGroup restRequestGroup){
        boolean flag = false;
        if (restGroupDao.updateRestRequestGroup(restRequestGroup) > 0 ){
            int effect = restRequestMappingDao.updateGroupCode(restRequestGroup.getGroupID(), restRequestGroup.getGroupCode());
            restRequestScriptDao.updateGroupCode(restRequestGroup.getGroupID(), restRequestGroup.getGroupCode());

            // 有匹配被修改，需要通知agent
            if (effect > 0) {
                webSocketMappingPusher.pushRestMapping();
            }
            flag = true;
        }
        return flag;
    }

    private void dealDomain(TblRestRequestGroup restRequestGroup) {
        if (StringUtils.isNotEmpty(restRequestGroup.getSourceHost())){
            try {
                InetAddress[] ine = InetAddress.getAllByName(restRequestGroup.getSourceHost());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }
}
