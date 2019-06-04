package com.hanergy.reportForms.commons.utils.sfsys;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @ClassName MyAuthenticator
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/26 16:24
 * @Version 1.0
 **/
public class MyAuthenticator extends Authenticator {

    String _userName=null;
    char[] _password=null;


    public MyAuthenticator(){
    }
    public MyAuthenticator(String username, String password) {
        this._userName = username;
        this._password = password.toCharArray();
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(_userName, _password);
    }
}
