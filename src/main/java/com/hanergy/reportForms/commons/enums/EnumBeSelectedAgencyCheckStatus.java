package com.hanergy.reportForms.commons.enums;

/**
 * 背调状态
 *
 * @author fangshuai
 * @version 1.0.0
 * @time 2018年10月11日
 */
public enum EnumBeSelectedAgencyCheckStatus {
    /**
     * 待委托
     */
    BE_ENSTRUSTMENT(221, "待委托"),
    /**
     * 调查中
     */
    CHECKING(222, "调查中");


    private Integer code;
    private String msg;


    EnumBeSelectedAgencyCheckStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumBeSelectedAgencyCheckStatus getEnum(Integer code) {
        for (EnumBeSelectedAgencyCheckStatus status : EnumBeSelectedAgencyCheckStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }


}
