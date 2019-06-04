package com.hanergy.reportForms.commons.enums;

public enum EnumAgency {
    SELF(1111111111111111L, "自主调查"),
    ZHILIAO(5378479249961034L, "知了背调"),
    SHOUYOU(5378479249961035L, "首优"),
    XUANDU(5378479249961036L, "轩渡"),
    KAILAIDE(5679936006983971L, "凯莱德"),
    TAIHEDINGXIN(5679936007632973L, "太和鼎信");

    private Long id;

    private String name;

    EnumAgency(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EnumAgency getEnum(Long id) {
        for (EnumAgency type : EnumAgency.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }

}
