package com.message.dao;

import com.message.pojo.SampleToxinInfo;

import java.util.List;

public interface SampleToxinInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleToxinInfo record);

    int insertSelective(SampleToxinInfo record);

    SampleToxinInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleToxinInfo record);

    int updateByPrimaryKey(SampleToxinInfo record);

    /**
     * 查询主要毒素
     * @return
     */
    List<SampleToxinInfo> selectToxinInfo();

}