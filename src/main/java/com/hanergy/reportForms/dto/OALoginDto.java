package com.hanergy.reportForms.dto;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class OALoginDto {

    private String corpCode;
    private String encryptKey;

    private EncrptyInfo encryptInfo;

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public EncrptyInfo getEncryptInfo() {
        return encryptInfo;
    }

    public void setEncryptInfo(EncrptyInfo encryptInfo) {
        this.encryptInfo = encryptInfo;
    }

    public boolean isEmpty(){
        return !StringUtils.isEmpty(this.corpCode) && !StringUtils.isEmpty(this.encryptKey) && this.encryptInfo != null;
    }


}

