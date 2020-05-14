package com.lintao.epidemic.service;

import com.lintao.epidemic.bean.DailyEpidemicInfo;
import com.lintao.epidemic.bean.EpidemicDetailInfo;
import com.lintao.epidemic.bean.EpidemicInfo;
import com.lintao.epidemic.bean.ProvinceInfo;

import java.util.List;

public interface EpidemicService {
    /**
     * 保存当日的疫情数据，返回还未录入数据的省份列表
     * @param dailyEpidemicInfo
     * @param userId
     * @return
     */
    List<ProvinceInfo> saveData(DailyEpidemicInfo dailyEpidemicInfo, Integer userId);

    /**
     * 获取最新累积疫情数据
     * @return
     */
    List<EpidemicDetailInfo> findLastestData();

    /**
     * 获取今日疫情数据
     * @return
     */
    List<EpidemicInfo> findTodayData();

    /**
     * 获取昨日疫情数据
     * @return
     */
    List<EpidemicInfo> findYesterdayData();
}