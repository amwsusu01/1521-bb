package com.hanergy.reportForms.entity.entrust.report;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


public class CandidateReportEntity implements Serializable{
	private static final long serialVersionUID = 1L; 
	
		@ApiModelProperty(value = "背调记录id")
		private Long id ;
		
		@ApiModelProperty(value = "报告编码：当核实来源为“中介”，此项要填写")
		private String reportNumber;
		
		@ApiModelProperty(value = "订单编号")
		private String orderNumber;
		
		@ApiModelProperty(value = "核实来源：1候选人   2人力    3中介")
		private Integer verifySource;
		
		@ApiModelProperty(value = "核实单位：来源为人力则为用户表主键，来源为中介则为中介表主键")
		private Long verifyUnit;
		
		@ApiModelProperty(value = "核实单位名称")
		private String verifyName;
		
		@ApiModelProperty(value = "核实人员")
		private String verifyPerson;
		
		@ApiModelProperty(value = "审核内容")
		private String verifyContent;
		@ApiModelProperty(value = "核实说明")
		private String verifyExplain;
		
		@ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
		private Integer verifyLevel;
		
		@ApiModelProperty(value = "核实状态：1终版    2初版")
		private Integer verifyStatus;
		
		@ApiModelProperty(value = "核实日期(字符串)")
		private String submitDateStr;
		
		@ApiModelProperty(value = "总数")
		private Integer total;
		
		@ApiModelProperty(value = "审核人员")
		private Long examine;
		
		@ApiModelProperty(value = "提交日期")
		private Date submitDate;
		
		@ApiModelProperty(value = "身份证号码")
		private String idcard;
		
		@ApiModelProperty(value = "身份信息id")
		private Long credentialId;
		
		@ApiModelProperty(value = "候选人id")
		private Long beSelected;
		
		@ApiModelProperty(value = "候选人姓名")
		private String name;
		
		@ApiModelProperty(value = "候选人性别")
		private String sex;
		
		@ApiModelProperty(value = "模版id")
		private String template;
		
		@ApiModelProperty(value = "出生日期")
		private Date birthday;
		
		@ApiModelProperty(value = "出生日期")
		private String birthdayStr;
		
		@ApiModelProperty(value = "毕业学校")
		private String rSchool;
		
		@ApiModelProperty(value = "学位")
		private String rDegree;
		
		@ApiModelProperty(value = "手机号码")
		private String mobilephone;
		
		@ApiModelProperty(value = "电话号码")
		private String telephone;
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
			this.birthdayStr = this.dateToStr(birthday,"yyyy");
		}
		public String getBirthdayStr() {
			return birthdayStr;
		}
		public void setBirthdayStr(String birthdayStr) {
			this.birthdayStr = birthdayStr;
		}
		public String getrSchool() {
			return rSchool;
		}
		public void setrSchool(String rSchool) {
			this.rSchool = rSchool;
		}

		public String getrDegree() {
			return rDegree;
		}
		public void setrDegree(String rDegree) {
			this.rDegree = rDegree;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		public String getMobilephone() {
			return mobilephone;
		}
		public void setMobilephone(String mobilephone) {
			this.mobilephone = mobilephone;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getReportNumber() {
			return reportNumber;
		}
		public void setReportNumber(String reportNumber) {
			this.reportNumber = reportNumber;
		}
		public String getSubmitDateStr() {
			return submitDateStr;
		}
		public void setSubmitDateStr(String submitDateStr) {
			this.submitDateStr = submitDateStr;
		}
		public Integer getVerifySource() {
			return verifySource;
		}
		public void setVerifySource(Integer verifySource) {
			this.verifySource = verifySource;
		}
		
		public String getVerifyContent() {
			return verifyContent;
		}
		public void setVerifyContent(String verifyContent) {
			this.verifyContent = verifyContent;
		}
		public Long getVerifyUnit() {
			return verifyUnit;
		}
		public void setVerifyUnit(Long verifyUnit) {
			this.verifyUnit = verifyUnit;
		}
		public String getVerifyName() {
			return verifyName;
		}
		public void setVerifyName(String verifyName) {
			this.verifyName = verifyName;
		}
		public String getVerifyPerson() {
			return verifyPerson;
		}
		public void setVerifyPerson(String verifyPerson) {
			this.verifyPerson = verifyPerson;
		}
		public String getVerifyExplain() {
			return verifyExplain;
		}
		public void setVerifyExplain(String verifyExplain) {
			this.verifyExplain = verifyExplain;
		}
		public Integer getVerifyLevel() {
			return verifyLevel;
		}
		public void setVerifyLevel(Integer verifyLevel) {
			this.verifyLevel = verifyLevel;
		}
		public Integer getVerify_status() {
			return verifyStatus;
		}
		public void setVerify_status(Integer verifyStatus) {
			this.verifyStatus = verifyStatus;
		}
		public Long getExamine() {
			return examine;
		}
		public void setExamine(Long examine) {
			this.examine = examine;
		}
		public Date getSubmitDate() {
			return submitDate;
		}
		public void setSubmitDate(Date submitDate) {
			this.submitDate = submitDate;
			this.submitDateStr = this.dateToStr(submitDate,"yyyy-MM-dd");
		}
		public String getIdcard() {
			return idcard;
		}
		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}
		public Long getCredentialId() {
			return credentialId;
		}
		public void setCredentialId(Long credentialId) {
			this.credentialId = credentialId;
		}
		public Long getBeSelected() {
			return beSelected;
		}
		public void setBeSelected(Long beSelected) {
			this.beSelected = beSelected;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
		public Integer getVerifyStatus() {
			return verifyStatus;
		}
		public void setVerifyStatus(Integer verifyStatus) {
			this.verifyStatus = verifyStatus;
		}
		public Integer getTotal() {
			return total;
		}
		public void setTotal(Integer total) {
			this.total = total;
		}
	 
		
		public String getTemplate() {
			return template;
		}
		public void setTemplate(String template) {
			this.template = template;
		}
		public String dateToStr(Date date,String formatRule){
			 SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		        simpleDateFormat.applyPattern(formatRule);
		        return simpleDateFormat.format(date);
			
		}
	
}
