package com.hanergy.reportForms.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 作废标志
 */
public enum EnumCancel {

    YES(1, "是"), NO(0, "否");

    private Integer code;
    private String msg;

    EnumCancel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumCancel getEnum(Integer code) {
        for (EnumCancel cancel : EnumCancel.values()) {
            if (code.equals(cancel.getCode())) {
                return cancel;
            }
        }
        return null;
    }
    
	  public static Map<Integer,String> getMap (){
	    	Map<Integer,String> map = new HashMap<Integer,String>();
	    	 for (EnumCancel type : EnumCancel.values()) {
	    		 map.put(type.code, type.msg);
	         }
	    	return map;
	    	
	    }
}
