package com.hanergy.reportForms.commons.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 本地日志参考类
 * @author Administrator
 *
 */
public class LogUtils {
 
 
    /**
     * 获取业务日志logger
     *
     * @return
     */
    public static Logger getBussinessLogger() {
        return LogManager.getLogger(LogEnum.BUSSINESS.getCategory());
    }
 
    /**
     * 获取平台日志logger
     *
     * @return
     */
    public static Logger getPlatformLogger() {
        return LogManager.getLogger(LogEnum.PLATFORM.getCategory());
    }
 
    /**
     * 获取数据库日志logger
     *
     * @return
     */
    public static Logger getDBLogger() {
        return LogManager.getLogger(LogEnum.DB.getCategory());
    }
 
 
    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LogManager.getLogger(LogEnum.EXCEPTION.getCategory());
    }
 
 
}