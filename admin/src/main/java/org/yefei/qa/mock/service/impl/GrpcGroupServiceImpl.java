package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.bean.grpc.GrpcGroup;
import org.yefei.qa.mock.bean.grpc.GrpcRequestMapping;
import org.yefei.qa.mock.bean.grpc.GrpcRequestMethod;
import org.yefei.qa.mock.bean.rest.MappingJob;
import org.yefei.qa.mock.dao.common.IMappingJobDao;
import org.yefei.qa.mock.dao.common.IMappingRulesDetailDao;
import org.yefei.qa.mock.dao.common.IMappingTaskDao;
import org.yefei.qa.mock.dao.grpc.IGrpcGroupDao;
import org.yefei.qa.mock.dao.grpc.IGrpcRequestMappingDao;
import org.yefei.qa.mock.dao.grpc.IGrpcRequestScriptDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.pojo.*;
import org.yefei.qa.mock.service.IGrpcGroupService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:03
 */
@Service
public class GrpcGroupServiceImpl implements IGrpcGroupService {

    @Autowired
    private IGrpcGroupDao grpcGroupDao;

    @Autowired
    private IGrpcRequestMappingDao grpcRequestMappingDao;

    @Autowired
    private IMappingJobDao mappingJobDao;

    @Autowired
    private IMappingTaskDao mappingTaskDao;

    @Autowired
    private IMappingRulesDetailDao mappingRulesDetailDao;

    @Autowired
    private IGrpcRequestScriptDao grpcRequestScriptDao;

    private static final String protocol = ProtocolEnum.GRPC.getProtocol();

    private void importGrpcGroup(GrpcGroup grpcGroup) {
        TblGrpcRequestGroup tblGrpcRequestGroup = grpcGroup.getTblGrpcRequestGroup();
        List<TblGrpcRequestGroup> tblGrpcRequestGroups = grpcGroupDao.queryGrpcGroupByGroupName(tblGrpcRequestGroup.getGroupName());

        if (tblGrpcRequestGroups.size() > 0) {
            tblGrpcRequestGroup.setGroupID(tblGrpcRequestGroups.get(0).getGroupID());
        } else {
            tblGrpcRequestGroup.setUpdateTime(new Date());
            grpcGroupDao.insertGrpcRequestGroup(tblGrpcRequestGroup);
        }

        for (GrpcRequestMethod grpcRequestMethod : grpcGroup.getGrpcRequestMethodList()) {
            for (GrpcRequestMapping grpcRequestMapping : grpcRequestMethod.getGrpcRequestMappingList()) {
                //设置更新后的groupID
                grpcRequestMapping.getTblGrpcRequestMapping().setGroupID(tblGrpcRequestGroup.getGroupID());
                importGrpcMapping(grpcRequestMapping);
            }

            for (TblGrpcRequestScript tblGrpcRequestScript : grpcRequestMethod.getTblRestRequestScriptList()) {
                //设置更新后的groupID
                tblGrpcRequestScript.setGroupID(tblGrpcRequestGroup.getGroupID());
                imporGrpcRequestScript(tblGrpcRequestScript);
            }
        }

    }

    private void importGrpcMapping(GrpcRequestMapping grpcRequestMapping) {
        TblGrpcRequestMapping tblGrpcRequestMapping = grpcRequestMapping.getTblGrpcRequestMapping();
        tblGrpcRequestMapping.setUpdateTime(new Date());
        grpcRequestMappingDao.insertGrpcRequestMapping(tblGrpcRequestMapping);

        for (TblMappingRulesDetail tblMappingRulesDetail : grpcRequestMapping.getTblMappingRulesDetails()) {
            //设置更新后的RequestID
            tblMappingRulesDetail.setRequestID(tblGrpcRequestMapping.getRequestID());
            importMappingRulesDetail(tblMappingRulesDetail);
        }

        for (MappingJob mappingJob : grpcRequestMapping.getMappingJobList()) {
            //设置更新后的RequestID
            mappingJob.getTblMappingJob().setRequestID(tblGrpcRequestMapping.getRequestID());
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

    private void importMappingTask(TblMappingTask tblMappingTask) {
        tblMappingTask.setUpdateTime(new Date());
        mappingTaskDao.insertMappingTask(tblMappingTask);
    }

    private void imporGrpcRequestScript(TblGrpcRequestScript tblGrpcRequestScript) {
        tblGrpcRequestScript.setUpdateTime(new Date());
        grpcRequestScriptDao.insertGrpcScript(tblGrpcRequestScript);
    }


    private List<GrpcGroup> genGrpcGroupList(List<TblGrpcRequestGroup> tblGrpcRequestGroups) {
        List<GrpcGroup> grpcGroupList = new ArrayList<>();
        tblGrpcRequestGroups.forEach(tblGrpcRequestGroup -> {
            GrpcGroup grpcGroup = new GrpcGroup();
            grpcGroupList.add(grpcGroup);
            grpcGroup.setTblGrpcRequestGroup(tblGrpcRequestGroup);

            // 生成method
            genGrpcRequestMethod(grpcGroup);

            // 移除主键ID，避免导入时主键冲突
            tblGrpcRequestGroup.setGroupID(null);

        });
        return grpcGroupList;

    }


    private void genGrpcRequestMethod(GrpcGroup grpcGroup) {
        HashMap<String, GrpcRequestMethod> grpcRequestMethodHashMap = new HashMap<>();

        List<TblGrpcRequestMapping> tblGrpcRequestMappingList = grpcRequestMappingDao.queryGrpcRequestMappingList(grpcGroup.getTblGrpcRequestGroup().getGroupID());

        tblGrpcRequestMappingList.forEach(tblGrpcRequestMapping -> {

            String fullMethodName = tblGrpcRequestMapping.getServiceName() + "/" + tblGrpcRequestMapping.getMethodName();
            // 按照method聚合
            GrpcRequestMethod grpcRequestMethod;
            if (grpcRequestMethodHashMap.containsKey(fullMethodName)) {
                grpcRequestMethod = grpcRequestMethodHashMap.get(fullMethodName);
            } else {
                grpcRequestMethod = new GrpcRequestMethod();
                grpcRequestMethod.setFullMethod(fullMethodName);
                grpcRequestMethod.setServiceName(tblGrpcRequestMapping.getServiceName());
                grpcRequestMethod.setMethodName(tblGrpcRequestMapping.getMethodName());
                grpcRequestMethodHashMap.put(fullMethodName, grpcRequestMethod);
            }

            GrpcRequestMapping grpcRequestMapping = new GrpcRequestMapping();
            grpcRequestMethod.getGrpcRequestMappingList().add(grpcRequestMapping);
            grpcRequestMapping.setTblGrpcRequestMapping(tblGrpcRequestMapping);

            //设置匹配规则
            genMappingRulesDetails(grpcRequestMapping);

            //设置延时job
            genMappingJob(grpcRequestMapping);

            // 移除主键ID，避免导入时主键冲突
            tblGrpcRequestMapping.setRequestID(null);

        });

        // 生成执行脚本
        grpcRequestMethodHashMap.values().forEach(grpcRequestMethod -> {
            grpcGroup.getGrpcRequestMethodList().add(grpcRequestMethod);
            genMappingScript(grpcRequestMethod);
        });

    }


    /**
     * 生成执行脚本
     *
     * @param grpcRequestMethod
     */
    private void genMappingScript(GrpcRequestMethod grpcRequestMethod) {
        List<TblGrpcRequestScript> tblGrpcRequestScripts = grpcRequestScriptDao.queryScriptList(grpcRequestMethod.getServiceName(), grpcRequestMethod.getMethodName());
        grpcRequestMethod.setTblRestRequestScriptList(tblGrpcRequestScripts);

        tblGrpcRequestScripts.forEach(tblGrpcRequestScript -> {
            // 移除主键ID，避免导入时主键冲突
            tblGrpcRequestScript.setScriptID(null);
        });
    }


    /**
     * 设置延时任务
     *
     * @param grpcRequestMapping
     */
    private void genMappingJob(GrpcRequestMapping grpcRequestMapping) {

        Integer requestID = grpcRequestMapping.getTblGrpcRequestMapping().getRequestID();

        List<TblMappingJob> tblMappingJobList = mappingJobDao.queryTblMappingJobList(requestID, protocol);

        tblMappingJobList.forEach(tblMappingJob -> {
            MappingJob mappingJob = new MappingJob();
            grpcRequestMapping.getMappingJobList().add(mappingJob);
            mappingJob.setTblMappingJob(tblMappingJob);

            //设置延时task
            genMappingTask(mappingJob);

            // 移除主键ID，避免导入时主键冲突
            tblMappingJob.setJobID(null);

        });

    }


    /**
     * 设置具体的延时任务
     *
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
     *
     * @param grpcRequestMapping
     */
    private void genMappingRulesDetails(GrpcRequestMapping grpcRequestMapping) {
        List<TblMappingRulesDetail> tblMappingRulesDetails = mappingRulesDetailDao.queryMappingRulesDetail(grpcRequestMapping.getTblGrpcRequestMapping().getRequestID(), protocol);
        grpcRequestMapping.getTblMappingRulesDetails().addAll(tblMappingRulesDetails);
        tblMappingRulesDetails.forEach(tblMappingRulesDetail -> {

            // 移除主键ID，避免导入时主键冲突
            tblMappingRulesDetail.setRulesDetailID(null);
        });
    }


    @Override
    public void importGrpcGroupData(List<GrpcGroup> grpcGroupList) {
        grpcGroupList.forEach(grpcGroup -> {
            importGrpcGroup(grpcGroup);
        });
    }

    @Override
    public List<GrpcGroup> makeExportGrpcGroup(String groupIDs) {
        String[] split = groupIDs.split(",");
        List<Integer> groupIDList = new ArrayList<>();

        for (String s : split) {
            groupIDList.add(Integer.valueOf(s));
        }

        List<TblGrpcRequestGroup> tblGrpcRequestGroups = grpcGroupDao.queryGrpcGroupByIDS(groupIDList);

        return genGrpcGroupList(tblGrpcRequestGroups);
    }

    @Override
    public List<TblGrpcRequestGroup> queryGrpcGroupList() {
        return grpcGroupDao.queryGrpcRequestGroupList();
    }

    @Override
    public long countGrpcGroup() {
        return grpcGroupDao.countGrpcRequestGroup();
    }
//
//    @Override
//    public List<TblGrpcRequestGroup> queryGrpcGroupByCode(String groupCode) {
//        return null;
//    }
//
//    @Override
//    public long countGrpcGroupByCode(String groupCode) {
//        return 0;
//    }

    @Override
    public boolean saveGrpcGroup(TblGrpcRequestGroup grpcRequestGroup) throws IOException {
        if (grpcRequestGroup.getGroupID() == 0) {
            grpcGroupDao.insertGrpcRequestGroup(grpcRequestGroup);
        } else {
            grpcGroupDao.updateGrpcRequestGroup(grpcRequestGroup);
        }
        return true;
    }

    @Override
    public boolean deleteGrpcGroup(int groupID) {
        grpcGroupDao.deleteGrpcRequestGroup(groupID);
        return true;
    }

    @Override
    public TblGrpcRequestGroup getGroup(int groupID) {
        return grpcGroupDao.getGroup(groupID);
    }


    private boolean updateGrpcGroup(TblGrpcRequestGroup grpcRequestGroup) {
        boolean flag = false;
        if (grpcGroupDao.updateGrpcRequestGroup(grpcRequestGroup) > 0) {
//            grpcRequestMappingDao.updateGroupCode(grpcRequestGroup.getGroupID(), grpcRequestGroup.getGroupCode());
//            grpcRequestScriptDao.updateGroupCode(grpcRequestGroup.getGroupID(), grpcRequestGroup.getGroupCode());

            flag = true;
        }


        return flag;
    }

}
