package com.message.dao;

import com.message.pojo.CropCategory;

import java.util.List;

public interface CropCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropCategory record);

    int insertSelective(CropCategory record);

    CropCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropCategory record);

    int updateByPrimaryKey(CropCategory record);

    List<CropCategory> selectAll();
}