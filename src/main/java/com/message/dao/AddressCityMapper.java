package com.message.dao;

import com.message.pojo.AddressCity;

import java.util.List;


public interface AddressCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressCity record);

    int insertSelective(AddressCity record);

    AddressCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressCity record);

    int updateByPrimaryKey(AddressCity record);

    List<AddressCity> selectCity(String provinceCode);


    AddressCity selectCityLikeCityName(String name);
}