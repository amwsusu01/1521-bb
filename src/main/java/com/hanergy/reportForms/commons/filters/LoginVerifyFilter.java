package com.hanergy.reportForms.commons.filters;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.enums.ResultEnum;
import com.hanergy.reportForms.commons.utils.CookiesOperation;
import com.hanergy.reportForms.commons.utils.JsonUtil;
import com.hanergy.reportForms.commons.utils.LZString;
import com.hanergy.reportForms.commons.utils.ResResult;
import com.hanergy.reportForms.commons.utils.StrUtil;
import com.hanergy.reportForms.commons.utils.cont.DefaultConst;
import com.hanergy.reportForms.dto.user.UserDto;
import com.hanergy.reportForms.service.IUserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebFilter(filterName="longVerifyFilter",urlPatterns="/*")
//@Order(3)
public class LoginVerifyFilter implements Filter {


	@Autowired
	private IUserService userService;
	private static Logger logger = LogManager.getLogger(LoginVerifyFilter.class);
	private static List<String>  needLoginPageArr =  new  ArrayList<String>();
	/**
	 * 需要验证登陆的url
	 */
	private String needLoginList;
	/**
	 * SSO Server返回的token参数名称
	 */
	private String tokenParamName = DefaultConst.LOGIN_COOKIE;
	/**
	 * 用户登陆跳转地址
	 */
	private String userLoginUrl ;

	private String loginPath = "/api/v1/user/login";

	private String oaAuthLogin="/auth/login";

	private String updatePasswordPath = "/api/v1/user/updatePassword";

	private String swaagerPath = "swagger-ui.html";

	public String getUserLoginUrl() {
		return userLoginUrl;
	}

	public void setUserLoginUrl(String userLoginUrl) {
		this.userLoginUrl = userLoginUrl;
	}

	public String getNeedLoginPages() {
		return needLoginList;
	}

	public void setNeedLoginPages(String needLoginList) {
		this.needLoginList = needLoginList;
	}

	public String getTokenParamName() {
		return tokenParamName;
	}

	public void setTokenParamName(String tokenParamName) {
		this.tokenParamName = tokenParamName;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		needLoginList = filterConfig.getInitParameter("needLoginList");
		needLoginPageArr = this.changToList(needLoginList);
	}



	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String visitIp = this.getVisitIpAddr(request);
		logger.info("request.getRequestURI() :"+ request.getRequestURI() +  "    ,visitIp:"+visitIp);

		Cookie LoginCookie = CookiesOperation.getCookieByName(request, tokenParamName);


		if(request.getRequestURI().indexOf(this.loginPath) > -1 ){
			UserDto user  =userService.verifyLogin(request, tokenParamName);
			if(user == null){
				chain.doFilter(request, response);
				return ;
			}else{
				JsonUtil.printToJson(request, response,  ResResult.getResultEnum(ResultEnum.USER_LOGINING_OTHER_SIDE));
				return ;
			}
		}
		if(request.getRequestURI().contains(oaAuthLogin)){
			chain.doFilter(request, response);
		}

		if(request.getRequestURI().contains(this.updatePasswordPath) || request.getRequestURI().contains(this.swaagerPath)
		|| request.getRequestURI().contains("webjars")||request.getRequestURI().contains("swagger")
        || request.getRequestURI().contains("v2/api-docs")){
			chain.doFilter(request, response);
			return ;
		}

		if(LoginCookie == null ||StrUtil.isEmpty(LoginCookie.getName())){
			JsonUtil.printToJson(request, response,  ResResult.getResultEnum(ResultEnum.USER_NOT_LOGIN));
			return;

		}else{
			logger.info("loginVerifyFilter  LoginCookie:" +  LoginCookie == null ? "":JSONObject.toJSON(LoginCookie)  );
			UserDto user  =userService.verifyLogin(request, tokenParamName);
			if(user == null ){
				JsonUtil.printToJson(request, response,  ResResult.getResultEnum(ResultEnum.USER_NOT_LOGIN));
				return ;
			}else{
				  String uriComponent = LZString.compressToEncodedURIComponent(JSONObject.toJSONString(user));
		          JSONObject success = ResResult.success(uriComponent, "登陆成功!");
				request.setAttribute("userInfo", success);
				chain.doFilter(request, response);
				return;
			}
		}


	}
	public void destroy() {
	}




	private List<String> changToList(String  str){
		if(StrUtil.isEmpty(str)){
			return null;
		}
		return Lists.newArrayList(str.split(","));

	}
	public String getVisitIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
}
