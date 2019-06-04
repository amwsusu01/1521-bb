package com.hanergy.reportForms.commons.enums;

/**
 * 证件类型
 */
public enum EnumDocumentType {
    OTHERS(0,"其他"),IDCARD(1,"身份证"),RESIDENCE(2,"居住证"),
    VISA(3,"签证"),PASSPORT(4,"签证"),HUKOUBOOK(5,"户口本"),
    MILITARYCARD(6,"军人证"),LEAGUECARD(7,"团员证"),PARTYMEMBER(8,"党员证"),
    HONGKONGANDMACAOPASS(9,"港澳通行证");

    private Integer code;
    private String msg;

    EnumDocumentType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumDocumentType getEnum(Integer code) {
        for (EnumDocumentType type : EnumDocumentType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return OTHERS;
    }
}
