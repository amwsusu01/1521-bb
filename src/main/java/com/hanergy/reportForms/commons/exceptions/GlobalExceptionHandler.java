package com.hanergy.reportForms.commons.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName GlobalException
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/13 11:50
 * @Version 1.0
 **/
//@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler  {



    @Autowired
    private ExceptionCallback exceptionCallbackImpl;

    /**
     * 拦截捕捉 系统级异常
     *
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String ErrorHandler(Exception exception) {

        return exception.getMessage();
    }

    /**
     * 拦截捕捉 自定义异常 DemoRuntimeException.class
     *
     * @param baseException
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_EXTENDED)
    @ExceptionHandler(value = BaseRuntimeException.class)
    public ExceptionResponseBody myErrorHandler(BaseRuntimeException baseException) {

        exceptionCallbackImpl.callback(baseException.resultJson());
        return baseException.resultJson();
    }

}
