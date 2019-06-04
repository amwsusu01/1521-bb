package com.hanergy.reportForms.dto.beselected;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ResumeDto
 * @Description TODO 候选人填写的简历
 * @Author DURONGHONG
 * @DATE 2018/10/8 10:12
 * @Version 1.0
 **/
@ApiModel(value = "候选人填写的简历信息")
public class ResumeDto implements Serializable {

    @ApiModelProperty(value = "候选人id")
    private Long id;

    @ApiModelProperty(value = "中文姓名")
    private String name;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "参加工作时间")
    private String workDate;

    @ApiParam(value = "手机号码")
    private String mobilePhone;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "现居住地")
    private String nowPlace;

    @ApiModelProperty(value = "户口所在地")
    private String domPlace;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "证件号码")
    private String idcard;

    @ApiModelProperty(value = "教育背景")
    private List<ResumeEducationDto> educations;

    @ApiModelProperty(value = "工作经验")
    private List<ResumeExpirenceDto> expirences;

    @ApiModelProperty(value = "英文名")
    private String engName;
    @ApiModelProperty(value = "曾用名")
    private String usedName;
    @ApiModelProperty(value = "更名日期")
    private String changeNameDate;
    @ApiModelProperty(value = "证件类型:1:身份证 2:居住证 3:签证 4:护照 5:户口本 6:军人证 7:团员证 8:党员证 9:港澳通行证 0:其他")
    private Integer documentType;
    @ApiModelProperty(value = "国籍")
    private String nationality;
    @ApiModelProperty(value = "户口管辖派出所")
    private String police;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNowPlace() {
        return nowPlace;
    }

    public void setNowPlace(String nowPlace) {
        this.nowPlace = nowPlace;
    }

    public String getDomPlace() {
        return domPlace;
    }

    public void setDomPlace(String domPlace) {
        this.domPlace = domPlace;
    }

    public List<ResumeEducationDto> getEducations() {
        return educations;
    }

    public void setEducations(List<ResumeEducationDto> educations) {
        this.educations = educations;
    }

    public List<ResumeExpirenceDto> getExpirences() {
        return expirences;
    }

    public void setExpirences(List<ResumeExpirenceDto> expirences) {
        this.expirences = expirences;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getUsedName() {
        return usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    public String getChangeNameDate() {
        return changeNameDate;
    }

    public void setChangeNameDate(String changeNameDate) {
        this.changeNameDate = changeNameDate;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
