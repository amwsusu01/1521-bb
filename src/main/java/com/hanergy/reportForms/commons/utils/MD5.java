package com.hanergy.reportForms.commons.utils;


import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {

    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    public MD5() {
    }

    // 返回形式为数字跟字符
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * 加密
     *
     * @param strObj 需加密的参数
     * @return
     */
    public static String getMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

	public static void main(String[] args) throws UnsupportedEncodingException {
		String xAccount = "17021809253821";
		String xSecretkey = "l*9kdkk41mpd4w1l";
		long xTimestamp = System.currentTimeMillis();
		String salt = "919f37e180ec5d84810c1f1e20923287c4e44bf6c07e6b76ed18b1390b74fad6";
		System.out.println("xTimestamp:"+xTimestamp);
		StringBuffer sb = new StringBuffer("x-account");
			sb.append(xAccount).append("x-secretkey").
			append(xSecretkey).append("x-timestamp").
			append(xTimestamp).append(salt);
			
		String spellStr = sb.toString();
		System.out.println("spellStr:"+spellStr);
    	String sign = DigestUtils.md5DigestAsHex((spellStr).getBytes("UTF-8"));
    	System.out.println("sign:"+sign);
	}
	 
}