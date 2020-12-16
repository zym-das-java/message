package com.message.dao;

import com.message.pojo.CropSpecies;

import java.util.List;

public interface CropSpeciesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CropSpecies record);

    int insertSelective(CropSpecies record);

    CropSpecies selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CropSpecies record);

    int updateByPrimaryKey(CropSpecies record);

    List<CropSpecies> selectAllCropSpecies();

    List<CropSpecies> selectByCropCategoryId(String id);
}