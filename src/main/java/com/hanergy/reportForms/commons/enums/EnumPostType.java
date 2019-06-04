package com.hanergy.reportForms.commons.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 岗位类型
 */
public enum EnumPostType {
	/**
	 * 资金相关
	 */
    FINANCE(1, "资金相关"),
    /**
     * 非资金相关
     */
    NOT_FINANCE(0, "非资金相关"), 
    /**
     * 销售岗
     */
    SALE(2, "销售岗"),
    /**
     * 中睿岗位
     */
    ZHONGRUI(3, "中睿岗位"),
    /**
     * 财富(拓丰)岗位
     */
    CAIFU_TUOFENG(4, "财富(拓丰)岗位");

    private Integer code;
    private String msg;

    EnumPostType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumPostType getEnum(Integer code) {
        for (EnumPostType type : EnumPostType.values()) {
            if (code.equals(type.getCode())) {
                return type;
            }
        }
        return null;
    }
    
	  public static Map<Integer,String> getMap (){
	    	Map<Integer,String> map = new HashMap<Integer,String>();
	    	 for (EnumPostType type : EnumPostType.values()) {
	    		 map.put(type.code, type.msg);
	         }
	    	return map;
	    	
	    }

}
