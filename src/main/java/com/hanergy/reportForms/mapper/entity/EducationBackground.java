package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * <p>
 * 教育背景
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value = "教育背景", description = "教育背景")
public class EducationBackground extends Model<EducationBackground> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "15374284691288898")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人（表的主键）", example = "15374284691263114")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "来源：1候选人   2人力    3中介背调", example = "2")
    private Integer verifySource;

    @ApiModelProperty(value = "背调（表的主键）", example = "15374284691263112")
    private Long checkId;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯", example = "1")
    private Integer verifyLevel;

    @ApiModelProperty(value = "核实说明", example = "信息一致")
    private String verifyExplain;

    @ApiModelProperty(value = "学校", example = "北京大学")
    private String school;

    @ApiModelProperty(value = "学校核实：1真   0假", example = "1")
    private Integer schoolVerify;

    @ApiModelProperty(value = "学校说明", example = "信息一致")
    private String schoolVerifyExplain;

    @ApiModelProperty(value = "入学日期", example = "2010-09")
    private String schoolStart;

    @ApiModelProperty(value = "入学核实：1真   0假", example = "1")
    private Integer schoolStartVerify;

    @ApiModelProperty(value = "入学说明", example = "一致")
    private String schoolStartVerifyExplain;

    @ApiModelProperty(value = "毕业日期", example = "2014-07")
    private String graduationDate;

    @ApiModelProperty(value = "毕业核实：1真   0假", example = "1")
    private Integer graduationDateVerify;

    @ApiModelProperty(value = "毕业说明", example = "一致")
    private String graduationDateVerifyExplain;

    @ApiModelProperty(value = "专业", example = "英语")
    @Nullable
    private String major;

    @ApiModelProperty(value = "专业核实：1真   0假", example = "1")
    private Integer majorVerify;

    @ApiModelProperty(value = "专业说明", example = "信息一致")
    private String majorVerifyExplain;

    @ApiModelProperty(value = "学历（最高）：1大学专科   2大学本科   3硕士研究生   4博士研究生 5 高中及以下 6 高等职业学校    0其它", example = "2")
    private Integer education;

    @ApiModelProperty(value = "学历核实：1真   0假", example = "1")
    private Integer educationVerify;

    @ApiModelProperty(value = "学历说明", example = "信息一致")
    private String educationVerifyExplain;

    @ApiModelProperty(value = "学历性质（教育类型）：1全日制   2非全日制   0其它", example = "1")
    private Integer educatioType;

    @ApiModelProperty(value = "学位时间（获取学位的时间）", example = "2012-03")
    private String degreeDate;

    @ApiModelProperty(value = "学位", example = "学士")
    private String degree;

    @ApiModelProperty(value = "学位核实：1真   0假", example = "1")
    private Integer degreeVerify;

    @ApiModelProperty(value = "学位说明", example = "信息一致")
    private String degreeVerifyExplain;

    @ApiModelProperty(value = "学历证书编号")
    private String educationNumber;
    @ApiModelProperty(value = "是否授予学位:0-否,1-是")
    private Integer awardDegree;

    @ApiModelProperty(value = "就读国家")
    private String nationality;

    @ApiModelProperty(value = "就读国家核实: 0假 1真")
    private Integer nationalityVerify;
    @ApiModelProperty(value = "就读国家核实说明")
    private String nationalityVerifyExplain;

    @ApiModelProperty(value = "学院(系)")
    private String college;
    @ApiModelProperty(value = "学院核实:1真,0假")
    private Integer collegeVerify;
    @ApiModelProperty(value = "学院核实说明")
    private String collegeVerifyExplain;

//    @ApiModelProperty(value = "学校对比")
//    private  int  schoolVitae;
//    @ApiModelProperty(value = "入学时间对比")
//    private  int schoolStartVitae;
//    @ApiModelProperty(value = "毕业时间对比")
//    private int graduationDateViate;
//    @ApiModelProperty(value = "专业对比")
//    private int majorViate;
//    @ApiModelProperty(value = "学历对比")
//    private int educationViate;
//    @ApiModelProperty(value = "学位对比")
//    private int degreeViate;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getCollegeVerify() {
        return collegeVerify;
    }

    public void setCollegeVerify(Integer collegeVerify) {
        this.collegeVerify = collegeVerify;
    }

    public String getCollegeVerifyExplain() {
        return collegeVerifyExplain;
    }

    public void setCollegeVerifyExplain(String collegeVerifyExplain) {
        this.collegeVerifyExplain = collegeVerifyExplain;
    }

    public Integer getNationalityVerify() {
        return nationalityVerify;
    }

    public void setNationalityVerify(Integer nationalityVerify) {
        this.nationalityVerify = nationalityVerify;
    }

    public String getNationalityVerifyExplain() {
        return nationalityVerifyExplain;
    }

    public void setNationalityVerifyExplain(String nationalityVerifyExplain) {
        this.nationalityVerifyExplain = nationalityVerifyExplain;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeSelected() {
        return beSelected;
    }

    public void setBeSelected(Long beSelected) {
        this.beSelected = beSelected;
    }

    public Integer getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(Integer verifySource) {
        this.verifySource = verifySource;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public Integer getVerifyLevel() {
        return verifyLevel;
    }

    public void setVerifyLevel(Integer verifyLevel) {
        this.verifyLevel = verifyLevel;
    }

    public String getVerifyExplain() {
        return StringUtils.isEmpty(verifyExplain) ? "" : verifyExplain;
    }

    public void setVerifyExplain(String verifyExplain) {
        this.verifyExplain = verifyExplain;
    }

    public String getSchool() {
        return StringUtils.isEmpty(school) ? "" : school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getSchoolVerify() {
        return schoolVerify;
    }

    public void setSchoolVerify(Integer schoolVerify) {
        this.schoolVerify = schoolVerify;
    }

    public String getSchoolVerifyExplain() {
        return StringUtils.isEmpty(schoolVerifyExplain) ? "" : schoolVerifyExplain;
    }

    public void setSchoolVerifyExplain(String schoolVerifyExplain) {
        this.schoolVerifyExplain = schoolVerifyExplain;
    }

    public Integer getSchoolStartVerify() {
        return schoolStartVerify;
    }

    public void setSchoolStartVerify(Integer schoolStartVerify) {
        this.schoolStartVerify = schoolStartVerify;
    }

    public String getSchoolStartVerifyExplain() {
        return StringUtils.isEmpty(schoolStartVerifyExplain) ? "" : schoolStartVerifyExplain;
    }

    public void setSchoolStartVerifyExplain(String schoolStartVerifyExplain) {
        this.schoolStartVerifyExplain = schoolStartVerifyExplain;
    }

    public Integer getGraduationDateVerify() {
        return graduationDateVerify;
    }

    public void setGraduationDateVerify(Integer graduationDateVerify) {
        this.graduationDateVerify = graduationDateVerify;
    }

    public String getGraduationDateVerifyExplain() {
        return StringUtils.isEmpty(graduationDateVerifyExplain) ? "" : graduationDateVerifyExplain;
    }

    public void setGraduationDateVerifyExplain(String graduationDateVerifyExplain) {
        this.graduationDateVerifyExplain = graduationDateVerifyExplain;
    }

    public String getMajor() {
        return StringUtils.isEmpty(major) ? "" : major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getMajorVerify() {
        return majorVerify;
    }

    public void setMajorVerify(Integer majorVerify) {
        this.majorVerify = majorVerify;
    }

    public String getMajorVerifyExplain() {
        return StringUtils.isEmpty(majorVerifyExplain) ? "" : majorVerifyExplain;
    }

    public void setMajorVerifyExplain(String majorVerifyExplain) {
        this.majorVerifyExplain = majorVerifyExplain;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getEducationVerify() {
        return educationVerify;
    }

    public void setEducationVerify(Integer educationVerify) {
        this.educationVerify = educationVerify;
    }

    public String getEducationVerifyExplain() {
        return StringUtils.isEmpty(educationVerifyExplain) ? "" : educationVerifyExplain;
    }

    public void setEducationVerifyExplain(String educationVerifyExplain) {
        this.educationVerifyExplain = educationVerifyExplain;
    }

    public Integer getEducatioType() {
        return educatioType;
    }

    public void setEducatioType(Integer educatioType) {
        this.educatioType = educatioType;
    }

    public String getDegreeDate() {
        return degreeDate;
    }

    public void setDegreeDate(String degreeDate) {
        this.degreeDate = degreeDate;
    }

    public String getDegree() {
        return StringUtils.isEmpty(degree) ? "" : degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getDegreeVerify() {
        return degreeVerify;
    }

    public void setDegreeVerify(Integer degreeVerify) {
        this.degreeVerify = degreeVerify;
    }

    public String getDegreeVerifyExplain() {
        return StringUtils.isEmpty(degreeVerifyExplain) ? "" : degreeVerifyExplain;
    }

    public void setDegreeVerifyExplain(String degreeVerifyExplain) {
        this.degreeVerifyExplain = degreeVerifyExplain;
    }

    public String getEducationNumber() {
        return educationNumber;
    }

    public void setEducationNumber(String educationNumber) {
        this.educationNumber = educationNumber;
    }

    public Integer getAwardDegree() {
        return awardDegree;
    }

    public void setAwardDegree(Integer awardDegree) {
        this.awardDegree = awardDegree;
    }

    public String getSchoolStart() {
        return schoolStart;
    }

    public void setSchoolStart(String schoolStart) {
        this.schoolStart = schoolStart;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
