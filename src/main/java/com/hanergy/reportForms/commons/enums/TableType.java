package com.hanergy.reportForms.commons.enums;

public enum TableType {
    cgscDetail(1,"超过4次(含)未请假未提报明细表"),
    tsxywDetail(2,"提报月平均条数小于5明细表"),
    zsxywDetail(3,"提报月平均字数小于5明细表"),
    jdzqDetail(4,"9点之前提报数据明细表"),
    sedzqDetail(5,"12点之前提报数据明细表"),
    cglhzDetail(6,"提报内容重复超6次(含)汇总表"),
    cglmxDetail(7,"提报内容重复超6次（含）明细表"),
    issueDetail(8,"部门问题明细表"),
    introspectionDetail(9,"部门反省明细表");
    private Integer id;
    private String type;
    private TableType(Integer id,String type){
        this.id=id;
        this.type=type;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
