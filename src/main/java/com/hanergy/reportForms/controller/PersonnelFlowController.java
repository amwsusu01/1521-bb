package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.CommonSearchParam;
import com.hanergy.reportForms.mapper.entity.JobApplication;
import com.hanergy.reportForms.mapper.entity.UrlData;
import com.hanergy.reportForms.schedule.ScheduledService;
import com.hanergy.reportForms.service.personnelflow.PersonnelFlowService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/personnelflow")
public class PersonnelFlowController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    ScheduledService scheduledService;

    @Autowired
    PersonnelFlowService personnelFlowService;

    @GetMapping("/scheduled")
    public void scheduled(){
        scheduledService.scheduled();
    }

    @ApiOperation("列表查询参数")
    @PostMapping("/param")
    public JSONObject findQueryParam(@RequestBody JobApplication jobApplication){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=personnelFlowService.findQueryParam(jobApplication);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("人员流量明细")
    @PostMapping("/list")
    public JSONObject personnelFlowList(@RequestBody JobApplication jobApplication){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=personnelFlowService.list(jobApplication);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("人员流量明细导出")
    @PostMapping("/export")
    public void personnelFlowExport(@RequestBody JobApplication jobApplication, HttpServletResponse response){
        try {
            personnelFlowService.personnelFlowExport(jobApplication,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
