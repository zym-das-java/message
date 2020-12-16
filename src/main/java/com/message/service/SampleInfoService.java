package com.message.service;


import com.message.common.PageUtil;
import com.message.common.ResultUtil;
import com.message.pojo.*;
import com.message.vo.SampleInfoVO;


import java.io.OutputStream;
import java.util.List;

public interface SampleInfoService {
    /**
     * 查询所有信息
     * @return
     */
    PageUtil findAllSampleInfo(Integer pageNum, Integer pageSize,
                               String search,String pollutionRate,
                               Integer toxinType, Integer breed,
                               Integer province, Integer city,
                               Integer county,String year,
                               String month, String day);

    List<SampleToxinInfo> findAllSampleToxinInfo();

    List<AddressProvince> findAllAddressProvince();

    List<AddressCity> findAllAddressCity(String provinceCode);

    List<AddressTown> findAllAddressTown(String cityCode);

    List<CropSpecies> findAllCropSpecies();

    List<CropCategory> findAllCropCategory();

    List<CropSpecies> selectByCropCategoryId(String id);

    void exportInfo(String[] s, OutputStream outputStream);

    List<SampleInfoVO> exportInfo1(String[] s);

    /**
     * 数据导入数据库
     * @param sampleInfo
     * @return
     */
    ResultUtil upload(List<List<String>> reader);

    ResultUtil add(SampleInfo sampleInfo);

    ResultUtil set(SampleInfo sampleInfo);

    SampleInfo findById(Integer id);

    ResultUtil delect(Integer id);

    AddressCity getCityByCityName(String name);

    AddressTown getTownByCountyName(String name);

    boolean beachSaveSampleInfo(List<SampleInfo> sampleInfos);


    SampleInfo findSampleinfoInId(Integer id);
}
