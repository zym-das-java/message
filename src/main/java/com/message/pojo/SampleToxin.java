package com.message.pojo;

public class SampleToxin {
    private Integer id;

    private Integer toxinId;

    private Float toxinCount;

    private Integer sampleInfoId;

    private SampleToxinInfo sampleToxinInfo;

    public SampleToxinInfo getSampleToxinInfo() {
        return sampleToxinInfo;
    }

    public void setSampleToxinInfo(SampleToxinInfo sampleToxinInfo) {
        this.sampleToxinInfo = sampleToxinInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToxinId() {
        return toxinId;
    }

    public void setToxinId(Integer toxinId) {
        this.toxinId = toxinId;
    }

    public Float getToxinCount() {
        return toxinCount;
    }

    public void setToxinCount(Float toxinCount) {
        this.toxinCount = toxinCount;
    }

    public Integer getSampleInfoId() {
        return sampleInfoId;
    }

    public void setSampleInfoId(Integer sampleInfoId) {
        this.sampleInfoId = sampleInfoId;
    }

    public SampleToxin() {
    }

    @Override
    public String toString() {
        return "SampleToxin{" +
                "id=" + id +
                ", toxinId=" + toxinId +
                ", toxinCount=" + toxinCount +
                ", sampleInfoId=" + sampleInfoId +
                ", sampleToxinInfo=" + sampleToxinInfo +
                '}';
    }
}