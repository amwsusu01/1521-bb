package com.hanergy.reportForms.VO;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName CheckVO
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/28 8:48
 * @Version 1.0
 **/
public class CheckVO implements Serializable {


    @ApiModelProperty(value = "背调表主键")
    private Long id;
    @ApiModelProperty(value = "核实来源：1候选人   2人力    3中介")
    private Integer verifySource;
    @ApiModelProperty(value = "核实单位：来源为人力则为用户表主键，来源为中介则为中介表主键")
    private String verifyUnit;
    @ApiModelProperty(value = "核实单位名称")
    private String verifyName;
    @ApiModelProperty(value = "核实人员")
    private String verifyPerson;
    @ApiModelProperty(value = "核实说明")
    private String verifyExplain;
    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
    private String verifyLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(Integer verifySource) {
        this.verifySource = verifySource;
    }

    public String getVerifyUnit() {
        return verifyUnit;
    }

    public void setVerifyUnit(String verifyUnit) {
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

    public String getVerifyLevel() {
        return verifyLevel;
    }

    public void setVerifyLevel(String verifyLevel) {
        this.verifyLevel = verifyLevel;
    }
}
