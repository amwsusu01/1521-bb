package com.hanergy.reportForms.commons.exceptions;

/**
 * @ClassName ExceptionResponseBody
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/13 11:57
 * @Version 1.0
 **/
public class ExceptionResponseBody {

    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private String msg;
    private String path;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
