package com.hanergy.reportForms.commons;

import com.hanergy.reportForms.commons.utils.secret.RSAUtils;

import java.util.Random;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/13 11:06
 * @Version 1.0
 **/
public class BaseController {


    protected static String getToken(Long userId) throws Exception {
        String str = getNumber() + userId + System.currentTimeMillis();
        return RSAUtils.encryptByPublicKey(str);
    }

    protected static String getNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            Random random = new Random();
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

}
