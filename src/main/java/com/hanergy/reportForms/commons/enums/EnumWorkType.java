package com.hanergy.reportForms.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工类型
 */
public enum EnumWorkType{
	/**
	 * 正式员工
	 */
    FULL_TIME_WORKER(1, "正式员工"),
    /**
     * 应届
     */
    INORDER(2, "应届"),
    /**
     * 实习
     */
    PRACTICE_WORKER(3, "实习");

    private Integer code;
    private String msg;

    EnumWorkType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumWorkType getEnum(Integer code) {
        for (EnumWorkType type : EnumWorkType.values()) {
            if (code.equals(type.getCode())) {
                return type;
            }
        }
        return null;
    }
	  public static Map<Integer,String> getMap (){
	    	Map<Integer,String> map = new HashMap<Integer,String>();
	    	 for (EnumWorkType type : EnumWorkType.values()) {
	    		 map.put(type.code, type.msg);
	         }
	    	return map;
	    	
	    }


}
