package com.hanergy.reportForms.commons.enums;

/**
 * 检查项
 * @author fangshuai
 * @version 1.0.0
 * @time 2018年10月11日
 */
public enum EnumCheckItemId {
	/**
	 * 身份验证
	 */
    IDENTITY_VERIFICATION(5679936005962384L,"身份验证"), 
    /**
     * 不良记录
     */
    BAD_RECORD( 5679936009543086L,"不良记录"),
    /**
     * 学历验证
     */
    EDUCATION_VERIFICATION( 5679936001120562L,"学历验证"),
    /**
     * 学位验证
     */
    DEGREE_VERIFICATION( 5679936001958758L,"学位验证"),
    /**
     * 法院诉讼
     */
    LITIGATION( 5679936002433356L,"法院诉讼"),
    /**
     * 商业利益
     */
    COMMERCIAL_PROFIT( 5679936006167673L,"商业利益"),
    /**
     * 简历对比
     */
    RESUME_CONTRAST( 5679936001054972L,"简历对比"),
    /**
     * 金融违规
     */
    FINANCIAL_IRREGULARITIES(5679936005815302L,"金融违规"),
    /**
     * 全球数据库
     */
    GLOBAL_DATABASE(5679936007861991L,"全球数据库"),
    /**
     * 第一段工作履历表现及评价
     */
    FIRST_WORK_RECORD_CHECK(5679936001668267L,"第一段工作履历表现及评价"),
    /**
     * 第二段工作履历表现及评价
     */
    SECOND_WORK_RECORD_CHECK(5679936001821868L,"第二段工作履历表现及评价"),
    /**
     * 第三段工作履历表现及评价
     */
    THIRED_WORK_RECORD_CHECK(5679936007882453L,"第三段工作履历表现及评价");

    private Long code;
    private String msg;

    EnumCheckItemId(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumCheckItemId getEnum(String code) {
        for (EnumCheckItemId type : EnumCheckItemId.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
    public static void main(String[] args) {
    	System.out.println(EnumCheckItemId.FIRST_WORK_RECORD_CHECK == EnumCheckItemId.getEnum("HANERGY_BS_CI_010"));;
	}
}
