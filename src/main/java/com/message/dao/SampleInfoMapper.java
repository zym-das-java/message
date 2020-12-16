package com.message.dao;

import com.message.pojo.SampleInfo;
import com.message.vo.SampleInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SampleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleInfo record);

    int insertSelective(SampleInfo record);

    SampleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleInfo record);

    int updateByPrimaryKey(SampleInfo record);

    List<SampleInfo> findAll(@Param("statrNum") Integer statrNum,
                             @Param("pageSize")Integer pageSize,
                             @Param("search")String search,
                             @Param("pollutionRateX")Integer pollutionRatX,
                             @Param("pollutionRateD")Integer pollutionRatD,
                             @Param("toxinType")Integer toxinType,
                             @Param("breed")Integer breed,
                             @Param("province")Integer province,
                             @Param("city")Integer city,
                             @Param("county")Integer county,
                             @Param("harvestTime")String harvestTime
                             );
    Integer count();

    SampleInfoVO selectById(Integer id);

    SampleInfo findById(Integer id);

    void add(SampleInfo sampleInfo);

    SampleInfo selectSampleInfoById(Integer id);

    int upSampleToxinById(SampleInfo sampleInfo);
}