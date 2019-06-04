package com.hanergy.reportForms.commons.enums;

/**
 * @ClassName EnumAgencyType
 * @Description TODO 中介类型
 * @Author DURONGHONG
 * @DATE 2018/9/28 17:23
 * @Version 1.0
 **/
public enum EnumAgencyType {
	/**
	 * 自主背调
	 */
    OWNER_BACAKGROUND(2, "自主背调"), 
    /**
     * 中介背调
     */
    AGENCY_BACKGROUND(3, "中介背调");

    private int code;
    private String msg;

    EnumAgencyType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumAgencyType getEnum(int code) {
        for (EnumAgencyType type : EnumAgencyType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
