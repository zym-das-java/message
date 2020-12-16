package com.message.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

public class SampleToxinInfo {
    private Integer id;
    @ExcelProperty(index = 5,value = "主要毒素")
    private String toxinType;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToxinType() {
        return toxinType;
    }

    public void setToxinType(String toxinType) {
        this.toxinType = toxinType == null ? null : toxinType.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SampleToxinInfo{" +
                "id=" + id +
                ", toxinType='" + toxinType + '\'' +
                ", state=" + state +
                '}';
    }
}