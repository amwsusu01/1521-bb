package com.hanergy.reportForms.commons.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class SsoUtil {

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("userId", "1112");
        map.put("timestamp", "" + System.currentTimeMillis());

        String secret = generateAesSecret();
        final String encrypted = encrypt(JSON.toJSONString(map), secret);
        System.out.println(encrypted);
        System.out.println(decrypt(encrypted, secret));
    }


    /**
     * 生成AES加密密钥。
     *
     * @return AES密钥。
     */
    public static String generateAesSecret() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey secretKey = kg.generateKey();
            return Base64.getUrlEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * AES加密。
     *
     * @param value  需要加密的字符串。
     * @param secret 加密密钥。
     * @return 加密后BASE64 URL SAFE编码。
     */
    public static String encrypt(String value, String secret) {
        try {
            SecretKey secretKey = new SecretKeySpec(Base64.getUrlDecoder().decode(secret), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES 解密。
     *
     * @param value  需要解密的字符串。
     * @param secret 解密密钥。
     * @return 解密后的明文。
     */
    public static String decrypt(String value, String secret) {
        try {
            SecretKey secretKey = new SecretKeySpec(Base64.getUrlDecoder().decode(secret), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytes = Base64.getUrlDecoder().decode(value);
            byte[] decrypted = cipher.doFinal(bytes);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
