package com.hanergy.reportForms.commons.utils;

import java.util.Random;

/**
 * @ClassName ProjectUtils
 * @Description TODO 项目工具类
 * @Author DURONGHONG
 * @DATE 2018/10/22 14:16
 * @Version 1.0
 **/
public class ProjectUtils {

    private static String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 生成指定位数的随机字符串
     *
     * @param number 指定生成的字符串位数
     * @return
     */
    public static String buildRandomCode(Integer number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number; i++) {
            Random random = new Random();
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String randomCode = buildRandomCode(6);
        System.out.println(randomCode);
    }
}
