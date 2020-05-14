package com.lintao.epidemic.controller;

import com.lintao.epidemic.bean.*;
import com.lintao.epidemic.bean.*;
import com.lintao.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/epidemicData")
public class EpidemicController {
    @Autowired
    private EpidemicService epidemicService;

    /**
     * 录入疫情数据
     * @param dailyEpidemicInfo
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/ajax/input")
    @ResponseBody
    public AjaxResponseInfo inputData(@RequestBody DailyEpidemicInfo dailyEpidemicInfo, Model model, HttpSession session){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        //从session中获取当前登录系统的用户信息
        UserInfo user = (UserInfo)session.getAttribute("loginUser");
        if(user==null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("你还没有登录");
        }else{
            //将数据保存
            List<ProvinceInfo> list = epidemicService.saveData(dailyEpidemicInfo,user.getUserId());
            responseInfo.setData(list);
        }
        return responseInfo;
    }

    /**
     * 查询最新累积疫情信息
     * @return
     */
    @GetMapping("/ajax/lastestData")
    @ResponseBody
    public AjaxResponseInfo findLastestData(){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        List<EpidemicDetailInfo> list = epidemicService.findLastestData();
        responseInfo.setData(list);
        return responseInfo;
    }

    /**
     * 查询今日疫情信息
     * @return
     */
    @GetMapping("/ajax/todayData")
    @ResponseBody
    public AjaxResponseInfo findTodayData(){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        List<EpidemicInfo> list = epidemicService.findTodayData();
        responseInfo.setData(list);
        return responseInfo;
    }

    /**
     * 查询昨日疫情信息
     * @return
     */
    @GetMapping("/ajax/yesterdayData")
    @ResponseBody
    public AjaxResponseInfo findYesterdayData(){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        List<EpidemicInfo> list = epidemicService.findYesterdayData();
        responseInfo.setData(list);
        return responseInfo;
    }

}
