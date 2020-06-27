package org.yefei.qa.mock.dao.grpc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yefei.qa.mock.dao.grpc.IGrpcGroupDao;
import org.yefei.qa.mock.model.gen.dao.TblGrpcRequestGroupMapper;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroup;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroupExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:03
 */
@Repository
public class GrpcGroupDaoImpl implements IGrpcGroupDao {

    @Autowired
    private TblGrpcRequestGroupMapper tblGrpcRequestGroupMapper;


    @Override
    public int insertGrpcRequestGroup(TblGrpcRequestGroup tblGrpcRequestGroup) {
        return tblGrpcRequestGroupMapper.insert(tblGrpcRequestGroup);
    }

    @Override
    public int deleteGrpcRequestGroup(int groupID) {
        TblGrpcRequestGroup record = new TblGrpcRequestGroup();
        record.setIsActive(false);

        TblGrpcRequestGroupExample example = new TblGrpcRequestGroupExample();
        TblGrpcRequestGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        tblGrpcRequestGroupMapper.updateByExampleSelective(record, example);
        return tblGrpcRequestGroupMapper.deleteByPrimaryKey(groupID);
    }

    @Override
    public int updateGrpcRequestGroup(TblGrpcRequestGroup tblGrpcRequestGroup) {
        return tblGrpcRequestGroupMapper.updateByPrimaryKeySelective(tblGrpcRequestGroup);
    }

    @Override
    public List<TblGrpcRequestGroup> queryGrpcRequestGroupList() {
        TblGrpcRequestGroupExample example = buildExample();
        return tblGrpcRequestGroupMapper.selectByExample(example);

    }

    @Override
    public long countGrpcRequestGroup() {
        TblGrpcRequestGroupExample example = buildExample();
        return tblGrpcRequestGroupMapper.countByExample(example);

    }

    private TblGrpcRequestGroupExample buildExample() {
        TblGrpcRequestGroupExample example = new TblGrpcRequestGroupExample();

        example.setOrderByClause("groupName asc, isActive desc, sortIndex asc, updateTime desc");
        return example;
    }

    @Override
    public List<TblGrpcRequestGroup> queryGrpcGroupByGroupName(String groupName) {
        TblGrpcRequestGroupExample example = new TblGrpcRequestGroupExample();
        TblGrpcRequestGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupNameEqualTo(groupName);

        return tblGrpcRequestGroupMapper.selectByExample(example);
    }

    @Override
    public long countGrpcGroupByGroupName(String groupName) {
        TblGrpcRequestGroupExample example = new TblGrpcRequestGroupExample();
        TblGrpcRequestGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupNameEqualTo(groupName);

        return tblGrpcRequestGroupMapper.countByExample(example);
    }


    @Override
    public TblGrpcRequestGroup getGroup(int groupID) {
        return tblGrpcRequestGroupMapper.selectByPrimaryKey(groupID);
    }

    @Override
    public List<TblGrpcRequestGroup> queryGrpcGroupByIDS(List<Integer> groupIDS) {
        TblGrpcRequestGroupExample example = new TblGrpcRequestGroupExample();
        TblGrpcRequestGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDIn(groupIDS);

        return tblGrpcRequestGroupMapper.selectByExample(example);
    }

    @Override
    public List<TblGrpcRequestGroup> queryGrpcGroupByID(int groupID) {
        TblGrpcRequestGroupExample example = new TblGrpcRequestGroupExample();
        TblGrpcRequestGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        return tblGrpcRequestGroupMapper.selectByExample(example);
    }
}
