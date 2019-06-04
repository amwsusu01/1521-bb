package com.hanergy.reportForms.commons.enums;

/**
 * 检查项
 * @author fangshuai
 * @version 1.0.0
 * @time 2018年10月11日
 */
public enum EnumCheckItemCode {
	/**
	 * 身份验证
	 */
    IDENTITY_VERIFICATION("HANERGY_BS_CI_001","身份验证"), 
    /**
     * 不良记录
     */
    BAD_RECORD( "HANERGY_BS_CI_002","不良记录"),
    /**
     * 学历验证
     */
    EDUCATION_VERIFICATION( "HANERGY_BS_CI_003","学历验证"),
    /**
     * 学位验证
     */
    DEGREE_VERIFICATION( "HANERGY_BS_CI_004","学位验证"),
    /**
     * 法院诉讼
     */
    LITIGATION("HANERGY_BS_CI_005","法院诉讼"),
    /**
     * 商业利益
     */
    COMMERCIAL_PROFIT("HANERGY_BS_CI_006","商业利益"),
    /**
     * 简历对比
     */
    RESUME_CONTRAST("HANERGY_BS_CI_007","简历对比"),
    /**
     * 金融违规
     */
    FINANCIAL_IRREGULARITIES("HANERGY_BS_CI_008","金融违规"),
    /**
     * 全球数据库
     */
    GLOBAL_DATABASE("HANERGY_BS_CI_009","全球数据库"),
    /**
     * 第一段工作履历表现及评价
     */
    FIRST_WORK_RECORD_CHECK("HANERGY_BS_CI_010","第一段工作履历表现及评价"),
    /**
     * 第二段工作履历表现及评价
     */
    SECOND_WORK_RECORD_CHECK("HANERGY_BS_CI_011","第二段工作履历表现及评价"),
    /**
     * 第三段工作履历表现及评价
     */
    THIRED_WORK_RECORD_CHECK("HANERGY_BS_CI_012","第三段工作履历表现及评价");

    private String code;
    private String msg;

    EnumCheckItemCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumCheckItemCode getEnum(String code) {
        for (EnumCheckItemCode type : EnumCheckItemCode.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
    public static void main(String[] args) {
    	System.out.println(EnumCheckItemCode.FIRST_WORK_RECORD_CHECK == EnumCheckItemCode.getEnum("HANERGY_BS_CI_010"));;
	}
}
