package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.entity.log.SystemLog;
import com.hanergy.reportForms.mapper.entity.Problem;
import com.hanergy.reportForms.service.problem.IproblemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api("问题接口")
@RestController
@RequestMapping("/problem")
public class ProblemController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IproblemService iproblemService;

    @ApiOperation("问题清单列表")
    @PostMapping("/problemlist")
    public JSONObject problemlist(@RequestBody Problem problem) {
        JSONObject result = new JSONObject();
        try {
            result = iproblemService.problemlist(problem);
            result.put("code", "200");
            result.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            result.put("code", "500");
            result.put("msg", "查询失败");
        }
        return result;
    }


    @ApiOperation("问题清单导出")
    @PostMapping("/problemexcelexport")
    public void problemExcelExport(@RequestBody Problem problem, HttpServletResponse response) {
        try {
            iproblemService.problemDetailedExcel(response, problem);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
        }
    }
}
