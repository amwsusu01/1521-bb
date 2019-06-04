package com.hanergy.reportForms.commons.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ExceptionCallbackImpl implements ExceptionCallback {

    @Override
    public void callback(ExceptionResponseBody responseBody) {
        System.out.println("Mysql 数据库插入");
    }
}