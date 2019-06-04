package com.hanergy.reportForms.commons.enums;

/**
 * @Description TODO 删除标志
 * @Author DURONGHONG
 * @DATE 2018/9/28 17:31
 * @Version 1.0
 **/
public enum EnumDeltag {

    NORMAL(0, "否"),
    DELETE(1, "删除");

    private Integer code;
    private String msg;

    EnumDeltag(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumDeltag getEnum(Integer code) {
        for (EnumDeltag deltag : EnumDeltag.values()) {
            if (code.equals(deltag.getCode())) {
                return deltag;
            }
        }
        return null;
    }

}
