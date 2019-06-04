package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.BaseController;
import com.hanergy.reportForms.commons.CommonSearchParam;
import com.hanergy.reportForms.commons.utils.DBUtil;
import com.hanergy.reportForms.commons.utils.ReturnExcel;
import com.hanergy.reportForms.config.JDBCInfoConfig;
import com.hanergy.reportForms.service.detailcollect.DetailCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("detailcollect")
public class DetailCollectController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    DetailCollectService detailCollectService;

    @ApiOperation("超过4次(含)未请假未提报明细表")
    @PostMapping("/cgscDetail")
    public JSONObject cgscDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
             jsonObject=detailCollectService.cgscdetail(searchParam);
             jsonObject.put("code",200);
             jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("提报月平均条数小于5明细表")
    @PostMapping("/tsxywDetail")
    public JSONObject tsxywDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.tsxywDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }
    @ApiOperation("提报月平均字数小于5明细表")
    @PostMapping("/zsxywDetail")
    public JSONObject zsxywDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.zsxywDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("9点之前提报数据明细表")
    @PostMapping("/jdzqDetail")
    public JSONObject jdzqDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.jdzqDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("12点之前提报数据明细表")
    @PostMapping("/sedzqDetail")
    public JSONObject sedzqDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.sedzqDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("提报内容重复超6次(含)汇总表")
    @PostMapping("/cglhzDetail")
    public JSONObject cglhzDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.cglhzDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("提报内容重复超6次（含）明细表")
    @PostMapping("/cglmxDetail")
    public JSONObject cglmxDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.cglmxDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("部门问题明细表")
    @PostMapping("/issueDetail")
    public JSONObject issueDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.issueDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("部门反省明细表")
    @PostMapping("/introspectionDetail")
    public JSONObject introspectionDetail(@RequestBody CommonSearchParam searchParam) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.introspectionDetail(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("获取所有控股集团以及集团下的所有事业群，事业部，省公司，以及部门")
    @GetMapping("/allorg")
    public JSONObject getAllOrganization(@RequestParam(value = "konggujituan", defaultValue = "") String konggujituan,
                                         @RequestParam(value = "shiyequn", defaultValue = "") String shiyequn,
                                         @RequestParam(value = "shiyebu", defaultValue = "") String shiyebu,
                                         @RequestParam(value = "shenggongsi", defaultValue = "") String shenggongsi) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.getAllOrganization(konggujituan,shiyequn,shiyebu,shenggongsi);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("全员提报数据明细")
    @PostMapping("/allreport")
    public JSONObject allReport(@RequestBody CommonSearchParam searchParam){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=detailCollectService.allReport(searchParam);
            jsonObject.put("code",200);
            jsonObject.put("msg","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("code",500);
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }
    @PostMapping("/export")
    public void export(@RequestBody CommonSearchParam searchParam,HttpServletResponse response){
       detailCollectService.export(searchParam,response);
    }
}
