package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.BaseController;
import com.hanergy.reportForms.commons.utils.DBUtil;
import com.hanergy.reportForms.commons.utils.ReturnExcel;
import com.hanergy.reportForms.config.JDBCInfoConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

@Api("导出接口")
@Controller
@RequestMapping(value = "/return")
public class ReturnController extends BaseController {

    private JDBCInfoConfig jdbcConfig;
    @Autowired
    private JDBCInfoConfig configAutowired;
    @PostConstruct
    public void init() {
    	jdbcConfig = this.configAutowired;
    }
	
	@ApiOperation(value = "导出excel")
	@RequestMapping(value = "/printExcel", method = RequestMethod.GET)
	public void printExcel(
			@RequestParam(value = "dept", defaultValue = "") String dept,
			@RequestParam(value = "jobGrade", defaultValue = "") String jobGrade,
			@RequestParam(value = "employeeName", defaultValue = "") String employeeName,
			@RequestParam(value = "employeeID", defaultValue = "") String employeeID,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "beginDate", defaultValue = "2018-03") String beginDate,
			@RequestParam(value = "endDate", defaultValue = "2018-11") String endDate,
			HttpServletResponse response) throws Exception {
		String sql = null ;

		Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
		//问题
		String sql1 = " select s.month , s.day, s.employeeID, s.empname, s.deptname, s.sol_pro from 1521_dsj_solve s where s.month between ? and ?   " ;
		//反省
		String sql2 = " select s.month, s.day, s.employeeID, s.empname, s.deptname, s.introspect from 1521_dsj_introspect s  where s.month between ? and ?  " ;
		//超过4次(含)未请假未提报明细表
		String sql3 = " select d.month1 , d.inputtime , d.employeeID, d.realName, d.frameName from 1521_dsj_nlf_detail d where d.month1 between ? and ? ";
		//提报月平均条数小于5明细表
		String sql4 = " select d.in_month, d.in_date, substr(d.inputtime,1,19) in_datetime, d.employeeID, d.empname, d.departmentname, d.content_new from 1521_tiaoshu5_hanzq_dtl d where d.in_month between ? and ? " ;
		//提报月平均字数小于5明细表
		String sql5 = " select d.in_month,  d.in_date,  substr(d.inputtime,1,19) in_datetime,  d.employeeID,  d.empname, d.departmentname,  d.content_new  from 1521_zishu5_hanzq_dtl d where d.in_month between ? and ?  " ;
		//9点之前：
		String sql6 = " select d.month, substr(d.inputtime,1,10) day , substr(d.inputtime,1,19) inputtime, d.employeeID, d.emp_name, d.dept_name, d.content_new from 1521_dsj_time_interval_detail d where d.type='9' and d.month between ? and ?  " ;
		//12点之前：
		String sql7 = " select d.month, substr(d.inputtime,1,10) day , substr(d.inputtime,1,19) inputtime, d.employeeID, d.emp_name, d.dept_name, d.content_new from 1521_dsj_time_interval_detail d where d.type='12' and d.month between ? and ?  " ;
		//提报内容重复超6次(含)汇总表
		String sql8 = " select substr(d.day,1,7) month, d.day, d.employeeID, d.empname, d.deptname, d.ts, d.content from 1521_dsj_cf_total d  where substr(d.day,1,7) between ? and ?  " ;
		//提报内容重复超6次（含）明细表
		String sql9 = " select substr(d.day1,1,7) month, d.day1, d.employeeID, d.empname, d.deptname, d.content_new from 1521_dsj_cf_detail d where substr(d.day1,1,7) between ? and ?  " ;


		if(!StringUtils.isBlank(dept)){
			String deptsql = DBUtil.setSql(dept);
			sql3 = sql3+ " and  d.frameName in ( "+deptsql+" )";
			sql4 = sql4+ " and  d.departmentname in ( "+deptsql+" )";
			sql5 = sql5+ " and  d.departmentname in ( "+deptsql+" )";
			sql6 = sql6+ " and  d.dept_name in ( "+deptsql+" )";
			sql7 = sql7+ " and  d.dept_name in ( "+deptsql+" )";
			sql8 = sql8+ " and  d.deptname in ( "+deptsql+" )";
			sql9 = sql9+ " and  d.deptname in ( "+deptsql+" )";
			sql1 = sql1+ " and  s.deptname in ( "+deptsql+" )";
			sql2 = sql2+ " and  s.deptname in ( "+deptsql+" )";
		}
		if(!StringUtils.isBlank(jobGrade)){
			String jobGradesql = DBUtil.setSql(jobGrade);
			sql3 = sql3+ " and  d.type in ( "+jobGradesql+" )";
			sql4 = sql4+ " and  d.level_stage in ( "+jobGradesql+" )";
			sql5 = sql5+ " and  d.level_stage in ( "+jobGradesql+" )";
			sql6 = sql6+ " and  d.type1 in ( "+jobGradesql+" )";
			sql7 = sql7+ " and  d.type1 in ( "+jobGradesql+" )";
			sql8 = sql8+ " and  d.type in ( "+jobGradesql+" )";
			sql9 = sql9+ " and  d.type in ( "+jobGradesql+" )";
			sql1 = sql1+ " and  s.type in ( "+jobGradesql+" )";
			sql2 = sql2+ " and  s.type in ( "+jobGradesql+" )";
		}
		if (StringUtils.isNotBlank(employeeName)) {
			sql3 = sql3+ " and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
			sql4 = sql4+ " and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
			sql5 = sql5+ " and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
			sql6 = sql6+ " and INSTR(TRIM(REPLACE(d.emp_name,\" \",\"\")),?)";
			sql7 = sql7+ " and INSTR(TRIM(REPLACE(d.emp_name,\" \",\"\")),?)";
			sql8 = sql8+ " and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
			sql9 = sql9+ " and INSTR(TRIM(REPLACE(d.empname,\" \",\"\")),?)";
			sql1 = sql1+ " and INSTR(TRIM(REPLACE(s.empname,\" \",\"\")),?)";
			sql2 = sql2+ " and INSTR(TRIM(REPLACE(s.empname,\" \",\"\")),?)";
		}
		if (StringUtils.isNotBlank(employeeID)) {
			sql3 = sql3+ " and d.employeeID=?";
			sql4 = sql4+ " and d.employeeID=?";
			sql5 = sql5+ " and d.employeeID=?";
			sql6 = sql6+ " and d.employeeID=?";
			sql7 = sql7+ " and d.employeeID=?";
			sql8 = sql8+ " and d.employeeID=?";
			sql9 = sql9+ " and d.employeeID=?";
			sql1 = sql1+ " and s.employeeID=?";
			sql2 = sql2+ " and s.employeeID=?";
		}
		if(type.equals("type1")){
			sql = sql1 ;
		}
		if(type.equals("type2")){
			sql = sql2 ;
		}
		if(type.equals("type3")){
			sql = sql3 ;
		}
		if(type.equals("type4")){
			sql = sql4 ;
		}
		if(type.equals("type5")){
			sql = sql5 ;
		}
		if(type.equals("type6")){
			sql = sql6 ;
		}
		if(type.equals("type7")){
			sql = sql7 ;
		}
		if(type.equals("type8")){
			sql = sql8 ;
		}
		if(type.equals("type9")){
			sql = sql9 ;
		}

		try{
	        List<Map<String, Object>> lists = DBUtil.queryMapList(conn, sql , beginDate, endDate, dept , jobGrade,employeeName,employeeID);
			ReturnExcel.printFormsExcel(response,lists,type);
		} finally {
			if (null != conn)
				conn.close();
		}
	}
	
	@ResponseBody
	@ApiOperation(value = "取最新更新时间")
	@RequestMapping(value = "/getDate", method = RequestMethod.GET)
	public JSONObject getDate( HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		Connection conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUsername(), jdbcConfig.getPassword());
		String sql = "select max(in_date)  in_date from 1521_update_time";
        try{
		List<Map<String, Object>> lists = DBUtil.queryMapList(conn, sql );
        String date = lists.get(0).get("in_date").toString();
        result.put("Date", date );
		} finally {
			if (null != conn)
				conn.close();
		}
		return result ;
	}
}
