package com.message.dao;

import com.message.pojo.SampleToxin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SampleToxinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleToxin record);

    int insertSelective(SampleToxin record);

    SampleToxin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleToxin record);

    int updateByPrimaryKey(SampleToxin record);

    int insertBeachSampleToxin(@Param("toxins") List<SampleToxin> sampleToxins, @Param("id") Integer id);

    void upSampleToxinById(@Param("list") List<SampleToxin> list);

}