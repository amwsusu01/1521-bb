package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.BaseController;
import com.hanergy.reportForms.commons.utils.DBUtil;
import com.hanergy.reportForms.config.JDBCInfoConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

@Api("报表接口")
@Controller
@RequestMapping(value = "/report")
public class FromsController extends BaseController {

    @Resource
    private JdbcTemplate remoteJdbcTemplate;
    private JDBCInfoConfig jdbcConfig;
    @Autowired
    private JDBCInfoConfig configAutowired;

    @PostConstruct
    public void init() {
        jdbcConfig = this.configAutowired;
    }


    @ResponseBody
    @ApiOperation(value = "数据汇总统计")
    @RequestMapping(value = "/StatisticsList", method = RequestMethod.GET)
    public JSONObject statisticsList(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate) throws Exception {
        JSONObject result = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " where r.in_month between ? and ? ";
        if (!StringUtils.isBlank(dept)) {
            String deptsql = DBUtil.setSql(dept);
            sql = sql + " and  r.dept_name in ( " + deptsql + " )";
        } else {
            result.put("status", 1);
            result.put("msg", "请选择部门!");
            return result;
        }
        if (!StringUtils.isBlank(jobGrade)) {
            String jobGradesql = DBUtil.setSql(jobGrade);
            sql = sql + " and  r.level_stage in ( " + jobGradesql + " )";
        }
        sql = sql + " group by r.in_month ";
        try {
            // 超过4次(含)未请假未提报明细表
            String sql1 = "select r.in_month , sum(ifnull(n.nl_f,0)) cnt, round((sum(ifnull(n.nl_f,0))/ sum(r.renshu))*100,2) per from v_renshu r left join 1521_dsj_nlf n on r.in_month=n.month and r.level_stage=n.type and r.dept_name=n.deptname "
                    + sql;
            List<Map<String, Object>> lists1 = DBUtil.queryMapList(conn, sql1, beginDate, endDate, dept, jobGrade);
            String string1 = JSON.toJSONString(lists1);
            result.put("cgsc", string1);
            // 9点之前提报统计报表/一个月累计出现3次（含）
            String sql2 = "select r.in_month , sum(ifnull(n.cnt,0)) cnt, round((sum(ifnull(n.cnt,0))/ sum(r.renshu))*100,2) per from 1521_tibao_renshu r left join (select month,type1,dept_name,cnt from 1521_dsj_time_interval_total where type='9') n on r.in_month=n.month and r.level_stage=n.type1 and r.dept_name=n.dept_name "
                    + sql;
            List<Map<String, Object>> lists2 = DBUtil.queryMapList(conn, sql2, beginDate, endDate, dept, jobGrade);
            String string2 = JSON.toJSONString(lists2);
            result.put("jdzq", string2);
            // 12点之前提报统计报表/一个月累计出现3次（含）
            String sql3 = "select r.in_month , sum(ifnull(n.cnt,0)) cnt, round((sum(ifnull(n.cnt,0))/ sum(r.renshu))*100,2) per from 1521_tibao_renshu r left join (select month,type1,dept_name,cnt from 1521_dsj_time_interval_total where type='12') n on r.in_month=n.month and r.level_stage=n.type1 and r.dept_name=n.dept_name "
                    + sql;
            List<Map<String, Object>> lists3 = DBUtil.queryMapList(conn, sql3, beginDate, endDate, dept, jobGrade);
            String string3 = JSON.toJSONString(lists3);
            result.put("sedzq", string3);
            // 提报月平均条数小于5明细表
            String sql4 = "SELECT r.in_month , sum(ifnull( b.tiaoshu_5, 0 )) cnt, round((sum(ifnull(b.tiaoshu_5,0))/ sum(r.renshu))*100,2) per FROM 1521_tibao_renshu r left JOIN 1521_dsj_tiaoshu5_all b ON r.in_month = b.in_month AND r.level_stage = b.level_stage and r.dept_name=b.departmentname"
                    + sql;
            List<Map<String, Object>> lists4 = DBUtil.queryMapList(conn, sql4, beginDate, endDate, dept, jobGrade);
            String string4 = JSON.toJSONString(lists4);
            result.put("tsxyw", string4);
            // 提报月平均字数小于5明细表
            String sql5 = "SELECT r.in_month , sum(ifnull( b.zishu_5, 0 )) cnt, round((sum(ifnull(b.zishu_5,0))/ sum(r.renshu))*100,2) per FROM 1521_tibao_renshu r left JOIN 1521_dsj_zishu5_all b ON r.in_month = b.in_month AND r.level_stage = b.level_stage and r.dept_name=b.departmentname "
                    + sql;
            List<Map<String, Object>> lists5 = DBUtil.queryMapList(conn, sql5, beginDate, endDate, dept, jobGrade);
            String string5 = JSON.toJSONString(lists5);
            result.put("zsxyw", string5);
            // 提报内容重复超6次（含）明细表
            String sql6 = "select r.in_month , sum(ifnull(c.cn,0)) cnt, round((sum(ifnull(c.cn,0))/ sum(r.renshu))*100,2) per from 1521_tibao_renshu r left join 1521_dsj_cf c on r.in_month=c.month and r.level_stage=c.type and r.dept_name=c.dept_name "
                    + sql;
            List<Map<String, Object>> lists6 = DBUtil.queryMapList(conn, sql6, beginDate, endDate, dept, jobGrade);
            String string6 = JSON.toJSONString(lists6);
            result.put("cglt", string6);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn)
                conn.close();
        }
        return result;
    }

    @ResponseBody
    @ApiOperation(value = "部分热词")
    @RequestMapping(value = "/Vocabulary", method = RequestMethod.GET)
    public JSONObject vocabulary(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "beginDate", defaultValue = "") String beginDate) throws Exception {
        JSONObject json = new JSONObject();
        JSONArray jsArray = new JSONArray();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = "select p.name, p.freq from 1521_dsj_log_par p where 1=1 ";
        try {
            if (!StringUtils.isBlank(beginDate)) {
                sql = sql + " and p.month = ? ";
            }
            if (!StringUtils.isBlank(dept)) {
                sql = sql + " and p.deptname = ? ";
                String[] deptlist = dept.split(",");
                for (int i = 0; i < deptlist.length; i++) {
                    JSONObject json1 = new JSONObject();
                    List<Map<String, Object>> lists = DBUtil.queryMapList(conn, sql, beginDate, deptlist[i].trim());
                    json1.put("name", deptlist[i]);
                    json1.put("value", lists);
                    jsArray.add(json1);
                }
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
        } finally {
            if (null != conn)
                conn.close();
        }
        json.put("rs", jsArray);
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "问题明细")
    @RequestMapping(value = "/issueDetail", method = RequestMethod.GET)
    public JSONObject issueDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " where s.month between ? and ? ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  s.deptname in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  s.type in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)) {
                sql = sql + " and INSTR(TRIM(REPLACE(s.empname,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)) {
                sql = sql + " and s.employeeID=?";
            }
            //  部门问题明细表：
            String sql1 = " select s.month, s.day, s.employeeID, s.empname, s.deptname, s.sol_pro from 1521_dsj_solve s  " + sql + " ORDER by s.month  LIMIT ?, ? ";
            String countSql = " select count(*) as count from 1521_dsj_solve s " + sql;

            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql1, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "反省明细")
    @RequestMapping(value = "/introspectionDetail", method = RequestMethod.GET)
    public JSONObject introspectionDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " where s.month between ? and ? ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  s.deptname in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  s.type in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)) {
                sql = sql + " and INSTR(TRIM(REPLACE(s.empname,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)) {
                sql = sql + " and s.employeeID=?";
            }
            // 部门反省明细表
            String sql1 = " select s.month, s.day, s.employeeID, s.empname, s.deptname, s.introspect from 1521_dsj_introspect s  " + sql + " order by s.month LIMIT ?, ? ";
            String countSql = " select count(*) as count from 1521_dsj_introspect s  " + sql;
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql1, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "明细统计-超过4次(含)未请假未提报明细表")
    @RequestMapping(value = "/cgscDetail", method = RequestMethod.GET)
    public JSONObject cgscdetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " select d.month1, d.inputtime, d.employeeID, d.realName, d.frameName from 1521_dsj_nlf_detail d where d.month1 between ? and ? ";
        String countSql = " select count(*) as count from 1521_dsj_nlf_detail d where d.month1 between ? and ? ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  d.frameName in ( " + deptsql + " )";
                countSql = countSql + " and  d.frameName in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  d.type in ( " + jobGradesql + " )";
                countSql = countSql + " and  d.type in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)) {
                sql = sql + " and INSTR(TRIM(REPLACE(d.realName,\" \",\"\")),?)";
                countSql = countSql + " and INSTR(TRIM(REPLACE(d.realName,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)) {
                sql = sql + " and d.employeeID=?";
                countSql = countSql + " and d.employeeID=?";
            }
            String limit = " LIMIT ?,? ";
            // 超过4次(含)未请假未提报明细表
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql + limit, page, pageSize, beginDate, endDate, dept, jobGrade, employeeName, employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade, employeeName, employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }


    @ResponseBody
    @ApiOperation(value = "明细统计-平均条数小于5明细表")
    @RequestMapping(value = "/tsxywDetail", method = RequestMethod.GET)
    public JSONObject tsxywDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " select d.in_month, d.in_date, substr(d.inputtime,1,19) in_datetime, d.employeeID, d.empname, d.departmentname, d.content_new from 1521_tiaoshu5_hanzq_dtl d where d.in_month between ? and ? ";
        String countSql = " select count(*) as count from 1521_tiaoshu5_hanzq_dtl d where d.in_month between ? and ? ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  d.departmentname in ( " + deptsql + " )";
                countSql = countSql + " and  d.departmentname in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  d.level_stage in ( " + jobGradesql + " )";
                countSql = countSql + " and  d.level_stage in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)){
                sql = sql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
                countSql = countSql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)){
                sql = sql +" and d.employeeID=?";
                countSql = countSql +" and d.employeeID=?";
            }
            String limit = " LIMIT ?,? ";
            //平均条数小于5明细表
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql + limit, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "明细统计-平均字数小于5明细表")
    @RequestMapping(value = "/zsxywDetail", method = RequestMethod.GET)
    public JSONObject zsxywDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " select d.in_month,  d.in_date,  substr(d.inputtime,1,19) in_datetime,  d.employeeID,  d.empname, d.departmentname,  d.content_new  from 1521_zishu5_hanzq_dtl d where d.in_month between ? and ?  ";
        String countSql = " select count(*) as count from 1521_zishu5_hanzq_dtl d where d.in_month between ? and ?  ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  d.departmentname in ( " + deptsql + " )";
                countSql = countSql + " and  d.departmentname in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  d.level_stage in ( " + jobGradesql + " )";
                countSql = countSql + " and  d.level_stage in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)){
                sql = sql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
                countSql = countSql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)){
                sql = sql +" and d.employeeID=?";
                countSql = countSql +" and d.employeeID=?";
            }
            String limit = " LIMIT ?,? ";
            //平均字数小于5明细表
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql + limit, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "明细统计-9点之前提报数据明细表")
    @RequestMapping(value = "/jdzqDetail", method = RequestMethod.GET)
    public JSONObject jdzqDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " select d.month, substr(d.inputtime,1,10) day , substr(d.inputtime,1,19) inputtime , d.employeeID, d.emp_name, d.dept_name, d.content_new from 1521_dsj_time_interval_detail d where d.type='9' and d.month between ? and ?  ";
        String countSql = " select count(*) as count from 1521_dsj_time_interval_detail d where d.type='9' and d.month between ? and ?  ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  d.dept_name in ( " + deptsql + " )";
                countSql = countSql + " and  d.dept_name in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  d.type1 in ( " + jobGradesql + " )";
                countSql = countSql + " and  d.type1 in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)){

                sql = sql +" and INSTR(TRIM(REPLACE(d.emp_name,\" \",\"\")),?)";
                countSql = countSql +" and INSTR(TRIM(REPLACE(d.emp_name,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)){
                sql = sql +" and d.employeeID=?";
                countSql = countSql +" and d.employeeID=?";
            }
            String limit = " LIMIT ?,? ";
            // 9点之前提报数据明细表
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql + limit, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "明细统计-12点之前提报数据明细表")
    @RequestMapping(value = "/sedzqDetail", method = RequestMethod.GET)
    public JSONObject sedzqDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " select d.month, substr(d.inputtime,1,10) day ,substr(d.inputtime,1,19) inputtime , d.employeeID, d.emp_name, d.dept_name, d.content_new from 1521_dsj_time_interval_detail d where d.type='12' and d.month between ? and ?  ";
        String countSql = " select count(*) as count from 1521_dsj_time_interval_detail d where d.type='12' and d.month between ? and ?  ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  d.dept_name in ( " + deptsql + " )";
                countSql = countSql + " and  d.dept_name in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  d.type1 in ( " + jobGradesql + " )";
                countSql = countSql + " and  d.type1 in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)){
                sql = sql +" and INSTR(TRIM(REPLACE(d.emp_name,\" \",\"\")),?)";
                countSql = countSql +" and INSTR(TRIM(REPLACE(d.emp_name,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)){
                sql = sql +" and d.employeeID=?";
                countSql = countSql +" and d.employeeID=?";
            }
            String limit = " LIMIT ?,? ";
            // 12点之前提报数据明细表
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql + limit, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "明细统计-提报内容重复超6次（含）汇总表")
    @RequestMapping(value = "/cglhzDetail", method = RequestMethod.GET)
    public JSONObject cglhzDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " select substr(d.day,1,7) month, d.day, d.employeeID, d.empname, d.deptname, d.ts, d.content from 1521_dsj_cf_total d  where substr(d.day,1,7) between ? and ?  ";
        String countSql = " select count(*) as count from 1521_dsj_cf_total d  where substr(d.day,1,7) between ? and ?  ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  d.deptname in ( " + deptsql + " )";
                countSql = countSql + " and  d.deptname in ( " + deptsql + " )";

            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }
            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  d.type in ( " + jobGradesql + " )";
                countSql = countSql + " and  d.type in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)){
                sql = sql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
                countSql = countSql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)){
                sql = sql +" and d.employeeID=?";
                countSql = countSql +" and d.employeeID=?";
            }
            String limit = " LIMIT ?,? ";
            // 提报内容重复超6次（含）汇总表
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql + limit, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }

    @ResponseBody
    @ApiOperation(value = "明细统计-提报内容重复超6次（含）明细表")
    @RequestMapping(value = "/cglmxDetail", method = RequestMethod.GET)
    public JSONObject cglmxDetail(
            @RequestParam(value = "dept", defaultValue = "") String dept,
            @RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
            @RequestParam(value = "employeeName", defaultValue = "") String employeeName,
            @RequestParam(value = "employeeID", defaultValue = "") String employeeID,
            @RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
            @RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        JSONObject json = new JSONObject();
        Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
        String sql = " select substr(d.day1,1,7) month, d.day1, d.employeeID, d.empname, d.deptname, d.content_new from 1521_dsj_cf_detail d where substr(d.day1,1,7) between ? and ?  ";
        String countSql = " select count(*) as count from 1521_dsj_cf_detail d where substr(d.day1,1,7) between ? and ?  ";
        try {
            if (!StringUtils.isBlank(dept)) {
                String deptsql = DBUtil.setSql(dept);
                sql = sql + " and  d.deptname in ( " + deptsql + " )";
                countSql = countSql + " and  d.deptname in ( " + deptsql + " )";
            } else {
                json.put("status", 1);
                json.put("msg", "请选择部门!");
                return json;
            }

            if (!StringUtils.isBlank(jobGrade)) {
                String jobGradesql = DBUtil.setSql(jobGrade);
                sql = sql + " and  d.type in ( " + jobGradesql + " )";
                countSql = countSql + " and  d.type in ( " + jobGradesql + " )";
            }
            if (StringUtils.isNotBlank(employeeName)){
                sql = sql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
                countSql = countSql +" and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
            }
            if (StringUtils.isNotBlank(employeeID)){
                sql = sql +" and d.employeeID=?";
                countSql = countSql +" and d.employeeID=?";
            }
            String limit = " LIMIT ?,? ";
            // 提报内容重复超6次（含）明细表
            List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, sql + limit, page, pageSize, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql, beginDate, endDate, dept, jobGrade,employeeName,employeeID);
            String string = JSON.toJSONString(lists);
            json.put("list", string);
            json.put("count", countList.get(0).get("count"));
        } finally {
            if (null != conn)
                conn.close();
        }
        return json;
    }


}
