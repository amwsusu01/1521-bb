/**
 * 
 * @FileName: StrUtil
 * @Description: TODO(字符串处理类)
 *
 * Copyright(c) 2012, HHKX Technology Co.,Ltd.
 * 本程序的著作权归北京合华科讯科技有限公司所有。
 * Version:1.0
 *
 * Author: hu.xiaole
 * Date: 2012-12-27 上午04:04:25
 */
package com.hanergy.reportForms.commons.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName:StrUtil
 * @Description:TODO(这里用一句话描述这个类的作用)  
 *
 */
public class StrUtil {

	/** 
	 * @MethodName:procNullToStr 
	 * @ReturnType:String
	 * @Description:将值为null的对象为空字符串，否则调用该对象的toString()方法
	 * 
	 * @param orgObj
	 * @return
	 */	
	public static String procNullToStr(Object orgObj) {
		String target = null;
		if (orgObj == null) {
			target = "";
		} else {
			target = orgObj.toString();
		}
		return target;
	}

	/** 
	 * @MethodName:str_replace 
	 * @ReturnType:String
	 * @Description:字符串替换函数
	 * 
	 * @param con
	 * @param tag
	 * @param rep
	 * @return
	 */	
	public static String str_replace(String con, String tag, String rep) {
		int j = 0;
		int i = 0;
		String RETU = "";
		String temp = con;
		int tagc = tag.length();
		while (i < con.length()) {
			if (con.substring(i).startsWith(tag)) {
				temp = con.substring(j, i) + rep;
				RETU += temp;
				i += tagc;
				j = i;
			} else {
				i += 1;
			}
		}
		RETU += con.substring(j);
		return RETU;
	}
	
	/** 
	 * @MethodName:replaceHH 
	 * @ReturnType:String
	 * @Description:TODO(去除换行及空格)  
	 * 
	 * @param str
	 * @return
	 */	
	public static String replaceHH(String str){
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		String after = m.replaceAll("");
		return after;
	}
	
	/** 
	 * @MethodName:replaceKG 
	 * @ReturnType:String
	 * @Description:TODO(去空格，包括中文全角，半角等所有空格)  
	 * 
	 * @param str
	 * @return
	 */	
	public static String replaceKG(String str){
		Pattern pattern = Pattern.compile("[\\s\\p{Zs}]+");
		Matcher re = pattern.matcher(str);
		str=re.replaceAll("");
		return str;
	}
	
	/**
	 * @MethodName:isEmpty
	 * @ReturnType: boolean
	 * @Description:判断字符串是否非空
	 *
	 * @param obj 参数对象
	 * @return true:是空字符串,false:不是空字符串
	 */
	public static boolean isEmpty(Object obj) {
		boolean blnRtn= false;
		String strObj = procNullToStr(obj);
		// 输入对象为空
		if ("".equals(strObj.trim())) {
			blnRtn = true;
		}
		return blnRtn;
	}
	
	public  static Integer  ObjectConvertToString(Object obj){
		if(obj == null ){
			return 0;
		}else if(obj  instanceof java.lang.String){
			if(isNumeric(String.valueOf(obj))){
				return Integer.valueOf(String.valueOf(obj));
			}else{
				return 0;
			}
		}else if(obj  instanceof java.lang.Integer ){
			return (Integer) obj;
		} 
		return 0;
		
	}
	public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
	
	public static boolean longIsNotEmpty(Long obj){
		if(obj != null && obj > 0)
			return true;
		return false;
	}

	public static void main(String[] args) {
		String a = " 鏇存柊鎴愬姛鐩存帴杩斿洖锛屽け璐ユ墽琛屾彃鍏ラ�昏緫";
		try {
			System.out.println(new String(a.getBytes("gbk"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
