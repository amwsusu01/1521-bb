package com.hanergy.reportForms.commons.enums;
/**
 * 教育程度
 * @author fangshuai
 * @version 1.0.0
 * @time 2018年10月11日
 */
public enum EnumEducation {
	/**
	 * 大专
	 */
    JUNIOR(1, "大专"),
    /**
     * 本科
     */
    UNDERGRADUATE(2, "本科"),
    /**
     * 硕士
     */
    MASTER(3, "硕士"),
    /**
     * 博士
     */
    DOCTOR(4, "博士"),
    /**
     * 其他
     */
    OTHER(0, "其他");

    private Integer code;
    private String msg;

    EnumEducation(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumEducation getEnum(Integer code) {
        for (EnumEducation education : EnumEducation.values()) {
            if (education.getCode().equals(code)) {
                return education;
            }
        }
        return null;
    }
    
    public static EnumEducation getCodeBymsg(String msg) {
        for (EnumEducation education : EnumEducation.values()) {
            if (education.getMsg().equals(msg)) {
                return education;
            }
        }
        return null;
    }
}
