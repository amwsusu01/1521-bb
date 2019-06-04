package com.hanergy.reportForms.commons.enums;

public enum EnumPhaseBeSelectedH5Status {
	 /**
	  * 已完成 :check_status :1
	  */
    COMPLETEMENT( "已完成"),
    /**
     * 待审批  :check_status :3 or 9
     */
    BE_VERIFY("待审批"),
    /**
     * 资料填写 :check_status :0
     */
    INFORMATION_FILLING("资料填写"),
    /**
     * 候选人授权 :check_status : 4 
     */
    AUTH("候选人授权"),
    /**
     * 候选人提交资料  :check_status : 5
     */
    CANDIDATE_FILLING("候选人提交资料"),
    /**
     * 联机调查 : check_status : 5  && self_check_status :211
     */
    MACHINE_CHECK("联机调查"),
    /**
     * 工作经历核查  : check_status : 5  && self_check_status :212
     */
    WORK_EXPIRENCE("工作经历核查"),
    /**		
     * 待委托   :check_status : 4 or 5 or 6   &&  agency_check_status :221
     */
    BE_ENTRUST("待委托"),
    /**
     * 调查中:check_status : 4 or 5 or 6   && agency_check_status :222
     */
    CHECKING("调查中"),
	/**
	 * 待委托,候选人授权  :   agency_check_status :221 && check_status : 4 
	 */
	BE_ENTRUST_OR_AUTH("待委托,候选人授权"),
	/**
	 * 待委托,候选人提交资料   :agency_check_status :221 && check_status : 5
	 */
	BE_ENTRUST_OR_CANDIDATE_FILLING("待委托,候选人提交资料"),
	/**
	 * 待委托,联机调查  :agency_check_status :221 &&  check_status : 5  && self_check_status :211
	 */
	BE_ENTRUST_OR_MACHINE_CHECK("待委托,联机调查"),
	/**
	 * 待委托,工作经历核查   :agency_check_status :221 &&  check_status : 5  && self_check_status :212
	 */
	BE_ENTRUST_OR_WORK_EXPIRENCE("待委托,工作经历核查"),
	/**
	 * 调查中,候选人授权  : agency_check_status :222 && check_status : 4 
	 */
	CHECKING_OR_AUTH("调查中,候选人授权"),
	/**
	 * 调查中,候选人提交资料  :agency_check_status :222 && check_status : 5
	 */
	CHECKING_OR_CANDIDATE_FILLING("调查中,候选人提交资料"),
	/**
	 * 调查中,联机调查  :agency_check_status :222 &&  check_status : 5  && self_check_status :211
	 */
	CHECKING_OR_MACHINE_CHECK("调查中,联机调查"),
	/**
	 * 调查中,工作经历核查  :agency_check_status :222 &&  check_status : 5  && self_check_status :212
	 */
	CHECKING_OR_MACHINE_WORK_EXPIRENCE("调查中,工作经历核查"),
	/**
	 * 待审核  :  check_status : 7
	 */
	BE_EXAMINE("待审核");
	

    private String name;

    EnumPhaseBeSelectedH5Status( String name) {
        this.name = name;
    }
 



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static EnumPhaseBeSelectedH5Status getEnum(String msg) {
        for (EnumPhaseBeSelectedH5Status type : EnumPhaseBeSelectedH5Status.values()) {
            if (type.getName().contains(msg)) {
                return type;
            }
        }
        return null;
    }


}
