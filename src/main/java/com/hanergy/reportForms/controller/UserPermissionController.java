package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.BaseController;
import com.hanergy.reportForms.commons.utils.DBUtil;
import com.hanergy.reportForms.config.JDBCInfoConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Api("用户数据权限接口")
@Controller
@RequestMapping(value = "/userPermission")
public class UserPermissionController extends BaseController {

    private JDBCInfoConfig jdbcConfig;
    @Autowired
    private JDBCInfoConfig configAutowired;

    @PostConstruct
    public void init() {
        jdbcConfig = this.configAutowired;
    }


    @ResponseBody
    @ApiOperation(value = "查询用户的数据权限")
    @RequestMapping(value = "/selectPermission", method = RequestMethod.GET)
    public JSONObject selectPermission(
            @RequestParam(value = "userCode", defaultValue = "") String userCode,
            @RequestParam(value = "deptid", defaultValue = "") String deptid,
            HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        if (userCode.isEmpty()) {
            result.put("status", 1);
            result.put("msg", "参数不可为空!");
            result.put("content", "");
            result.put("code", "01");
            return result;
        }
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        try {
            String sql = "select p.dept_id, d.dept_name  from 1521_user_dept p 	left  JOIN  1521_dsj_dept d on p.dept_id =  d.dept_id   where p.user_id = ? ";
            List<Map<String, Object>> list = DBUtil.queryMapList(conn, sql, userCode);
            result.put("user", JSON.toJSONString(list));
            result.put("status", 0);
            result.put("msg", "查询成功");
        } finally {
            if (null != conn)
                conn.close();
        }
        return result;
    }

    @ResponseBody
    @ApiOperation(value = "修改用户的数据权限")
    @RequestMapping(value = "/updatePermission", method = RequestMethod.GET)
    public JSONObject updatePermission(
            @RequestParam(value = "userCode", defaultValue = "") String userCode,
            @RequestParam(value = "deptids", defaultValue = "") String deptids,
            HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        if (userCode.isEmpty()) {
            result.put("status", 1);
            result.put("msg", "参数不可为空!");
            result.put("content", "");
            result.put("code", "01");
            return result;
        }
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        try {
            String sql = "delete  from 1521_user_dept  where user_id = ? ";
            DBUtil.update(conn, sql, userCode);
            if (!deptids.isEmpty()) {
                List<String> deptList = Arrays.asList(deptids.split(","));
                for (int i = 0; i < deptList.size(); i++) {
                    String sql1 = " INSERT INTO 1521_user_dept(user_id, dept_id) VALUES(?,?)";
                    DBUtil.update(conn, sql1, userCode, deptList.get(i));
                }
            }
        } catch (Exception e) {
            result.put("status", 1);
            result.put("msg", "保存失败，请联系管理员");
        } finally {
            if (null != conn)
                conn.close();
        }
        result.put("status", 0);
        result.put("msg", "修改成功");
        return result;
    }


    @ResponseBody
    @ApiOperation(value = "查询所有部门")
    @RequestMapping(value = "/selectDeptList", method = RequestMethod.GET)
    public JSONObject selectDeptList(
            HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        try {
            String sql = "select a.dept_id,a.dept_name  from 1521_dsj_dept a";
            List<Map<String, Object>> deptList = DBUtil.queryMapList(conn, sql);
            result.put("deptList", deptList);
            result.put("status", 0);
            result.put("msg", "查询成功");
        } finally {
            if (null != conn)
                conn.close();
        }
        return result;
    }

}
