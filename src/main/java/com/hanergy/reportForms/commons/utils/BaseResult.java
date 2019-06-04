package com.hanergy.reportForms.commons.utils;

/**
 * @ClassName BaseResult
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/25 14:56
 * @Version 1.0
 **/
public class BaseResult {

    //    public static String SUCCESS = "请求成功!";
//    public static String FAILED = "请求失败!";
//    public static int SUCCESS_STATUS = 1;

    // 接口调用成功(1:成功 0:失败)
    public Integer status = 1;
    // 业务执行成功(1:成功 0：失败)
    public String code;
    //信息
    public String[] msg;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }
}
