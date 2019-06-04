package com.hanergy.reportForms.commons.enums;


public enum ResultEnum {
    
     /**
      *  参数不合法
      */
    PARAM_INVALID(201, "参数不合法"), 
    /**
     * 当前模板已被绑定，无法修改
     */
    TEMPLATE_BINDED(1002, "当前模板已被绑定，无法修改"),  
    /**
     * 当前为终版单仍进行修改的非法行为
     */
    UPLOAD_ELLIGAL(1003,"上传报告不合法！！"), 
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(1004,"用户未登录"), 
    /**
     * 用户已在其他地方登陆
     */
    USER_LOGINING_OTHER_SIDE(1005,"用户已在其他地方登陆"),
    /**
     * 不允许对终板报告进行编辑
     */
    CHECK_INFO_ILLEGAL_SUBMIT(1006,"不允许对终板报告进行编辑");
     
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer code;
    public String msg;
    
    public Integer getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
}
