package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.mapper.entity.ProcessRecode;
import com.hanergy.reportForms.service.IprocessRecodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("用户操作记录")
@RequestMapping("/process")
@RestController
public class ProcessRecodeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IprocessRecodeService iprocessRecodeService;

    @ApiOperation("操作记录列表")
    @PostMapping("/recodeList")
    public JSONObject recodeList(@RequestBody ProcessRecode processRecode){
        JSONObject result;
        try {
            result= iprocessRecodeService.processRecodeList(processRecode);
            result.put("code", "200");
            result.put("msg", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            result=new JSONObject();
            result.put("code", "500");
            result.put("msg", "查询失败");
        }
        return result;
    }

    @ApiOperation("操作记录导出")
    @PostMapping("/recodeexport")
    public void recodeexportExport(@RequestBody ProcessRecode processRecode,HttpServletResponse response){
        try {
            iprocessRecodeService.processRecodeExport(response,processRecode);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
        }
    }
}
