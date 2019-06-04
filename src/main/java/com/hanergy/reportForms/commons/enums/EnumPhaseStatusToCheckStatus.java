package com.hanergy.reportForms.commons.enums;

public enum EnumPhaseStatusToCheckStatus {
	/**
	 * 
	 */
    INFORMATION_FILLLING(0, "资料填写"),
    CANDIDATE_AUTH(4, "候选人授权"),
    CANDIDATE_UPLOAD_SOURCE(5, "候选人提交资料"),
    MACHINE_VERIFY(2, "联机核查"),
    EXPIRENCE_VERIFY(2,"工作经历核查"),
    BE_ENTRUST_AGENCY(2,"待委托"),
    SURVEY(2,"调查中"),
    BE_VERIFT(7,"待核实");

    private Integer id;

    private String name;

    EnumPhaseStatusToCheckStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
 

    public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static EnumPhaseStatusToCheckStatus getEnum(Long id) {
        for (EnumPhaseStatusToCheckStatus type : EnumPhaseStatusToCheckStatus.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
	public static EnumPhaseStatusToCheckStatus getEnum(String msg) {
        for (EnumPhaseStatusToCheckStatus type : EnumPhaseStatusToCheckStatus.values()) {
            if (type.getName().contains(msg)) {
                return type;
            }
        }
        return null;
    }


}
