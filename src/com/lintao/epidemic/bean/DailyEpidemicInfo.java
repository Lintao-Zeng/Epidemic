package com.lintao.epidemic.bean;

import java.util.List;

/**
 * 封装对应日期的省份数据
 */
public class DailyEpidemicInfo {
    private String date;//日期，以天为单位
    private List<EpidemicInfo> array;//对应多个省的疫情信息

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<EpidemicInfo> getArray() {
        return array;
    }

    public void setArray(List<EpidemicInfo> array) {
        this.array = array;
    }
}
