package com.hanergy.reportForms.commons.enums;

public enum EnumBeSelectedSelfCheckStatus {

    ONLINE(211, "联机调查"), WORKCHECK(212, "工作履历调查");


    private Integer code;
    private String msg;

    EnumBeSelectedSelfCheckStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumBeSelectedSelfCheckStatus getEnum(Integer code) {
        for (EnumBeSelectedSelfCheckStatus status : EnumBeSelectedSelfCheckStatus.values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
