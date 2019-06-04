package com.hanergy.reportForms.commons.enums;

/**
 * 核实状态
 */
public enum EnumVerifyStatus {
	/**
	 * 保存
	 */
    SAVE_EDITION(0, "保存"),
	/**
	 * 终版
	 */
    FINAL_EDITION(1, "终版"),
    /**
     * 初版
     */
    FIRST_EDITION(2, "初版");

    private int code;
    private String msg;

    EnumVerifyStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EnumVerifyStatus getEnum(int code) {
        for (EnumVerifyStatus enumVerifyStatus : EnumVerifyStatus.values()) {
            if (enumVerifyStatus.code == code) {
                return enumVerifyStatus;
            }
        }
        return null;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
}
