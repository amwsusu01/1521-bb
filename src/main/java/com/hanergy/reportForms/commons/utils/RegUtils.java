package com.hanergy.reportForms.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtils {

    private static String EMAIL_PATTERN = "";
    private static String MOBILE_PATTERN = "^1[34578]\\d{9}$";

    public static Boolean isMobile(String mobile){
        Pattern pattern = Pattern.compile(MOBILE_PATTERN);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static Boolean isEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*身份证校验*/
    public static boolean identificationCardVerify(String cardId){
        if (cardId.length() == 15 || cardId.length() == 18) {
            if (!cardCodeVerifySimple(cardId)) {
                //error.put("cardId", "15位或18位身份证号码不正确");
                return false;
            } else {
                if (cardId.length() == 18 && !cardCodeVerify(cardId)) {
                    //error.put("cardId", "18位身份证号码不符合国家规范");
                    return false;
                }
            }
        } else {
            return false;
            //error.put("cardId", "身份证号码长度必须等于15或18位");
        }
        return true;
    }
    private static boolean cardCodeVerifySimple(String cardcode) {
        //第一代身份证正则表达式(15位)
        String isIDCard1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        //第二代身份证正则表达式(18位)
        String isIDCard2 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";

        //验证身份证
        if (cardcode.matches(isIDCard1) || cardcode.matches(isIDCard2)) {
            return true;
        }
        return false;
    }
    private static boolean cardCodeVerify(String cardcode) {
        int i = 0;
        String r = "error";
        String lastnumber = "";
        i += Integer.parseInt(cardcode.substring(0, 1)) * 7;
        i += Integer.parseInt(cardcode.substring(1, 2)) * 9;
        i += Integer.parseInt(cardcode.substring(2, 3)) * 10;
        i += Integer.parseInt(cardcode.substring(3, 4)) * 5;
        i += Integer.parseInt(cardcode.substring(4, 5)) * 8;
        i += Integer.parseInt(cardcode.substring(5, 6)) * 4;
        i += Integer.parseInt(cardcode.substring(6, 7)) * 2;
        i += Integer.parseInt(cardcode.substring(7, 8)) * 1;
        i += Integer.parseInt(cardcode.substring(8, 9)) * 6;
        i += Integer.parseInt(cardcode.substring(9, 10)) * 3;
        i += Integer.parseInt(cardcode.substring(10,11)) * 7;
        i += Integer.parseInt(cardcode.substring(11,12)) * 9;
        i += Integer.parseInt(cardcode.substring(12,13)) * 10;
        i += Integer.parseInt(cardcode.substring(13,14)) * 5;
        i += Integer.parseInt(cardcode.substring(14,15)) * 8;
        i += Integer.parseInt(cardcode.substring(15,16)) * 4;
        i += Integer.parseInt(cardcode.substring(16,17)) * 2;
        i = i % 11;
        lastnumber =cardcode.substring(17,18);
        if (i == 0) {
            r = "1";
        }
        if (i == 1) {
            r = "0";
        }
        if (i == 2) {
            r = "x";
        }
        if (i == 3) {
            r = "9";
        }
        if (i == 4) {
            r = "8";
        }
        if (i == 5) {
            r = "7";
        }
        if (i == 6) {
            r = "6";
        }
        if (i == 7) {
            r = "5";
        }
        if (i == 8) {
            r = "4";
        }
        if (i == 9) {
            r = "3";
        }
        if (i == 10) {
            r = "2";
        }
        if (r.equals(lastnumber.toLowerCase())) {
            return true;
        }
        return false;
    }

}
