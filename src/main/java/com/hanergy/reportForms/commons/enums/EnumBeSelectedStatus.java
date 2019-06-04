package com.hanergy.reportForms.commons.enums;
/**
 * 背调状态
 * @author fangshuai
 * @version 1.0.0
 * @time 2018年10月11日
 */
public enum EnumBeSelectedStatus {
	/**
	 * 为进行
	 */
    NOT_START(0, "未进行"), 
    /**
     * 完成
     */
    COMPLETED(1, "完成"),
    /**
     * 背调中（选公司）
     */
    BACKGROUND_PROCESSING(2, "背调中"),
    /**
     * 审批
     */
    BACKGROND_APPROVAL(3, "业务负责人审批"),
	/**
	 * 授权
	 */
	GRANT_AUTHORIZATION(4,"授权"),
	/**
	 * 资料填写
	 */
	INFORMATION_FILLING(5,"资料填写"),
	/**
	 * 启动
	 */
	START_UP(6,"启动"),
	/**
	 * 背调完成
	 */
	CHECK_OVER(7,"背调完成"),
	/**
	 * 背调负责人审批
	 */
	BACK_GROUND_APPROVAL(8,"背调负责人审批"),
	/**
	 * HRVP审批
	 */
	HRVP_GROUND_APPROVAL(9,"HRVP审批"),

	RECRUIT_LEADER(10,"招聘负责人审批完成");
	

    private Integer code;
    private String msg;


    EnumBeSelectedStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static EnumBeSelectedStatus getEnum(Integer code) {
        for (EnumBeSelectedStatus status : EnumBeSelectedStatus.values()) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }
        return NOT_START;
    }
}
