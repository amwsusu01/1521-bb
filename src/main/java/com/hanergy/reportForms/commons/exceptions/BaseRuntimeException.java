package com.hanergy.reportForms.commons.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public abstract class BaseRuntimeException extends RuntimeException {

    /**
     * 异常信息状态码
     */
    private int status = 700;

    public BaseRuntimeException() {
        super();
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

    protected int getStatus() {
        return status;
    }

    protected void setStatus(int status) {

        if (status < 700 || status > 799) {
//            throw new Exception("status 范围在 700 ~ 799之间!");
        }

        this.status = status;
    }

    /**
     * 发生异常时间
     *
     * @return
     */
    protected long getTimestamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 返回异常类型
     *
     * @return
     */
    protected String getExceptionType() {
        return this.getClass().toString();
    }

    /**
     * 明确指定自己的异常用途
     *
     * @return
     */
    protected abstract String getError();

    /**
     * 异常信息返回的数据统一格式
     *
     * @return
     */
    public ExceptionResponseBody resultJson() {

        ExceptionResponseBody responseBody = new ExceptionResponseBody();
        responseBody.setTimestamp(this.getTimestamp());
        responseBody.setStatus(this.getStatus());
        responseBody.setError(this.getError());
        responseBody.setException(this.getExceptionType());
        responseBody.setMsg(this.getMessage());
        responseBody.setPath(String.valueOf(this.getStackTrace()[0]));

        return responseBody;
    }

}