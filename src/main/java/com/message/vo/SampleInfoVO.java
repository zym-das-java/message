package com.message.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class SampleInfoVO extends BaseRowModel {

    //存在的是前端显示的数据
    /**
     * index头信息的索引
     */
    @ExcelProperty(index = 0,value = "id")
    private Integer id;
    @ExcelProperty(index = 1,value = "样品id")
    private String sampleId;
    @ExcelProperty(index = 2,value = "录入时间")
    private Date harvestTime;
    @ExcelProperty(index = 3,value = "取出时间")
    private Date samplingTime;
    @ExcelProperty(index = 4,value = "污染率")
    private Float pollutionRate;
    @ExcelProperty(index = 5,value = "省信息")
    private String pname;
    @ExcelProperty(index = 6,value = "市信息")
    private String cname;
    @ExcelProperty(index = 7,value = "县入时间")
    private String tname;
    @ExcelProperty(index = 8,value = "主要毒素")
    private String toxins;
    @ExcelProperty(index = 9,value = "农产品加工类型")
    private String crop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public Date getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(Date harvestTime) {
        this.harvestTime = harvestTime;
    }

    public Date getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    public Float getPollutionRate() {
        return pollutionRate;
    }

    public void setPollutionRate(Float pollutionRate) {
        this.pollutionRate = pollutionRate;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getToxins() {
        return toxins;
    }

    public void setToxins(String toxins) {
        this.toxins = toxins;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }
}
