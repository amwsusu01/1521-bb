package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 工作履历
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value = "工作履历", description = "工作履历")
public class Experience extends Model<Experience> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "15375144471987457")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人（表的主键）", example = "15375144471987452")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "来源：1候选人   2人力    3中介背调", example = "2")
    private Integer verifySource;

    @ApiModelProperty(value = "背调（表的主键）", example = "15375144471987451")
    private Long checkId;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯", example = "1")
    private Integer verifyLevel;

    @ApiModelProperty(value = "核实说明", example = "不一致")
    private String verifyExplain;

    @ApiModelProperty(value = "履历受访人", example = "张三")
    private String visitor;

    @ApiModelProperty(value = "履历受访日期", example = "2018-09-12")
    @TableField("visitorDate")
    private String visitorDate;

    @ApiModelProperty(value = "履历受访人部门", example = "技术研发部")
    @TableField("visitorDepartment")
    private String visitorDepartment;

    @ApiModelProperty(value = "履历受访人职位", example = "技术总监")
    @TableField("visitorJob")
    private String visitorJob;

    @ApiModelProperty(value = "履历受访人候选人关系", example = "上下级")
    @TableField("visitorRelation")
    private String visitorRelation;

    @ApiModelProperty(value = "履历受访人受访日期", example = "2012-09")
    @TableField("visitorEntry")
    private String visitorEntry;

    @ApiModelProperty(value = "履历受访人手机", example = "13495959595")
    @TableField("visitorPhone")
    private String visitorPhone;

    @ApiModelProperty(value = "公司（名称）", example = "华尔街英语")
    private String name;

    @ApiModelProperty(value = "公司核实：1真   0假", example = "1")
    @TableField("nameVerify")
    private Integer nameVerify;

    @ApiModelProperty(value = "公司说明", example = "信息不一致")
    @TableField("nameVerifyExplain")
    private String nameVerifyExplain;

    @ApiModelProperty(value = "入职", example = "2012-10-12")
    private String entry;

    @ApiModelProperty(value = "入职核实：1真   0假", example = "1")
    @TableField("entryVerify")
    private Integer entryVerify;

    @ApiModelProperty(value = "入职说明", example = "信息一致")
    @TableField("entryVerifyExplain")
    private String entryVerifyExplain;

    @ApiModelProperty(value = "离职：不填写即为“至今”", example = "2018-08")
    private String leaveDate;

    @ApiModelProperty(value = "离职核实：1真   0假", example = "1")
    @TableField("leaveVerify")
    private Integer leaveVerify;

    @ApiModelProperty(value = "任职年限", example = "2")
    @TableField("workingLlife")
    private Integer workingLlife;

    @ApiModelProperty(value = "离职说明", example = "个人意愿")
    @TableField("leaveVerifyExplain")
    private String leaveVerifyExplain;

    @ApiModelProperty(value = "离职原因：1合同到期   2公司辞退   3本人意愿  0其它", example = "3")
    @TableField("leaveReason")
    private Integer leaveReason;

    @ApiModelProperty(value = "离职原因核实：1真   0假", example = "1")
    @TableField("leaveReasonVerify")
    private Integer leaveReasonVerify;

    @ApiModelProperty(value = "离职原因说明", example = "一致")
    @TableField("leaveReasonVerifyExplain")
    private String leaveReasonVerifyExplain;

    @ApiModelProperty(value = "月薪", example = "15000元")
    @TableField("monthSalary")
    private String monthSalary;

    @ApiModelProperty(value = "月薪核实：1真   0假", example = "1")
    @TableField("monthSalaryVerify")
    private Integer monthSalaryVerify;

    @ApiModelProperty(value = "月薪说明", example = "信息一致")
    @TableField("monthSalaryVerifyExplain")
    private String monthSalaryVerifyExplain;

    @ApiModelProperty(value = "年薪", example = "200000元")
    @TableField("yearSalary")
    private String yearSalary;

    @ApiModelProperty(value = "年薪核实：1真   0假", example = "1")
    @TableField("yearSalaryVerify")
    private Integer yearSalaryVerify;

    @ApiModelProperty(value = "年薪说明", example = "信息一致")
    @TableField("yearSalaryVerifyExplain")
    private String yearSalaryVerifyExplain;

    @ApiModelProperty(value = "职位（最终）", example = "高级Java")
    private String job;

    @ApiModelProperty(value = "职位核实：1真   0假", example = "1")
    @TableField("jobVerify")
    private Integer jobVerify;

    @ApiModelProperty(value = "职位说明", example = "信息一致")
    @TableField("jobVerifyExplain")
    private String jobVerifyExplain;

    @ApiModelProperty(value = "职责（包括工作内容）", example = "系统维护")
    private String duty;

    @ApiModelProperty(value = "职责核实：1真   0假", example = "1")
    @TableField("dutyVerify")
    private Integer dutyVerify;

    @ApiModelProperty(value = "职责说明", example = "信息一致")
    @TableField("dutyVerifyExplain")
    private String dutyVerifyExplain;

    @ApiModelProperty(value = "上级（汇报对象）", example = "王五")
    private String superior;

    @ApiModelProperty(value = "上级核实：1真   0假", example = "1")
    @TableField("superiorVerify")
    private Integer superiorVerify;

    @ApiModelProperty(value = "上级核实说明", example = "一致")
    @TableField("superiorVerifyExplain")
    private String superiorVerifyExplain;

    @ApiModelProperty(value = "下属（员工人数）", example = "3")
    private Integer subordinate;

    @ApiModelProperty(value = "下属核实: 1 真 0 假", example = "1")
    @TableField("subordinateVerify")
    private Integer subordinateVerify;

    @ApiModelProperty(value = "下属说明", example = "一致")
    @TableField("subordinateVerifyExplain")
    private String subordinateVerifyExplain;

    @ApiModelProperty(value = "劳动争议存在：1是   0否", example = "0")
    @TableField("disputeExist")
    private Integer disputeExist;

    @ApiModelProperty(value = "劳动争议解决：1是   0否", example = "0")
    @TableField("disputeRelieve")
    private Integer disputeRelieve;

    @ApiModelProperty(value = "违规（违反过公司规定）：1是   0否", example = "0")
    private Integer illegality;

    @ApiModelProperty(value = "竞业协议存在：1是   0否", example = "0")
    @TableField("competitionExist")
    private Integer competitionExist;

    @ApiModelProperty(value = "竞业协议失效：1是   0否", example = "0")
    @TableField("competitionRelieve")
    private Integer competitionRelieve;

    @ApiModelProperty(value = "竞业协议失效日期", example = "2020-09")
    @TableField("competitionDate")
    private String competitionDate;

    @ApiModelProperty(value = "合同存在：1是   0否", example = "0")
    @TableField("contractExist")
    private Integer contractExist;

    @ApiModelProperty(value = "合同失效：1是   0否", example = "0")
    @TableField("contractRelieve")
    private Integer contractRelieve;

    @ApiModelProperty(value = "合同失效日期", example = "2018-08")
    @TableField("contractDate")
    private String contractDate;

    @ApiModelProperty(value = "表现受访人", example = "里斯")
    private String visitor2;

    @ApiModelProperty(value = "表现受访日期", example = "2018-09-11")
    @TableField("visitorDate2")
    private String visitorDate2;

    @ApiModelProperty(value = "表现受访人部门", example = "人事部")
    private String visitorDepartment2;

    @ApiModelProperty(value = "表现受访人职位", example = "人事专员")
    @TableField("visitorJob2")
    private String visitorJob2;

    @ApiModelProperty(value = "表现受访人候选人关系", example = "同事")
    @TableField("visitorRelation2")
    private String visitorRelation2;

    @ApiModelProperty(value = "表现受访人入职（日期）", example = "2012-08")
    @TableField("visitorEntry2")
    private String visitorEntry2;

    @ApiModelProperty(value = "表现受访人手机", example = "13200003333")
    @TableField("visitorPhone2")
    private String visitorPhone2;

    @ApiModelProperty(value = "优势：对应太和鼎信的【优点】", example = "工作能力突出")
    private String advantage;

    @ApiModelProperty(value = "劣势：对应太和鼎信的【缺点】", example = "时间观念有待加强")
    private String defect;

    @ApiModelProperty(value = "表现受访人描述优点2")
    @TableField(value = "visitor_advantage2")
    private String visitorAdvantage2;

    @ApiModelProperty(value = "表现受访人描述缺点2")
    @TableField(value = "visitor_defect2")
    private String visitorDefect2;

    @ApiModelProperty(value = "同事描述优点3")
    @TableField(value = "visitor_advantage3")
    private String visitorAdvantage3;
    @ApiModelProperty(value = "同事描述缺点3")
    @TableField(value = "visitor_defect3")
    private String visitorDefect3;

    @ApiModelProperty(value = "领导能力描述：对应太和鼎信的【领导能力】", example = "不错")
    @TableField("leaderAbility")
    private String leaderAbility;

    @ApiModelProperty(value = "领导能力分值：1很好   2好   3一般   4待提升   5不适用", example = "2")
    private Integer leadership;

    @ApiModelProperty(value = "协调能力描述：对应太和鼎信的【计划组织】", example = "协调能力优秀")
    @TableField("coordinateAbility")
    private String coordinateAbility;

    @ApiModelProperty(value = "协调能力分值：1很好   2好   3一般   4待提升   5不适用", example = "1")
    private Integer coordinate;

    @ApiModelProperty(value = "工作态度：1很好   2好   3一般   4待提升   5不适用", example = "1")
    private Integer attitude;

    @ApiModelProperty(value = "工作绩效：1很好   2好   3一般   4待提升   5不适用", example = "1")
    private Integer performance;

    @ApiModelProperty(value = "主动性：1很好   2好   3一般   4待提升   5不适用", example = "1")
    private Integer initiative;

    @ApiModelProperty(value = "诚信度：1很好   2好   3一般   4待提升   5不适用", example = "1")
    private Integer sincerity;

    @ApiModelProperty(value = "团队精神：1很好   2好   3一般   4待提升   5不适用", example = "1")
    private Integer team;

    @ApiModelProperty(value = "学习能力：1很好   2好   3一般   4待提升   5不适用", example = "1")
    private Integer study;

    @ApiModelProperty(value = "打分：满分10分", example = "9")
    private Integer score;

    @ApiModelProperty(value = "共事：1是   0否", example = "1")
    private Integer cooperation;

    @ApiModelProperty(value = "具体工作地址")
    private String address;

    @ApiModelProperty(value = "业绩表现描述")
    private String performanceDesc;

    @ApiModelProperty(value = "公司电话")
    private String telephone;

    @ApiModelProperty(value = "可否与单位核实:0-否,1-是")
    private Integer canVerify;

    @ApiModelProperty(value = "可与该单位核实的时间")
    private String canVerifyDate;

    @ApiModelProperty(value = "同事姓名")
    private String visitor3;

    @ApiModelProperty(value = "同事职位")
    @TableField(value = "visitorJob3")
    private String visitorJob3;

    @ApiModelProperty(value = "同事电话")
    @TableField(value = "visitorPhone3")
    private String visitorPhone3;

    @ApiModelProperty(value = "同事邮箱")
    @TableField(value = "visitorMail3")
    private String visitorMail3;

    @ApiModelProperty(value = "上级邮箱")
    @TableField(value = "visitorMail2")
    private String visitorMail2;

    @ApiModelProperty(value = "履历受访人邮箱")
    private String visitorMail;

    @ApiModelProperty(value = "工作方式:1-实习,2-兼职,3-全职正式,4-尚在试用期,5-第三方派遣")
    @TableField("work_mode")
    private Integer workMode;

    @ApiModelProperty(value = "受访人与候选人关系3")
    @TableField(value = "visitorRelation3")
    private String visitorRelation3;
    @ApiModelProperty(value = "履历受访人与候选人共事时间")
    @TableField(value = "visitor_work_together")
    private String visitorWorkTogether;

    @ApiModelProperty(value = "表现受访人与候选人共事时间")
    @TableField(value = "visitor_work_together2")
    private String visitorWorkTogether2;

    @ApiModelProperty(value = "受访人与候选人共事时间")
    @TableField(value = "visitor_work_together3")
    private String visitorWorkTogether3;


    @TableField("visitor_score")
    @ApiModelProperty(value = "履历受访人打分")
    private String visitorScore;

    @ApiModelProperty(value = "表现受访人打分")
    @TableField(value = "visitor_score2")
    private String visitorScore2;

    @ApiModelProperty(value = "同事打分")
    @TableField("visitor_score3")
    private String visitorScore3;

    @ApiModelProperty(value = "同事受访日期")
    @TableField(value = "visitor_date3")
    private String visitorDate3;

    @ApiModelProperty(value = "同事描述入职日期")
    @TableField(value = "visitor_entry3")
    private String visitorEntry3;

    @ApiModelProperty(value = "在该公司的工作项目列表")
    @TableField(exist = false)
    private List<Project> projects;

    @ApiModelProperty(value = "履历受访人描述缺点")
    @TableField(value = "visitor_defect")
    private String visitorDefect;

    @ApiModelProperty(value = "履历受访人描述优点")
    @TableField(value = "visitor_advantage")
    private String visitorAdvantage;

    @ApiModelProperty(value = "履历受访人预期")
    @TableField(value = "visitor_expect")
    private String visitorExpect;

    @ApiModelProperty(value = "表现受访人预期")
    @TableField(value = "visitor_expect2")
    private String visitorExpect2;

    @ApiModelProperty(value = "同事描述预期")
    @TableField(value = "visitor_expect3")
    private String visitorExpect3;

    @ApiModelProperty(value = "履历受访人描述领导力")
    @TableField(value = "visitor_leadership")
    private String visitorLeadership;

    @ApiModelProperty(value = "表现受访人描述领导力")
    @TableField(value = "visitor_leadership2")
    private String visitorLeadership2;

    @ApiModelProperty(value = "同事描述领导力")
    @TableField(value = "visitor_leadership3")
    private String visitorLeadership3;

    @ApiModelProperty(value = "履历受访人描述薪资")
    @TableField(value = "visitor_salary")
    private String visitorSalary;

    @ApiModelProperty(value = "表现受访人描述薪资")
    @TableField(value = "visitor_salary2")
    private String visitorSalary2;

    @ApiModelProperty(value = "同事描述薪资")
    @TableField(value = "visitor_salary3")
    private String visitorSalary3;

    @ApiModelProperty(value = "履历受访人描述离职原因")
    @TableField(value = "visitor_leave_reason")
    private String visitorLeaveReason;

    @ApiModelProperty(value = "表现受访人描述离职原因")
    @TableField(value = "visitor_leave_reason2")
    private String visitorLeaveReason2;

    @ApiModelProperty(value = "同事描述离职原因")
    @TableField(value = "visitor_leave_reason3")
    private String visitorLeaveReason3;
    
    @ApiModelProperty(value = "履历排序，1：第一段 ;2：第二段; 3：第三段")
    @TableField(value = "expirence_seq")
    private Integer expirenceSeq;

    @ApiModelProperty(value = "所在部门")
    private String department;
    @ApiModelProperty(value = "所在部门核实:1真,0假")
    private Integer departmentVerify;
    @ApiModelProperty(value = "所在部门核实说明")
    private String departmentVerifyExplain;

    @ApiModelProperty(value = "公司名称对比")
    private int nameViate;
    @ApiModelProperty(value = "入职日期对比")
    private int entryViate;
    @ApiModelProperty(value = "离职日期对比")
    private int  leaveViate;
    @ApiModelProperty(value = "职位对比")
    private int jobViate;

    public int getNameViate() {
        return nameViate;
    }

    public void setNameViate(int nameViate) {
        this.nameViate = nameViate;
    }

    public int getEntryViate() {
        return entryViate;
    }

    public void setEntryViate(int entryViate) {
        this.entryViate = entryViate;
    }

    public int getLeaveViate() {
        return leaveViate;
    }

    public void setLeaveViate(int leaveViate) {
        this.leaveViate = leaveViate;
    }

    public int getJobViate() {
        return jobViate;
    }

    public void setJobViate(int jobViate) {
        this.jobViate = jobViate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getDepartmentVerify() {
        return departmentVerify;
    }

    public void setDepartmentVerify(Integer departmentVerify) {
        this.departmentVerify = departmentVerify;
    }

    public String getDepartmentVerifyExplain() {
        return departmentVerifyExplain;
    }

    public void setDepartmentVerifyExplain(String departmentVerifyExplain) {
        this.departmentVerifyExplain = departmentVerifyExplain;
    }

    public Integer getExpirenceSeq() {
		return expirenceSeq;
	}

	public void setExpirenceSeq(Integer expirenceSeq) {
		this.expirenceSeq = expirenceSeq;
	}

	public String getVisitorDefect() {
        return visitorDefect;
    }

    public void setVisitorDefect(String visitorDefect) {
        this.visitorDefect = visitorDefect;
    }

    public String getVisitorAdvantage() {
        return visitorAdvantage;
    }

    public void setVisitorAdvantage(String visitorAdvantage) {
        this.visitorAdvantage = visitorAdvantage;
    }

    public String getVisitorExpect() {
        return visitorExpect;
    }

    public void setVisitorExpect(String visitorExpect) {
        this.visitorExpect = visitorExpect;
    }

    public String getVisitorExpect2() {
        return visitorExpect2;
    }

    public void setVisitorExpect2(String visitorExpect2) {
        this.visitorExpect2 = visitorExpect2;
    }

    public String getVisitorExpect3() {
        return visitorExpect3;
    }

    public void setVisitorExpect3(String visitorExpect3) {
        this.visitorExpect3 = visitorExpect3;
    }

    public String getVisitorLeadership() {
        return visitorLeadership;
    }

    public void setVisitorLeadership(String visitorLeadership) {
        this.visitorLeadership = visitorLeadership;
    }

    public String getVisitorLeadership2() {
        return visitorLeadership2;
    }

    public void setVisitorLeadership2(String visitorLeadership2) {
        this.visitorLeadership2 = visitorLeadership2;
    }

    public String getVisitorLeadership3() {
        return visitorLeadership3;
    }

    public void setVisitorLeadership3(String visitorLeadership3) {
        this.visitorLeadership3 = visitorLeadership3;
    }

    public String getVisitorSalary() {
        return visitorSalary;
    }

    public void setVisitorSalary(String visitorSalary) {
        this.visitorSalary = visitorSalary;
    }

    public String getVisitorSalary2() {
        return visitorSalary2;
    }

    public void setVisitorSalary2(String visitorSalary2) {
        this.visitorSalary2 = visitorSalary2;
    }

    public String getVisitorSalary3() {
        return visitorSalary3;
    }

    public void setVisitorSalary3(String visitorSalary3) {
        this.visitorSalary3 = visitorSalary3;
    }

    public String getVisitorLeaveReason() {
        return visitorLeaveReason;
    }

    public void setVisitorLeaveReason(String visitorLeaveReason) {
        this.visitorLeaveReason = visitorLeaveReason;
    }

    public String getVisitorLeaveReason2() {
        return visitorLeaveReason2;
    }

    public void setVisitorLeaveReason2(String visitorLeaveReason2) {
        this.visitorLeaveReason2 = visitorLeaveReason2;
    }

    public String getVisitorLeaveReason3() {
        return visitorLeaveReason3;
    }

    public void setVisitorLeaveReason3(String visitorLeaveReason3) {
        this.visitorLeaveReason3 = visitorLeaveReason3;
    }

    public String getVisitorAdvantage2() {
        return visitorAdvantage2;
    }

    public void setVisitorAdvantage2(String visitorAdvantage2) {
        this.visitorAdvantage2 = visitorAdvantage2;
    }

    public String getVisitorDefect2() {
        return visitorDefect2;
    }

    public void setVisitorDefect2(String visitorDefect2) {
        this.visitorDefect2 = visitorDefect2;
    }

    public String getVisitorAdvantage3() {
        return visitorAdvantage3;
    }

    public void setVisitorAdvantage3(String visitorAdvantage3) {
        this.visitorAdvantage3 = visitorAdvantage3;
    }

    public String getVisitorDefect3() {
        return visitorDefect3;
    }

    public void setVisitorDefect3(String visitorDefect3) {
        this.visitorDefect3 = visitorDefect3;
    }

 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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

    public String getVisitor() {
        return StringUtils.isEmpty(visitor) ? "" : visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

 

    public String getVisitorDepartment() {
        if (StringUtils.isEmpty(visitorDepartment)) {
            return "";
        }
        return visitorDepartment;
    }

    public void setVisitorDepartment(String visitorDepartment) {
        this.visitorDepartment = visitorDepartment;
    }

    public String getVisitorJob() {
        return StringUtils.isEmpty(visitorJob) ? "" : visitorJob;
    }

    public void setVisitorJob(String visitorJob) {
        this.visitorJob = visitorJob;
    }

    public String getVisitorRelation() {
        return StringUtils.isEmpty(visitorRelation) ? "" : visitorRelation;
    }

    public void setVisitorRelation(String visitorRelation) {
        this.visitorRelation = visitorRelation;
    }


    public String getVisitorPhone() {
        return StringUtils.isEmpty(visitorPhone) ? "" : visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public String getName() {
        return StringUtils.isEmpty(name) ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNameVerify() {
        return nameVerify;
    }

    public void setNameVerify(Integer nameVerify) {
        this.nameVerify = nameVerify;
    }

    public String getNameVerifyExplain() {
        return StringUtils.isEmpty(nameVerifyExplain) ? "" : nameVerifyExplain;
    }

    public void setNameVerifyExplain(String nameVerifyExplain) {
        this.nameVerifyExplain = nameVerifyExplain;
    }


    public Integer getEntryVerify() {
        return entryVerify;
    }

    public void setEntryVerify(Integer entryVerify) {
        this.entryVerify = entryVerify;
    }

    public String getEntryVerifyExplain() {
        return StringUtils.isEmpty(entryVerifyExplain) ? "" : entryVerifyExplain;
    }

    public void setEntryVerifyExplain(String entryVerifyExplain) {
        this.entryVerifyExplain = entryVerifyExplain;
    }


    public Integer getLeaveVerify() {
        return leaveVerify;
    }

    public void setLeaveVerify(Integer leaveVerify) {
        this.leaveVerify = leaveVerify;
    }

    public Integer getWorkingLlife() {
        return workingLlife;
    }

    public void setWorkingLlife(Integer workingLlife) {
        this.workingLlife = workingLlife;
    }

    public String getLeaveVerifyExplain() {
        return StringUtils.isEmpty(leaveVerifyExplain) ? "" : leaveVerifyExplain;

    }

    public void setLeaveVerifyExplain(String leaveVerifyExplain) {
        this.leaveVerifyExplain = leaveVerifyExplain;
    }

    public Integer getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(Integer leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Integer getLeaveReasonVerify() {
        return leaveReasonVerify;
    }

    public void setLeaveReasonVerify(Integer leaveReasonVerify) {
        this.leaveReasonVerify = leaveReasonVerify;
    }

    public String getLeaveReasonVerifyExplain() {
        return StringUtils.isEmpty(leaveReasonVerifyExplain) ? "" : leaveReasonVerifyExplain;
    }

    public void setLeaveReasonVerifyExplain(String leaveReasonVerifyExplain) {
        this.leaveReasonVerifyExplain = leaveReasonVerifyExplain;
    }

    public String getMonthSalary() {
        return StringUtils.isEmpty(monthSalary) ? "" : monthSalary;
    }

    public void setMonthSalary(String monthSalary) {
        this.monthSalary = monthSalary;
    }

    public Integer getMonthSalaryVerify() {
        return monthSalaryVerify;
    }

    public void setMonthSalaryVerify(Integer monthSalaryVerify) {
        this.monthSalaryVerify = monthSalaryVerify;
    }

    public String getMonthSalaryVerifyExplain() {
        return StringUtils.isEmpty(monthSalaryVerifyExplain) ? "" : monthSalaryVerifyExplain;
    }

    public void setMonthSalaryVerifyExplain(String monthSalaryVerifyExplain) {
        this.monthSalaryVerifyExplain = monthSalaryVerifyExplain;
    }

    public String getYearSalary() {
        return StringUtils.isEmpty(yearSalary) ? "" : yearSalary;
    }

    public void setYearSalary(String yearSalary) {
        this.yearSalary = yearSalary;
    }

    public Integer getYearSalaryVerify() {
        return yearSalaryVerify;
    }

    public void setYearSalaryVerify(Integer yearSalaryVerify) {
        this.yearSalaryVerify = yearSalaryVerify;
    }

    public String getYearSalaryVerifyExplain() {
        return StringUtils.isEmpty(yearSalaryVerifyExplain) ? "" : yearSalaryVerifyExplain;
    }

    public void setYearSalaryVerifyExplain(String yearSalaryVerifyExplain) {
        this.yearSalaryVerifyExplain = yearSalaryVerifyExplain;
    }

    public String getJob() {
        return StringUtils.isEmpty(job) ? "" : job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getJobVerify() {
        return jobVerify;
    }

    public void setJobVerify(Integer jobVerify) {
        this.jobVerify = jobVerify;
    }

    public String getJobVerifyExplain() {
        return StringUtils.isEmpty(jobVerifyExplain) ? "" : jobVerifyExplain;
    }

    public void setJobVerifyExplain(String jobVerifyExplain) {
        this.jobVerifyExplain = jobVerifyExplain;
    }

    public String getDuty() {
        return StringUtils.isEmpty(duty) ? "" : duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Integer getDutyVerify() {
        return dutyVerify;
    }

    public void setDutyVerify(Integer dutyVerify) {
        this.dutyVerify = dutyVerify;
    }

    public String getDutyVerifyExplain() {
        return StringUtils.isEmpty(dutyVerifyExplain) ? "" : dutyVerifyExplain;
    }

    public void setDutyVerifyExplain(String dutyVerifyExplain) {
        this.dutyVerifyExplain = dutyVerifyExplain;
    }

    public String getSuperior() {
        return StringUtils.isEmpty(superior) ? "" : superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public Integer getSuperiorVerify() {
        return superiorVerify;
    }

    public void setSuperiorVerify(Integer superiorVerify) {
        this.superiorVerify = superiorVerify;
    }

    public String getSuperiorVerifyExplain() {
        return StringUtils.isEmpty(superiorVerifyExplain) ? "" : superiorVerifyExplain;
    }

    public void setSuperiorVerifyExplain(String superiorVerifyExplain) {
        this.superiorVerifyExplain = superiorVerifyExplain;
    }

    public Integer getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(Integer subordinate) {
        this.subordinate = subordinate;
    }

    public Integer getSubordinateVerify() {
        return subordinateVerify;
    }

    public void setSubordinateVerify(Integer subordinateVerify) {
        this.subordinateVerify = subordinateVerify;
    }

    public String getSubordinateVerifyExplain() {
        return StringUtils.isEmpty(subordinateVerifyExplain) ? "" : subordinateVerifyExplain;
    }

    public void setSubordinateVerifyExplain(String subordinateVerifyExplain) {
        this.subordinateVerifyExplain = subordinateVerifyExplain;
    }

    public Integer getDisputeExist() {
        return disputeExist;
    }

    public void setDisputeExist(Integer disputeExist) {
        this.disputeExist = disputeExist;
    }

    public Integer getDisputeRelieve() {
        return disputeRelieve;
    }

    public void setDisputeRelieve(Integer disputeRelieve) {
        this.disputeRelieve = disputeRelieve;
    }

    public Integer getIllegality() {
        return illegality;
    }

    public void setIllegality(Integer illegality) {
        this.illegality = illegality;
    }

    public Integer getCompetitionExist() {
        return competitionExist;
    }

    public void setCompetitionExist(Integer competitionExist) {
        this.competitionExist = competitionExist;
    }

    public Integer getCompetitionRelieve() {
        return competitionRelieve;
    }

    public void setCompetitionRelieve(Integer competitionRelieve) {
        this.competitionRelieve = competitionRelieve;
    }


    public Integer getContractExist() {
        return contractExist;
    }

    public void setContractExist(Integer contractExist) {
        this.contractExist = contractExist;
    }

    public Integer getContractRelieve() {
        return contractRelieve;
    }

    public void setContractRelieve(Integer contractRelieve) {
        this.contractRelieve = contractRelieve;
    }


    public String getVisitor2() {
        return StringUtils.isEmpty(visitor2) ? "" : visitor2;
    }

    public void setVisitor2(String visitor2) {
        this.visitor2 = visitor2;
    }


    public String getVisitorDepartment2() {
        return visitorDepartment2;
    }

    public void setVisitorDepartment2(String visitorDepartment2) {
        this.visitorDepartment2 = visitorDepartment2;
    }

    public String getVisitorJob2() {
        return StringUtils.isEmpty(visitor2) ? "" : visitorJob2;
    }

    public void setVisitorJob2(String visitorJob2) {
        this.visitorJob2 = visitorJob2;
    }

    public String getVisitorRelation2() {
        return StringUtils.isEmpty(visitorRelation2) ? "" : visitorRelation2;
    }

    public void setVisitorRelation2(String visitorRelation2) {
        this.visitorRelation2 = visitorRelation2;
    }


    public String getVisitorPhone2() {
        return visitorPhone2;
    }

    public void setVisitorPhone2(String visitorPhone2) {
        this.visitorPhone2 = visitorPhone2;
    }

    public String getAdvantage() {
        return StringUtils.isEmpty(advantage) ? "" : advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getDefect() {
        return StringUtils.isEmpty(defect) ? "" : defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getLeaderAbility() {
        return StringUtils.isEmpty(leaderAbility) ? "" : leaderAbility;
    }

    public void setLeaderAbility(String leaderAbility) {
        this.leaderAbility = leaderAbility;
    }

    public Integer getLeadership() {
        return leadership;
    }

    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
    }

    public String getCoordinateAbility() {
        return StringUtils.isEmpty(coordinateAbility) ? "" : coordinateAbility;
    }

    public void setCoordinateAbility(String coordinateAbility) {
        this.coordinateAbility = coordinateAbility;
    }

    public Integer getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Integer coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public Integer getSincerity() {
        return sincerity;
    }

    public void setSincerity(Integer sincerity) {
        this.sincerity = sincerity;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Integer getStudy() {
        return study;
    }

    public void setStudy(Integer study) {
        this.study = study;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCooperation() {
        return cooperation;
    }

    public void setCooperation(Integer cooperation) {
        this.cooperation = cooperation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerformanceDesc() {
        return performanceDesc;
    }

    public void setPerformanceDesc(String performanceDesc) {
        this.performanceDesc = performanceDesc;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getCanVerify() {
        return canVerify;
    }

    public void setCanVerify(Integer canVerify) {
        this.canVerify = canVerify;
    }


    public String getVisitor3() {
        return visitor3;
    }

    public void setVisitor3(String visitor3) {
        this.visitor3 = visitor3;
    }

    public String getVisitorJob3() {
        return visitorJob3;
    }

    public void setVisitorJob3(String visitorJob3) {
        this.visitorJob3 = visitorJob3;
    }

    public String getVisitorPhone3() {
		return visitorPhone3;
	}

	public void setVisitorPhone3(String visitorPhone3) {
		this.visitorPhone3 = visitorPhone3;
	}

	public String getVisitorMail3() {
        return visitorMail3;
    }

    public void setVisitorMail3(String visitorMail3) {
        this.visitorMail3 = visitorMail3;
    }

    public String getVisitorMail2() {
        return visitorMail2;
    }

    public void setVisitorMail2(String visitorMail2) {
        this.visitorMail2 = visitorMail2;
    }

    public String getVisitorMail() {
        return visitorMail;
    }

    public void setVisitorMail(String visitorMail) {
        this.visitorMail = visitorMail;
    }

    public Integer getWorkMode() {
        return workMode;
    }

    public void setWorkMode(Integer workMode) {
        this.workMode = workMode;
    }

    public String getVisitorRelation3() {
        return visitorRelation3;
    }

    public void setVisitorRelation3(String visitorRelation3) {
        this.visitorRelation3 = visitorRelation3;
    }

    public String getVisitorWorkTogether() {
        return visitorWorkTogether;
    }

    public void setVisitorWorkTogether(String visitorWorkTogether) {
        this.visitorWorkTogether = visitorWorkTogether;
    }

    public String getVisitorWorkTogether2() {
        return visitorWorkTogether2;
    }

    public void setVisitorWorkTogether2(String visitorWorkTogether2) {
        this.visitorWorkTogether2 = visitorWorkTogether2;
    }

    public String getVisitorWorkTogether3() {
        return visitorWorkTogether3;
    }

    public void setVisitorWorkTogether3(String visitorWorkTogether3) {
        this.visitorWorkTogether3 = visitorWorkTogether3;
    }

    public String getVisitorScore() {
        return visitorScore;
    }

    public void setVisitorScore(String visitorScore) {
        this.visitorScore = visitorScore;
    }

    public String getVisitorScore2() {
        return visitorScore2;
    }

    public void setVisitorScore2(String visitorScore2) {
        this.visitorScore2 = visitorScore2;
    }

    public String getVisitorScore3() {
        return visitorScore3;
    }

    public void setVisitorScore3(String visitorScore3) {
        this.visitorScore3 = visitorScore3;
    }
    
    public String getVisitorDate() {
		return visitorDate;
	}

	public void setVisitorDate(String visitorDate) {
		this.visitorDate = visitorDate;
	}

	public String getVisitorEntry() {
		return visitorEntry;
	}

	public void setVisitorEntry(String visitorEntry) {
		this.visitorEntry = visitorEntry;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getCompetitionDate() {
		return competitionDate;
	}

	public void setCompetitionDate(String competitionDate) {
		this.competitionDate = competitionDate;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getVisitorDate2() {
		return visitorDate2;
	}

	public void setVisitorDate2(String visitorDate2) {
		this.visitorDate2 = visitorDate2;
	}

	public String getVisitorEntry2() {
		return visitorEntry2;
	}

	public void setVisitorEntry2(String visitorEntry2) {
		this.visitorEntry2 = visitorEntry2;
	}

	public String getCanVerifyDate() {
		return canVerifyDate;
	}

	public void setCanVerifyDate(String canVerifyDate) {
		this.canVerifyDate = canVerifyDate;
	}

	public String getVisitorDate3() {
		return visitorDate3;
	}

	public void setVisitorDate3(String visitorDate3) {
		this.visitorDate3 = visitorDate3;
	}

	public String getVisitorEntry3() {
		return visitorEntry3;
	}

	public void setVisitorEntry3(String visitorEntry3) {
		this.visitorEntry3 = visitorEntry3;
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
