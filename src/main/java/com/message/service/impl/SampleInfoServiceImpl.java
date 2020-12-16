package com.message.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.message.common.PageUtil;
import com.message.common.ResultUtil;
import com.message.dao.*;
import com.message.pojo.*;
import com.message.service.SampleInfoService;
import com.message.vo.SampleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SampleInfoServiceImpl implements SampleInfoService {

    @Autowired
    private SampleInfoMapper sampleInfoMapper;


    @Autowired
    private SampleToxinInfoMapper sampleToxinInfoMapper;

    @Autowired
    private AddressProvinceMapper addressProvinceMapper;

    @Autowired
    private AddressCityMapper addressCityMapper;

    @Autowired
    private AddressTownMapper addressTownMapper;

    @Autowired
    private CropSpeciesMapper cropSpeciesMapper;

    @Autowired
    private  SampleToxinMapper sampleToxinMapper;

    @Autowired
    private CropCategoryMapper cropCategoryMapper;

    @Override
    public PageUtil findAllSampleInfo(Integer pageNum, Integer pageSize,
                                      String search,String pollutionRate,
                                      Integer toxinType, Integer breed,
                                      Integer province, Integer city,
                                      Integer county,String year,
                                      String month, String day) {
        PageUtil pageUtil = new PageUtil();

        Integer pollutionRatX = null;
        Integer pollutionRatD = null;
        if (pollutionRate!=null && !"".equals(pollutionRate)){
            String[] s = pollutionRate.split("-");
             pollutionRatX = new Integer(s[0]);
             pollutionRatD = new Integer(s[1]);
        }
        String harvestTime = null;
        if (day!=null && !"".equals(day)) {
            if (new Integer(day)>=10&&new Integer(month)>=10){
                harvestTime=year+"-"+month+"-"+day;
            }else if (new Integer(day)>=10&&new Integer(month)<10){
                harvestTime=year+"-"+0+month+"-"+day;
            }else if (new Integer(day)<10&&new Integer(month)>=10){
                harvestTime=year+"-"+month+"-"+0+day;
            }else if(new Integer(day)<10&&new Integer(month)<10){
                harvestTime=year+"-"+month+"-"+day;
            }
        }else{
            if (month!=null&&month!=""){

                if (new Integer(month)>=10){
                    harvestTime=year+"-"+month;
                }else {
                    harvestTime=year+"-"+0+month;
                }

            }else {
                if (year!=null&&year!=""){
                    harvestTime=year;
                }
            }
        }
        pageUtil.setPageNum(pageNum);
        pageUtil.setPageSize(pageSize);
        pageUtil.setTotal(sampleInfoMapper.count());
        List<SampleInfo> all = sampleInfoMapper.findAll(pageUtil.getStatrNum(),
                pageSize,search,pollutionRatX,pollutionRatD,toxinType,breed,
                province,city,county,harvestTime
        );
        pageUtil.setResult(all);

        return pageUtil;
    }

    @Override
    public List<SampleToxinInfo> findAllSampleToxinInfo() {
        return sampleToxinInfoMapper.selectToxinInfo();
    }

    @Override
    public List<AddressProvince> findAllAddressProvince() {
        return addressProvinceMapper.selectProvince();
    }

    @Override
    public List<AddressCity> findAllAddressCity(String provinceCode) {
        return addressCityMapper.selectCity(provinceCode);
    }

    @Override
    public List<AddressTown> findAllAddressTown(String code) {
        return addressTownMapper.selectTown(code);
    }

    @Override
    public List<CropSpecies> findAllCropSpecies() {
        return cropSpeciesMapper.selectAllCropSpecies();
    }

    @Override
    public List<CropSpecies> selectByCropCategoryId(String id) {
        return cropSpeciesMapper.selectByCropCategoryId(id);
    }

    @Override
    public List<CropCategory> findAllCropCategory() {
        return cropCategoryMapper.selectAll();
    }

    @Override
    public void exportInfo(String[] s,OutputStream outputStream){
        List<SampleInfoVO> sampleInfos = new ArrayList<>();
        for (int i = 0; i <s.length ; i++) {
            sampleInfos.add(sampleInfoMapper.selectById(Integer.valueOf(s[i])));
        }
        try {
            Sheet sheet = new Sheet(0,1,SampleInfoVO.class);
           // OutputStream outputStream = new FileOutputStream("D://导出信息.xls");
            ExcelWriter writer  = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLS, true);
            writer.write(sampleInfos,sheet);
            writer.finish();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<SampleInfoVO> exportInfo1(String[] s) {
        List<SampleInfoVO> sampleInfos = new ArrayList<>();
        for (int i = 0; i <s.length ; i++) {
            sampleInfos.add(sampleInfoMapper.selectById(Integer.valueOf(s[i])));
        }
        return  sampleInfos;
    }

    @Transactional
    @Override
    public ResultUtil upload(List<List<String>>  reader) {
        ResultUtil resultUtil = new ResultUtil();
        List<CropSpecies> cropSpecies = cropSpeciesMapper.selectAllCropSpecies();
        List<AddressProvince> addressProvinces = addressProvinceMapper.selectProvince();
        List<SampleToxinInfo> sampleToxinInfos = sampleToxinInfoMapper.selectToxinInfo();
        //获取表头信息
        List<String> toxinHenders = reader.get(1);
        try {
            for (int i = 2; i <reader.size() ; i++) {
                SampleInfo sampleInfo = new SampleInfo();
                List<String> strings = reader.get(i);
                sampleInfo.setSampleId(strings.get(0));
                //品种id
                String s = strings.get(1);
                if (!"".equals(s)){
                    for (CropSpecies c : cropSpecies) {
                        if (c.getCropSpecies().equals(s)){
                            sampleInfo.setBreed(c.getId());
                            break;
                        }
                    }
                }
                if ("是".equals(strings.get(2))){
                    sampleInfo.setFlag(1);
                }else {
                    sampleInfo.setFlag(2);
                }
                //农产品加工类型
                String s1 = strings.get(3);
                if (!"".equals(s1)){
                    Integer integer = (int) Double.parseDouble(s1);
                    sampleInfo.setCropCategoryId(integer);
                }
                //省信息
                if (!"".equals(strings.get(4))){
                    for (AddressProvince p: addressProvinces) {
                        if (strings.get(4).equals(p.getName())){
                            sampleInfo.setProvince(p.getCode());
                        }
                    }
                }
                //市信息
                if (!"".equals(strings.get(5))){
                    AddressCity addressCity = addressCityMapper.selectCityLikeCityName(strings.get(5));
                    sampleInfo.setCity(addressCity.getCode());
                }

                String county = "";
                String town = strings.get(6);
                if (!"".equals(town)){
                    if (town.length()==2){
                        if (town.endsWith("县")||town.endsWith("区")){

                            county = town.substring(0,1) + "　" +town.substring(1);
                        }else {
                            county = town;
                        }

                    }else {
                        county = town;
                    }
                    AddressTown addressTown = addressTownMapper.selectTownByTownName(county);
                    sampleInfo.setCounty(addressTown.getCode());
                }
                sampleInfo.setTownship(strings.get(7));
                sampleInfo.setVillage(strings.get(8));
                sampleInfo.setHousehold(strings.get(9));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD");
                //取样时间
                String harvestTime = strings.get(10);
                if (!"".equals(harvestTime)){
                    sampleInfo.setHarvestTime(simpleDateFormat.parse(harvestTime));
                }
                //收获时间
                String samplingTime = strings.get(11);
                if (!"".equals(samplingTime)){
                    sampleInfo.setSamplingTime(simpleDateFormat.parse(samplingTime));
                }
                sampleInfo.setSamplingPeople(strings.get(12));
                sampleInfo.setSeasonal(strings.get(13));
                sampleInfo.setDescription(strings.get(14));
                //真菌污染率
                if (!"".equals(strings.get(15)))
                sampleInfo.setPollutionRate(Float.valueOf(strings.get(15)));
                //默认添加数据
                sampleInfo.setIsdel(0);
                sampleInfo.setCreateTime(new Date());
                int index = 17;
                int end = 29;
                if (strings.size() <29){
                    end = strings.size();
                }
                //创建关系表
                List<SampleToxin> sampleToxins = new ArrayList<>();
                for (int j = index; j < end ; j++) {
                    //获取毒素含量
                    String toxinCount = strings.get(j);
                    if (toxinCount!=null&&!toxinCount.equals("")){
                        //创建毒素对象
                        SampleToxin sampleToxin = new SampleToxin();
                            sampleToxin.setToxinCount(Float.valueOf(toxinCount));
                            //根据表头信息获取该毒素的名称
                            String toxinName = toxinHenders.get(j);
                            //循环所有的毒素，根据毒素名称取出毒素的id信息，存储到中间表中
                            for (SampleToxinInfo toxinInfo:sampleToxinInfos) {
                                if (toxinName.equals(toxinInfo.getToxinType())){
                                    sampleToxin.setToxinId(toxinInfo.getId());
                                    break;
                                }
                            }
                            sampleToxins.add(sampleToxin);

                    }

                }
                sampleInfo.setSampleToxinList(sampleToxins);
                sampleInfoMapper.insert(sampleInfo);
                Integer id = sampleInfo.getId();
                sampleToxinMapper.insertBeachSampleToxin(sampleInfo.getSampleToxinList(),id);
            }

            resultUtil.setSuccess(true);
            resultUtil.setMessage("导入成功");
        }catch (Exception e){
            resultUtil.setSuccess(false);
            resultUtil.setMessage("导入失败");
            e.printStackTrace();
        }
        return resultUtil;
    }

    /**
     * 添加
     * @param sampleInfo
     * @return
     */
    @Transactional
    @Override
    public ResultUtil add(SampleInfo sampleInfo) {
        ResultUtil resultUtil = new ResultUtil();
        try {
            //默认添加数据
            sampleInfo.setIsdel(0);
            sampleInfo.setCreateTime(new Date());
            sampleInfo.setFlag(1);
            sampleInfoMapper.insert(sampleInfo);
            Integer id = sampleInfo.getId();
            sampleToxinMapper.insertBeachSampleToxin(sampleInfo.getSampleToxinList(),id);
            resultUtil.setMessage("添加成功");
            resultUtil.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            resultUtil.setMessage("添加失败");
            resultUtil.setSuccess(false);
        }
        return resultUtil;
    }

    /**
     * 查询要修改的Id
     * @param
     * @return
     */
    @Override
    public SampleInfo findById(Integer id) {
            SampleInfo sampleInfo = sampleInfoMapper.findById(id);
        return sampleInfo;
    }
    /**
     * 修改
     * @param sampleInfo
     * @return
     */
    @Override
    @Transactional
    public ResultUtil set(SampleInfo sampleInfo) {
        ResultUtil resultUtil = new ResultUtil();
        try {
            sampleInfoMapper.upSampleToxinById(sampleInfo);
            sampleToxinMapper.upSampleToxinById(sampleInfo.getSampleToxinList());
            resultUtil.setMessage("修改成功");
            resultUtil.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            resultUtil.setMessage("修改失败");
            resultUtil.setSuccess(false);
        }

        return resultUtil;
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @Override
    public ResultUtil delect(Integer id) {
        ResultUtil resultUtil = new ResultUtil();
        try {
            sampleInfoMapper.deleteByPrimaryKey(id);
            resultUtil.setMessage("删除成功");
            resultUtil.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            resultUtil.setMessage("删除失败");
            resultUtil.setSuccess(false);
        }

        return resultUtil;
    }


    @Override
    public AddressCity getCityByCityName(String name) {

        return addressCityMapper.selectCityLikeCityName(name);
    }

    @Override
    public AddressTown getTownByCountyName(String name) {
        return addressTownMapper.selectTownByTownName(name);
    }

    @Override
    @Transactional
    public boolean beachSaveSampleInfo(List<SampleInfo> sampleInfos) {

        boolean b = false;
        try {
            for (int i = 0; i <sampleInfos.size() ; i++) {
                SampleInfo sampleInfo = sampleInfos.get(i);
                sampleInfoMapper.insert(sampleInfo);
                Integer id = sampleInfo.getId();
                sampleToxinMapper.insertBeachSampleToxin(sampleInfo.getSampleToxinList(),id);
            }
            b = true;
        }catch (Exception e){

            e.fillInStackTrace();
        }

        return b;
    }

    @Override
    public SampleInfo findSampleinfoInId(Integer id) {
        return sampleInfoMapper.selectSampleInfoById(id);
    }
}
