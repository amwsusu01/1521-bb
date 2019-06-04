package com.hanergy.reportForms.commons.enums;

/**
 * 背调核实来源
 */
public enum EnumVerifySource {
	/**
	 * 候选人
	 */
    CANDIDATE(1,"候选人"),
    /**
     * 人力
     */
    SF(2,"人力"),
    /**
     * 中介
     */
    AGENCY(3,"中介");

    private Integer code;
    private String msg;

    EnumVerifySource(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumVerifySource getEnum(Integer code){
        for(EnumVerifySource source:EnumVerifySource.values()){
            if(source.getCode().equals(code)){
                return source;
            }
        }
        return null;
    }
}
