package com.hanergy.reportForms.commons.enums;

public enum  OperationConstent {
    //操作类型 （1：新增，2：修改，3：删除，4:查看，5：登陆，6：导出）
    CREATE(1,"新增"),UPDATE(2,"修改"),DELETE(3,"删除"),VIEW(4,"查看"),LOGIN(5,"登陆"),EXPORT(6,"导出");
    private Integer id;
    private String type;
    private OperationConstent(Integer id,String type){
        this.id=id;
        this.type=type;
    }
    public static String getTypeById(Integer id){
        for (OperationConstent constent:OperationConstent.values()){
            if (constent.getId()==id){
                return constent.getType();
            }
        }
        return null;
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
