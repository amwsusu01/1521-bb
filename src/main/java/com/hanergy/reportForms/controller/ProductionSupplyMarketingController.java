package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.BaseController;
import com.hanergy.reportForms.commons.utils.DBUtil;
import com.hanergy.reportForms.commons.utils.ReturnExcel;
import com.hanergy.reportForms.config.JDBCInfoConfig;
import com.hanergy.reportForms.dto.ProductionSmInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

@Api("产销存项目接口")
@Controller
@RequestMapping(value = "/psm")
public class ProductionSupplyMarketingController extends BaseController {

    private JDBCInfoConfig jdbcConfig;
    @Autowired
    private JDBCInfoConfig configAutowired;
    @PostConstruct
    public void init() {
    	jdbcConfig = this.configAutowired;
    }
	
	@ResponseBody
	@ApiOperation(value = "产品维表和事业部维表")
	@RequestMapping(value = "/dimension", method = RequestMethod.GET)
	public JSONObject cglmxDetail( HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		//产品维表
		String cpsql = " select chanpin_name from dim_chanpin " ;
		//事业部维表
		String sysql = " select shiyebu_name from dim_shiyebu " ;
		//提交人
		String tjrsql = "select distinct fd_name from gxyth_dtl" ;

		try{
			List<Map<String, Object>> cplist = DBUtil.queryMapList(conn, cpsql);
	        List<Map<String, Object>> syblist = DBUtil.queryMapList(conn, sysql);
	        List<Map<String, Object>> tjrlist = DBUtil.queryMapList(conn, tjrsql);
	        json.put("product", JSON.toJSONString(cplist));
	        json.put("dept", JSON.toJSONString(syblist));
	        json.put("user", JSON.toJSONString(tjrlist));
		} finally {
			if (null != conn)
				conn.close();
		}
		return json;
	}
	
	//产供销一体化明细报表
    @RequestMapping(value = "/getProductDetailed",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getProductDetailed(@RequestBody ProductionSmInfo info  ,HttpServletResponse response)throws Exception {
		JSONObject json = new JSONObject();
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		String sql = "where 1=1 ";

		try{
			if(info.getPage() == null || info.getPageSize() == null){
				json.put("status", 1);
				json.put("msg", "分页参数不能为空。");
				return json;
			}
			if(!StringUtils.isBlank(info.getCountDate())){
				sql = sql+ " and in_date = ? ";
			}
			if(!StringUtils.isBlank(info.getProducts())){
				String DBsql = DBUtil.setSql(info.getProducts());
				sql = sql+ " and CHANPIN_NAME in ( "+DBsql+" )";
			}
			if(!StringUtils.isBlank(info.getCAIGOUSHENQING())){
				/*String DBsql = DBUtil.setSql(info.getCAIGOUSHENQING());
				sql = sql+ " and FD_CAIGOUSHENQING in ( "+DBsql+" )";*/
				sql = sql+ " and FD_CAIGOUSHENQING like ? ";
				info.setCAIGOUSHENQING("%"+info.getCAIGOUSHENQING().trim()+"%");
			}
			if(!StringUtils.isBlank(info.getSHIYEBU())){
				String DBsql = DBUtil.setSql(info.getSHIYEBU());
				sql = sql+ " and FD_SHIYEBU in ( "+DBsql+" )";
			}
			if(!StringUtils.isBlank(info.getWULIAOMIAOSHU())){
				/*String DBsql = DBUtil.setSql(info.getWULIAOMIAOSHU());
				sql = sql+ " and FD_WULIAOMIAOSHU in ( "+DBsql+" )";*/
				sql = sql+ " and FD_WULIAOMIAOSHU like ? ";
				info.setWULIAOMIAOSHU("%"+info.getWULIAOMIAOSHU().trim()+"%");
			}
			if(!StringUtils.isBlank(info.getNAME())){
				String DBsql = DBUtil.setSql(info.getNAME());
				sql = sql+ " and FD_NAME in ( "+DBsql+" )";
			}

			if(!StringUtils.isBlank(info.getPRJIHUA())){
				sql = sql+ " and PRJIHUA >= ? ";
			}
			if(!StringUtils.isBlank(info.getPRJIHUAEND())){
				sql = sql+ " and PRJIHUA <= ? ";
			}

			if(!StringUtils.isBlank(info.getPRSHIJI())){
				sql = sql+ " and PRSHIJI  >= ? ";
			}
			if(!StringUtils.isBlank(info.getPRSHIJIEND())){
				sql = sql+ " and PRSHIJI  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getCAIGOUJIHUA())){
				sql = sql+ " and CAIGOUJIHUA  >= ? ";
			}
			if(!StringUtils.isBlank(info.getCAIGOUJIHUAEND())){
				sql = sql+ " and CAIGOUJIHUA  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getCAIGOUBIANGENG())){
				sql = sql+ " and CAIGOUBIANGENG  >= ? ";
			}
			if(!StringUtils.isBlank(info.getCAIGOUBIANGENGEND())){
				sql = sql+ " and CAIGOUBIANGENG  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getCAIGOUSHIJI())){
				sql = sql+ " and CAIGOUSHIJI  >= ? ";
			}
			if(!StringUtils.isBlank(info.getCAIGOUSHIJIEND())){
				sql = sql+ " and CAIGOUSHIJI  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getYUFUKUANJIHUA())){
				sql = sql+ " and YUFUKUANJIHUA  >= ? ";
			}
			if(!StringUtils.isBlank(info.getYUFUKUANJIHUAEND())){
				sql = sql+ " and YUFUKUANJIHUA  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getYUFUKUANBIANGENG())){
				sql = sql+ " and YUFUKUANBIANGENG  >= ? ";
			}
			if(!StringUtils.isBlank(info.getYUFUKUANBIANGENGEND())){
				sql = sql+ " and YUFUKUANBIANGENG  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getYUFUKUANSHIJI())){
				sql = sql+ " and YUFUKUANSHIJI  >= ? ";
			}
			if(!StringUtils.isBlank(info.getYUFUKUANSHIJIEND())){
				sql = sql+ " and YUFUKUANSHIJI  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getSHENGCHANJIHUA())){
				sql = sql+ " and SHENGCHANJIHUA  >= ? ";
			}
			if(!StringUtils.isBlank(info.getSHENGCHANJIHUAEND())){
				sql = sql+ " and SHENGCHANJIHUA  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getSHENGCHANBIANGENG())){
				sql = sql+ " and SHENGCHANBIANGENG  >= ? ";
			}
			if(!StringUtils.isBlank(info.getSHENGCHANBIANGENGEND())){
				sql = sql+ " and SHENGCHANBIANGENG  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getSHENGCHANSHIJI())){
				sql = sql+ " and SHENGCHANSHIJI  >= ? ";
			}
			if(!StringUtils.isBlank(info.getSHENGCHANSHIJIEND())){
				sql = sql+ " and SHENGCHANSHIJI  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getTIHUOKUANJIHUA())){
				sql = sql+ " and TIHUOKUANJIHUA  >= ? ";
			}
			if(!StringUtils.isBlank(info.getTIHUOKUANJIHUAEND())){
				sql = sql+ " and TIHUOKUANJIHUA  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getTIHUOKUANBIANGENG())){
				sql = sql+ " and TIHUOKUANBIANGENG  >= ? ";
			}
			if(!StringUtils.isBlank(info.getTIHUOKUANBIANGENGEND())){
				sql = sql+ " and TIHUOKUANBIANGENG  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getTIHUOKUANSHIJI())){
				sql = sql+ " and TIHUOKUANSHIJI  >= ? ";
			}
			if(!StringUtils.isBlank(info.getTIHUOKUANSHIJIEND())){
				sql = sql+ " and TIHUOKUANSHIJI  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getJIAOFUJIHUA())){
				sql = sql+ " and JIAOFUJIHUA  >= ? ";
			}
			if(!StringUtils.isBlank(info.getJIAOFUJIHUAEND())){
				sql = sql+ " and JIAOFUJIHUA  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getJIAOFUBIANGENG())){
				sql = sql+ " and JIAOFUBIANGENG  >= ? ";
			}
			if(!StringUtils.isBlank(info.getJIAOFUBIANGENGEND())){
				sql = sql+ " and JIAOFUBIANGENG  <= ? ";
			}
			
			if(!StringUtils.isBlank(info.getJIAOFUBIANGENG())){
				sql = sql+ " and JIAOFUBIANGENG  >= ? ";
			}
			if(!StringUtils.isBlank(info.getJIAOFUBIANGENGEND())){
				sql = sql+ " and JIAOFUBIANGENG  <= ? ";
			}
			if(!StringUtils.isBlank(info.getIsExprot()) && info.getIsExprot().equals("1")){
				String dbsql = " select a.CHANPIN_NAME,a.FD_CAIGOUSHENQING,a.FD_SHIYEBU,a.FD_WULIAOBIANHAO,a.FD_WULIAOMIAOSHU,a.FD_NAME,a.WARNING,a.PRJIHUA,a.PRSHIJI,a.CAIGOUJIHUA,a.CAIGOUBIANGENG,a.CAIGOUSHIJI,a.YUFUKUANJIHUA,a.YUFUKUANBIANGENG,a.YUFUKUANSHIJI,a.SHENGCHANJIHUA,a.SHENGCHANBIANGENG,a.SHENGCHANSHIJI,a.TIHUOKUANJIHUA,a.TIHUOKUANBIANGENG,a.TIHUOKUANSHIJI,a.JIAOFUJIHUA,a.JIAOFUBIANGENG,a.JIAOFUSHIJI from gxyth_dtl a " + sql ;
		        List<Map<String, Object>> lists = DBUtil.queryMapList(conn, dbsql , info.getCountDate(), info.getProducts(), info.getCAIGOUSHENQING(),  info.getSHIYEBU(), info.getWULIAOMIAOSHU(), info.getNAME(), info.getPRJIHUA(), info.getPRJIHUAEND(),  info.getPRSHIJI(),  info.getPRSHIJIEND(),   info.getCAIGOUJIHUA(),  info.getCAIGOUJIHUAEND(),   info.getCAIGOUBIANGENG(),  info.getCAIGOUBIANGENGEND(),   info.getCAIGOUSHIJI(),  info.getCAIGOUSHIJIEND(),   info.getYUFUKUANJIHUA(),  info.getYUFUKUANJIHUAEND(),   info.getYUFUKUANBIANGENG(),  info.getYUFUKUANBIANGENGEND(),   info.getYUFUKUANSHIJI(),  info.getYUFUKUANSHIJIEND(),   info.getSHENGCHANJIHUA(),  info.getSHENGCHANJIHUAEND(),   info.getSHENGCHANBIANGENG(),  info.getSHENGCHANBIANGENGEND(),   info.getSHENGCHANSHIJI(),   info.getSHENGCHANSHIJIEND(),    info.getTIHUOKUANJIHUA(),   info.getTIHUOKUANJIHUAEND(),    info.getTIHUOKUANBIANGENG(),   info.getTIHUOKUANBIANGENGEND(),    info.getTIHUOKUANSHIJI(),  info.getTIHUOKUANSHIJIEND(),   info.getJIAOFUJIHUA(),  info.getJIAOFUJIHUAEND(),   info.getJIAOFUBIANGENG(),  info.getJIAOFUBIANGENGEND(),   info.getJIAOFUBIANGENG(),  info.getJIAOFUBIANGENGEND()  );
		        Boolean flage = ReturnExcel.printProductDetailedExcel(response,lists);
		        if(flage){
					json.put("status", 0);
					json.put("msg", "导出成功!");	
		        }else{
					json.put("status", 1);
					json.put("msg", "导出失败!");		
		        }
			}else{
				String dbsql = " select a.CHANPIN_NAME,a.FD_CAIGOUSHENQING,a.FD_SHIYEBU,a.FD_WULIAOBIANHAO,a.FD_WULIAOMIAOSHU,a.FD_NAME,a.WARNING,a.PRJIHUA,a.PRSHIJI,a.CAIGOUJIHUA,a.CAIGOUBIANGENG,a.CAIGOUSHIJI,a.YUFUKUANJIHUA,a.YUFUKUANBIANGENG,a.YUFUKUANSHIJI,a.SHENGCHANJIHUA,a.SHENGCHANBIANGENG,a.SHENGCHANSHIJI,a.TIHUOKUANJIHUA,a.TIHUOKUANBIANGENG,a.TIHUOKUANSHIJI,a.JIAOFUJIHUA,a.JIAOFUBIANGENG,a.JIAOFUSHIJI from gxyth_dtl a " + sql + " Limit ? ,?  ";
				String countSql = " select count(*) as count from gxyth_dtl " + sql;
				List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, dbsql , info.getPage() ,info.getPageSize(), info.getCountDate(), info.getProducts(), info.getCAIGOUSHENQING(),  info.getSHIYEBU(), info.getWULIAOMIAOSHU(), info.getNAME(), info.getPRJIHUA(), info.getPRJIHUAEND(),  info.getPRSHIJI(),  info.getPRSHIJIEND(),   info.getCAIGOUJIHUA(),  info.getCAIGOUJIHUAEND(),   info.getCAIGOUBIANGENG(),  info.getCAIGOUBIANGENGEND(),   info.getCAIGOUSHIJI(),  info.getCAIGOUSHIJIEND(),   info.getYUFUKUANJIHUA(),  info.getYUFUKUANJIHUAEND(),   info.getYUFUKUANBIANGENG(),  info.getYUFUKUANBIANGENGEND(),   info.getYUFUKUANSHIJI(),  info.getYUFUKUANSHIJIEND(),   info.getSHENGCHANJIHUA(),  info.getSHENGCHANJIHUAEND(),   info.getSHENGCHANBIANGENG(),  info.getSHENGCHANBIANGENGEND(),   info.getSHENGCHANSHIJI(),   info.getSHENGCHANSHIJIEND(),    info.getTIHUOKUANJIHUA(),   info.getTIHUOKUANJIHUAEND(),    info.getTIHUOKUANBIANGENG(),   info.getTIHUOKUANBIANGENGEND(),    info.getTIHUOKUANSHIJI(),  info.getTIHUOKUANSHIJIEND(),   info.getJIAOFUJIHUA(),  info.getJIAOFUJIHUAEND(),   info.getJIAOFUBIANGENG(),  info.getJIAOFUBIANGENGEND(),   info.getJIAOFUBIANGENG(),  info.getJIAOFUBIANGENGEND()  );
		        List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql , info.getCountDate(), info.getProducts(), info.getCAIGOUSHENQING(),  info.getSHIYEBU(), info.getWULIAOMIAOSHU(), info.getNAME(), info.getPRJIHUA(), info.getPRJIHUAEND(),  info.getPRSHIJI(),  info.getPRSHIJIEND(),   info.getCAIGOUJIHUA(),  info.getCAIGOUJIHUAEND(),   info.getCAIGOUBIANGENG(),  info.getCAIGOUBIANGENGEND(),   info.getCAIGOUSHIJI(),  info.getCAIGOUSHIJIEND(),   info.getYUFUKUANJIHUA(),  info.getYUFUKUANJIHUAEND(),   info.getYUFUKUANBIANGENG(),  info.getYUFUKUANBIANGENGEND(),   info.getYUFUKUANSHIJI(),  info.getYUFUKUANSHIJIEND(),   info.getSHENGCHANJIHUA(),  info.getSHENGCHANJIHUAEND(),   info.getSHENGCHANBIANGENG(),  info.getSHENGCHANBIANGENGEND(),   info.getSHENGCHANSHIJI(),   info.getSHENGCHANSHIJIEND(),    info.getTIHUOKUANJIHUA(),   info.getTIHUOKUANJIHUAEND(),    info.getTIHUOKUANBIANGENG(),   info.getTIHUOKUANBIANGENGEND(),    info.getTIHUOKUANSHIJI(),  info.getTIHUOKUANSHIJIEND(),   info.getJIAOFUJIHUA(),  info.getJIAOFUJIHUAEND(),   info.getJIAOFUBIANGENG(),  info.getJIAOFUBIANGENGEND(),   info.getJIAOFUBIANGENG(),  info.getJIAOFUBIANGENGEND()  );
				json.put("status", 0);
				json.put("msg", "查询成功!");	
		        json.put("list", lists);
				json.put("count", countList.get(0).get("count"));
			}
		} finally {
			if (null != conn)
				conn.close();
		}
		return json;
	}
	
	@ResponseBody
	@ApiOperation(value = "预警统计图")
	@RequestMapping(value = "/getWarning", method = RequestMethod.GET)
	public JSONObject getWarning(
			@RequestParam(value = "Product", defaultValue = "") String Product,
			@RequestParam(value = "Date", defaultValue = "") String Date,
			@RequestParam(value = "Warning", defaultValue = "") String Warning,
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		String sql = " where 1=1 " ;
		if (!StringUtils.isBlank(Product)) {
			String deptsql = DBUtil.setSql(Product);
			sql = sql + " and chanpin_name in ( " + deptsql + " )";
		}
		if (!StringUtils.isBlank(Date)) {
			sql = sql + " and in_date = ? ";
		}
		//预警次数
		String cssql = " select sum(one_count) one_count ,sum(two_count) two_count,sum(three_count) three_count from gxyth_yujing " + sql;
		//预警趋势图
		String qssql = " select in_date_qs,sum(one_count) one_count ,sum(two_count) two_count,sum(three_count) three_count from gxyth_yujing_qs " + sql + " group by in_date_qs ";
		//预警节点柱状图
		String jdsql = " select process   ,sum(process_count)  ,sum(process_count)/sum(all_count)   from gxyth_hz " + sql;		
		
		if (!StringUtils.isBlank(Warning)) {
			jdsql = jdsql + " and  warning_stage = ? ";
		}
		jdsql = jdsql + " group by  process ";
		try {
			List<Map<String, Object>> lists1 = DBUtil.queryMapList(conn, cssql, Product, Date );
			String string1 = JSON.toJSONString(lists1);
			result.put("yjcs", string1);

			List<Map<String, Object>> lists2 = DBUtil.queryMapList(conn, qssql, Product, Date );
			String string2 = JSON.toJSONString(lists2);
			result.put("yjqs", string2);
			
			List<Map<String, Object>> lists3 = DBUtil.queryMapList(conn, jdsql, Product, Date ,Warning);
			String string3 = JSON.toJSONString(lists3);
			result.put("yjjd", string3);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != conn)
				conn.close();
		}
		return result;
	}
          
	
	//到期预警明细
	@ApiOperation(value = "到期预警明细")
    @RequestMapping(value = "/warningDetailed",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject warningDetailed(@RequestBody ProductionSmInfo info ,HttpServletResponse response)throws Exception {
		JSONObject json = new JSONObject();
		Connection conn = DriverManager.getConnection(jdbcConfig.getPsmUrl(), jdbcConfig.getPsmUserName(), jdbcConfig.getPsmPassword());
		String sql = " where " ;
		try{
			if(info.getPage() == null || info.getPageSize() == null){
				json.put("status", 1);
				json.put("msg", "分页参数不能为空。");	
				return json;
			}
			if(!StringUtils.isBlank(info.getCountDate())){
				sql = sql+ " a.in_date = ?  ";
			}
			if(!StringUtils.isBlank(info.getProducts())){
				String DBsql = DBUtil.setSql(info.getProducts());
				sql = sql+ " and a.CHANPIN_NAME in ( "+DBsql+" )";
			}
			if(!StringUtils.isBlank(info.getType())){
				sql = sql+ " and  a."+info.getType()+" is not null";
			}
			if(!StringUtils.isBlank(info.getIsExprot()) &&  info.getIsExprot().equals("1")){
				String dbsql = " select a.FD_CAIGOUSHENQING,a.FD_SHIYEBU,a.FD_WULIAOBIANHAO,a.FD_WULIAOMIAOSHU,a.FD_NAME,a.new_stage,a.new_jihua,a.daoqi from  gxyth_daoqi_skip a " + sql ;
		        List<Map<String, Object>> lists = DBUtil.queryMapList(conn, dbsql , info.getCountDate(), info.getProducts() );
		        Boolean flage = ReturnExcel.printProductionExcel(response,lists);
		        if(flage){
					json.put("status", 0);
					json.put("msg", "导出成功!");	
		        }else{
					json.put("status", 1);
					json.put("msg", "导出失败!");		
		        }
			}else{
				String dbsql = " select a.FD_CAIGOUSHENQING,a.FD_SHIYEBU,a.FD_WULIAOBIANHAO,a.FD_WULIAOMIAOSHU,a.FD_NAME,a.new_stage,a.new_jihua,a.daoqi from gxyth_daoqi_skip a " + sql + " Limit ? ,? " ;
				String countSql = " select count(*) as count from gxyth_daoqi_skip a" + sql ;
				List<Map<String, Object>> lists = DBUtil.queryMapListByPagesize(conn, dbsql , info.getPage() ,info.getPageSize(), info.getCountDate(), info.getProducts());
		        List<Map<String, Object>> countList = DBUtil.queryMapList(conn, countSql , info.getCountDate(), info.getProducts() );
		        json.put("list", lists);
				json.put("count", countList.get(0).get("count"));
				json.put("status", 0);
				json.put("msg", "查询成功!");	
			}
		} finally {
			if (null != conn)
				conn.close();
		}
		return json;
	}

}
