package com.message.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

public class CropSpecies {
    private Integer id;
    @ExcelProperty(index = 2,value = "农产品加工类型")
    private String cropSpecies;

    private Integer cropCategoryId;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCropSpecies() {
        return cropSpecies;
    }

    public void setCropSpecies(String cropSpecies) {
        this.cropSpecies = cropSpecies == null ? null : cropSpecies.trim();
    }

    public Integer getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(Integer cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}