package com.hanergy.reportForms.commons.utils;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hanergy.reportForms.commons.enums.ResultEnum;

import java.util.Arrays;

/**
 * @ClassName ResResult
 * @Description TODO 接口返回
 * @Author DURONGHONG
 * @DATE 2018/9/13 8:34
 * @Version 1.0
 **/
public class ResResult extends BaseResult {

    private static ThreadLocal<JSONObject> threadLocal = new ThreadLocal<>();


    public static JSONObject getResResult() {
        JSONObject resResult = threadLocal.get();
        if (resResult == null) {
            resResult = new JSONObject();
            threadLocal.set(resResult);
        }
        return resResult;
    }


    public static JSONObject getResResult(String msg, int status, Object data) {
        JSONObject resResult = getResResult();
        resResult.put("status", status);
        resResult.put("msg", msg);
        resResult.put("data", data == null ? new JSONObject() : data);
        return resResult;
    }

    public static JSONObject getResResult(String msg, int status, Object data, String code) {
        JSONObject resResult = getResResult();
        resResult.put("status", status);
        resResult.put("msg", msg);
        resResult.put("data", data == null ? new JSONObject() : data);
        resResult.put("code", code);
        return resResult;
    }

    public static JSONObject getResResult(int status, Object data, String... msg) {
        JSONObject resResult = getResResult();
        resResult.put("status", status);
        resResult.put("msg", msg != null && msg.length > 0 ? Arrays.toString(msg) : "");
        resResult.put("data", data == null ? new JSONObject() : data);
        return resResult;
    }


    public static JSONObject success(Object data, String msg) {
        return getResResult(msg, 1, data);
    }

    public static JSONObject success(String msg) {
        return getResResult(msg, 1, null);
    }

    public static JSONObject failed() {
        return getResResult("请求失败!", 0, null);
    }

    public static JSONObject success() {
        return getResResult("请求成功!", 1, null);
    }

    public static JSONObject failed(String msg) {
        return getResResult(msg, 0, null);
    }

    public static JSONObject failed(Object data, String msg, int status) {
        return getResResult(msg, status, data);
    }

    public static JSONObject failed(Object data, String msg) {
        return failed(data, msg, 0);
    }

    public static JSONObject success(IPage<?> page) {
        JSONObject resResult = getResResult();
        resResult.put("status", 1);
        resResult.put("msg", "请求成功!");
        resResult.put("data", page.getRecords());
        resResult.put("total", page.getTotal());
        resResult.put("totalPages", page.getPages());
        resResult.put("current", page.getCurrent());
        resResult.put("pageSize", page.getSize());
        return resResult;
    }

    public static JSONObject getResultEnum(ResultEnum resultEnum) {
        return getResResult(resultEnum.msg, resultEnum.code, null);

    }

    public static JSONObject getResultEnumData(ResultEnum resultEnum, Object object) {
        return getResResult(resultEnum.msg, resultEnum.code, object);

    }
}
