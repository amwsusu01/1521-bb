package com.hanergy.reportForms.commons.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类
 *
 * @author DuRonghong
 * @Date 2018-09-12
 */
public class DateUtils {

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    public static String PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static String PATTER_YYYYMM = "yyyy-MM";
    public static String PATTERN_YYYYMMDD = "yyyy-MM-dd";

    /**
     * 控制SimpleDateFormat对象生成
     *
     * @return
     */

    private static SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat();
            threadLocal.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static String defaultDateToString(Date date) {
        return dateToString(date, PATTERN_YYYYMMDDHHMMSS);
    }

    public static String dateToStringYYYYMMDD(Date date) {
        return dateToString(date, PATTERN_YYYYMMDD);
    }


    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }


    public static Date defaultStringToDate(String dateStr) {
        return stringToDate(dateStr, PATTERN_YYYYMMDDHHMMSS);
    }

    public static Date stringToDateYYYYMM(String dateStr) {
        return stringToDate(dateStr, PATTER_YYYYMM);
    }

    public static Date stringToDateYYYYMMDD(String dateStr) {
        return stringToDate(dateStr, PATTERN_YYYYMMDD);
    }
    public static Date stringToDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat format = getSimpleDateFormat();
            format.applyPattern(pattern.replaceAll("-",""));
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date stringToDate2(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat format = getSimpleDateFormat();
            format.applyPattern(pattern);
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 默认以当前时间为起点进行比较
     *
     * @param date
     * @return
     */
    public static Integer defaultCompareTwoDate(Date date) {
        return compareTwoDate(new Date(), date);
    }

    /**
     * 可正可负,计算两个日期相差的月数
     *
     * @param dateFirst
     * @param dateSecond
     * @return
     */
    public static Integer compareTwoDate(Date dateFirst, Date dateSecond) {
        if (dateFirst == null || dateSecond == null) {
            return null;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(dateFirst);
        YearMonth first = YearMonth.of(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);

        c1.setTime(dateSecond);
        YearMonth second = YearMonth.of(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
        return first.compareTo(second);
    }

    public static String getYesterdayByFormat(String format) {
        return LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前月时间段
     *
     * @return
     */
    public static Date[] formatCurrentAllMonth() {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.DAY_OF_MONTH, startCal.getMinimum(Calendar.DATE));
        startCal.set(Calendar.HOUR_OF_DAY, startCal.getMinimum(Calendar.HOUR_OF_DAY));
        startCal.set(Calendar.MINUTE, startCal.getMinimum(Calendar.MINUTE));
        startCal.set(Calendar.SECOND, startCal.getMinimum(Calendar.SECOND));
        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DATE));
        endCal.set(Calendar.HOUR_OF_DAY, startCal.getActualMaximum(Calendar.HOUR_OF_DAY));
        endCal.set(Calendar.MINUTE, startCal.getActualMaximum(Calendar.MINUTE));
        endCal.set(Calendar.SECOND, startCal.getActualMaximum(Calendar.SECOND));
        return new Date[]{startCal.getTime(), endCal.getTime()};

    }

    // 获得某天最大时间 2017-10-15 23:59:59
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        //Date endOfDay = getEndOfDay(new Date());
        Date date=new Date();
        System.out.println(date.getTime());
    }
}
