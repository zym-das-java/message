package com.message.dao;

import com.message.pojo.AddressProvince;

import java.util.List;

public interface AddressProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressProvince record);

    int insertSelective(AddressProvince record);

    AddressProvince selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressProvince record);

    int updateByPrimaryKey(AddressProvince record);

    List<AddressProvince> selectProvince();
}