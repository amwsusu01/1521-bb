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
import java.util.UUID;

@Api("邮件管理接口")
@Controller
@RequestMapping(value = "/mail")
public class MailController extends BaseController {

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
	@ApiOperation(value = "查询预警模板列表")
	@RequestMapping(value = "/mailTemplateList", method = RequestMethod.GET)
	public JSONObject mailTemplateList(
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		try{
		String sql = "select *  from mail_template ";
		List<Map<String, Object>> list = DBUtil.queryMapList(conn, sql );
		result.put("list", JSON.toJSONString(list));
		result.put("status", 0);
		result.put("msg", "查询成功");
		} finally {
			if (null != conn)
				conn.close();
		}
		return result;
	}

	@ResponseBody
	@ApiOperation(value = "查询模板列表关联的人员列表")
	@RequestMapping(value = "/mailNotifierList", method = RequestMethod.GET)
	public JSONObject mailNotifierList(
			@RequestParam(value = "templateId", defaultValue = "") String templateId,
			@RequestParam(value = "userId ", defaultValue = "") String userId,
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		String sql = "select d.id ,d.name ,d.email from mail_template p left JOIN mail_notifier d on p.id =  d.templateId where p.id = ? ";
		if (templateId.isEmpty()) {
			result.put("status", 1);
			result.put("msg", "参数不可为空!");
			result.put("content", "");
			result.put("code", "01");
			return result;
		}
		if (!userId.isEmpty()) {
			sql = sql+ " and d.id = ? ";
		}
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		try{
		List<Map<String, Object>> list = DBUtil.queryMapList(conn, sql, templateId ,userId);
		result.put("list", JSON.toJSONString(list));
		result.put("status", 0);
		result.put("msg", "查询成功");
		} finally {
			if (null != conn)
				conn.close();
		}
		return result;
	}
	
	@ResponseBody
	@ApiOperation(value = "修改/删除关联人员")
	@RequestMapping(value = "/updateNotifier", method = RequestMethod.GET)
	public JSONObject updateNotifier(
			@RequestParam(value = "userId", defaultValue = "") String userId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "type", defaultValue = "") String type,
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		if (type.isEmpty() || userId.isEmpty() ) {
			result.put("status", 1);
			result.put("msg", "用户ID或操作类型不可为空!");
			result.put("content", "");
			result.put("code", "01");
			return result;
		}
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		try {
			if(type.equals("1")){
				String sql = "delete  from mail_notifier  where id = ? ";
				DBUtil.update(conn, sql, userId);
				result.put("status", 0);
				result.put("msg", "删除成功");
			}else if(type.equals("2")){
				if (name.isEmpty() || email.isEmpty() ) {
					result.put("status", 1);
					result.put("msg", "用户信息不可为空!");
					result.put("content", "");
					result.put("code", "01");
					return result;
				}
				String sql = "update mail_notifier SET name = ?, email = ? where id = ? ";
				DBUtil.update(conn, sql,name ,email, userId);
				result.put("status", 0);
				result.put("msg", "修改成功");
			}else{
				result.put("status", 1);
				result.put("msg", "操作类型有误!");
				result.put("content", "");
				result.put("code", "01");
			}
		} catch (Exception e) {
			result.put("status", 1);
			result.put("msg", "修改失败，请联系管理员");
		} finally {
			if (null != conn)
				conn.close();
		}
		return result;
	}
	
	@ResponseBody
	@ApiOperation(value = "新增关联人员")
	@RequestMapping(value = "/addNotifier", method = RequestMethod.GET)
	public JSONObject addNotifier(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "templateId", defaultValue = "") String templateId,
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		if (templateId.isEmpty() || name.isEmpty() || email.isEmpty()) {
			result.put("status", 1);
			result.put("msg", "参数不可为空!");
			result.put("content", "");
			result.put("code", "01");
			return result;
		}
		//生成UUid
        String uuid = UUID.randomUUID().toString();
        String id = uuid.replaceAll("-", "");
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		try {
			String sql1 = " INSERT INTO mail_notifier(id, name ,email ,templateId ) VALUES(?,?,?,?)";
			DBUtil.update(conn, sql1, id, name, email ,templateId);
			result.put("status", 0);
			result.put("msg", "新增成功");
		} catch (Exception e) {
			result.put("status", 1);
			result.put("msg", "新增失败，请联系管理员");
		} finally {
			if (null != conn)
				conn.close();
		}
		return result;
	}

	@ResponseBody
	@ApiOperation(value = "查询发送的预警记录")
	@RequestMapping(value = "/mailRecordList", method = RequestMethod.GET)
	public JSONObject mailRecordList(
			@RequestParam(value = "warningLevel", defaultValue = "") String warningLevel,
			@RequestParam(value = "beginDate", defaultValue = "") String beginDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate ,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		String sql = " where 1=1 ";
		if (!StringUtils.isBlank(warningLevel)) {
			String deptsql = DBUtil.setSql(warningLevel);
			sql = sql + " and  warning_level in ( " + deptsql + " )";
		}
		if (!StringUtils.isBlank(beginDate)) {
			sql = sql + " and beginDate >= ? ";
		}
		if (!StringUtils.isBlank(endDate)) {
			sql = sql + " and beginDate <= ? ";
		}
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		try {
			String sql1 = " select * from mail_record  " + sql + " ORDER by warning_level  LIMIT ?, ? ";
			String countSql = " select count(*) as count from mail_record " + sql;
	        List<Map<String, Object>> list = DBUtil.queryMapListByPagesize(conn, sql1 , page ,pageSize, warningLevel , beginDate, endDate  );
	        List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql ,warningLevel, beginDate, endDate );
	        result.put("list", list);
			result.put("count", countList.get(0).get("count"));
			result.put("status", 0);
			result.put("msg", "查询成功");
		} finally {
			if (null != conn)
				conn.close();
		}
		return result;
	}
	
}
