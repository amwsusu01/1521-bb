package com.hanergy.reportForms.commons.enums;

public enum EnumCheckStaticsType {
	
    ZLTX(1, "ZLTX");

    private Integer id;

    private String name;

    EnumCheckStaticsType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EnumCheckStaticsType getEnum(Long id) {
        for (EnumCheckStaticsType type : EnumCheckStaticsType.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }

}
