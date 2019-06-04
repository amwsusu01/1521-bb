package com.hanergy.reportForms.commons.utils;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtil {

	public static Object praseJsonToObject(Class<?> targerClass, String json) {
		return   JSONObject.parseArray(json, targerClass);
	}
	
	public static String toJson(Object o) {
        return JSONObject.toJSONString(o);
    }
	
	public static void printToJson(HttpServletRequest request,HttpServletResponse response,Object object) throws IOException{
		response.setContentType("text/html");
    	response.setCharacterEncoding("UTF-8");
    	response.getWriter().print(toJson(object));
    }
	/**
	 * 
	 * @param response 
	 * @param object       转换为json后的对象
	 * @param cookieName   
	 * @param cookieValue
	 * @param expireTime
	 * @throws IOException
	 */
	public static void printCookieObject(HttpServletResponse response,Object object,
			String cookieName,String cookieValue,int expireTime) throws IOException{
		response = CookiesOperation.addCookie(response, cookieName, cookieValue, expireTime);
		response.setContentType("text/html");
    	response.setCharacterEncoding("UTF-8");
    	response.getWriter().print(object);
    }
	
	
}
