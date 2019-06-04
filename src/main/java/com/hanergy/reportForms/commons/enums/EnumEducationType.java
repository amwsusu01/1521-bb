package com.hanergy.reportForms.commons.enums;

/**
 * 学历性质（教育类型）：1全日制   2非全日制   0其它
 */
public enum EnumEducationType {
    OTHER(0, "其他"),
    /**
     * 全日制
     */
    FULLTIME(1, "全日制"),
    /**
     * 非全日制
     */
    PARTTIME(2, "非全日制");

    private Integer code;
    private String msg;

    EnumEducationType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumEducationType getEnum(Integer code) {
        for (EnumEducationType type : EnumEducationType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
