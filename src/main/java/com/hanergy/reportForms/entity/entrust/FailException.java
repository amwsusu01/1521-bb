package com.hanergy.reportForms.entity.entrust;

public class FailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public String exceptionMsg = " method  run  failed !!";
	
	public FailException() {
	}
	public FailException( String exceptionMsg) {
		super(exceptionMsg);
		this.exceptionMsg= exceptionMsg;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}


}
