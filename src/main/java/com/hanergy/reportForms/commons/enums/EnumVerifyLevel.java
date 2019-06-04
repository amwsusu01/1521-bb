package com.hanergy.reportForms.commons.enums;

/**
 * 核实等级
 */
public enum EnumVerifyLevel {
    RED(1, "红灯"), YELLOW(2, "黄灯"), BLUE(3, "蓝灯"), GREEN(4, "绿灯");

    private int code;
    private String msg;

    EnumVerifyLevel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EnumVerifyLevel getEnum(int code) {
        for (EnumVerifyLevel level : EnumVerifyLevel.values()) {
            if (level.code == code) {
                return level;
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

	public static void main(String[] args) {
        System.out.println(EnumVerifyLevel.RED.code);
        System.out.println(EnumVerifyLevel.RED.msg);
    }
}
