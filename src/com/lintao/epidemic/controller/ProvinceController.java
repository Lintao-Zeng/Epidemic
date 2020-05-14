package com.lintao.epidemic.controller;

import com.lintao.epidemic.bean.AjaxResponseInfo;
import com.lintao.epidemic.bean.ProvinceInfo;
import com.lintao.epidemic.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/ajax/noDataList")
    @ResponseBody
    public AjaxResponseInfo noDataProvinceList(String date){
        List<ProvinceInfo> list = null;
        AjaxResponseInfo<List<ProvinceInfo>> responseInfo = new AjaxResponseInfo<>();
        if(!StringUtils.isEmpty(date)){
            list = provinceService.findNoDataProvinces(date);
            responseInfo.setData(list);
        }else{
            responseInfo.setCode(-1);
            responseInfo.setMsg("参数不足");
        }
        return responseInfo;
    }
}
