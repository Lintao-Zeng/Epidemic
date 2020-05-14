package com.lintao.epidemic.bean;

/**
 * 省份实体类
 */
public class ProvinceInfo {
    private Integer provinceId;
    private String provinceName;//省份名称
    private String provincePinYing;//省份拼音

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvincePinYing() {
        return provincePinYing;
    }

    public void setProvincePinYing(String provincePinYing) {
        this.provincePinYing = provincePinYing;
    }
}
