package com.hanergy.reportForms.commons.enums;

/**
 * 角色
 */
public enum EnumRoleType {

    SUPER_ADMIN(1L, "超级管理员"), 
    CADIDATE(2L, "候选人"),
    RECRUIT(3L, "招聘负责人"),
    SYSTEM_MANAGER(4L, "系统管理员"), 
    BACKGROUND(5L, "背调负责人"), 
    AGENCY(6L, "中介公司"),
    BUSSINESS(7L, "业务负责人"),
    HRVP(8L, "HRVP"),
    CONFIDENTIALITY(9L, "保密办");

    private Long code;
    private String name;

    EnumRoleType(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static EnumRoleType getEnum(Long code) {
        for (EnumRoleType type : EnumRoleType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
