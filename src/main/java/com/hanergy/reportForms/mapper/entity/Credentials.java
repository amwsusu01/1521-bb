package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 身份信息
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value = "身份信息", description = "身份信息")
public class Credentials extends Model<Credentials> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "12238239829392893")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人（表的主键）", example = "12323232323")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "来源：1候选人   2人力    3中介背调", example = "2")
    private Integer verifySource;

    @ApiModelProperty(value = "背调（表的主键）", example = "1212312314213")
    private Long checkId;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯", example = "1")
    private Integer verifyLevel;

    @ApiModelProperty(value = "核实说明", example = "一致")
    private String verifyExplain;

    @ApiModelProperty(value = "姓名", example = "李小龙")
    private String name;

    @ApiModelProperty(value = "姓名核实：1真   0假", example = "1")
    private Integer nameVerify;

    @ApiModelProperty(value = "姓名说明", example = "一致")
    private String nameVerifyExplain;

    @ApiModelProperty(value = "证件号码", example = "323234234234234")
    private String idcard;

    @ApiModelProperty(value = "证件号码核实：1真   0假", example = "1")
    private Integer idcardVerify;

    @ApiModelProperty(value = "证件号码说明", example = "12123123")
    private String idcardVerfifyExplain;

    @ApiModelProperty(value = "民族", example = "汉")
    private String nation;

    @ApiModelProperty(value = "民族核实：1真   0假", example = "1")
    private Integer nationVerify;

    @ApiModelProperty(value = "民族说明", example = "一致")
    private String nationVerifyExplain;

    @ApiModelProperty(value = "户籍地", example = "北京")
    private String domPlace;

    @ApiModelProperty(value = "户籍地核实：1真   0假", example = "0")
    private Integer domPlaceVerify;

    @ApiModelProperty(value = "户籍地说明", example = "信息不一致")
    private String domPlaceVerifyExplain;

    @ApiModelProperty(value = "居住地")
    private String nowPlace;

    @ApiModelProperty(value = "居住地核实,1-真,0-假")
    private Integer nowPlaceVerify;

    @ApiModelProperty(value = "居住地说明")
    private String nowPlaceVerifyExplain;

    @ApiModelProperty(value = "英文名")
    private String engName;
    @ApiModelProperty(value = "曾用名")
    private String usedName;
    @ApiModelProperty("更名日期")
    private String changeNameDate;
    @ApiModelProperty(value = "证件类型 1:身份证 2:居住证 3:签证 4:护照 5:户口本 6:军人证 7:团员证 8:党员证 9:港澳通行证 0:其他")
    private Integer documentType;
    @ApiModelProperty(value = "国籍")
    private String nationality;
    @ApiModelProperty(value = "户口管辖派出所")
    private String police;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "出生日期")
    private String birthday;
    @ApiModelProperty(value = "手机")
    private String mobilephone;
    
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
        return verifyExplain;
    }

    public void setVerifyExplain(String verifyExplain) {
        this.verifyExplain = verifyExplain;
    }

    public String getName() {
        return name;
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
        return nameVerifyExplain;
    }

    public void setNameVerifyExplain(String nameVerifyExplain) {
        this.nameVerifyExplain = nameVerifyExplain;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getIdcardVerify() {
        return idcardVerify;
    }

    public void setIdcardVerify(Integer idcardVerify) {
        this.idcardVerify = idcardVerify;
    }

    public String getIdcardVerfifyExplain() {
        return idcardVerfifyExplain;
    }

    public void setIdcardVerfifyExplain(String idcardVerfifyExplain) {
        this.idcardVerfifyExplain = idcardVerfifyExplain;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getNationVerify() {
        return nationVerify;
    }

    public void setNationVerify(Integer nationVerify) {
        this.nationVerify = nationVerify;
    }

    public String getNationVerifyExplain() {
        return nationVerifyExplain;
    }

    public void setNationVerifyExplain(String nationVerifyExplain) {
        this.nationVerifyExplain = nationVerifyExplain;
    }

    public String getDomPlace() {
        return domPlace;
    }

    public void setDomPlace(String domPlace) {
        this.domPlace = domPlace;
    }

    public Integer getDomPlaceVerify() {
        return domPlaceVerify;
    }

    public void setDomPlaceVerify(Integer domPlaceVerify) {
        this.domPlaceVerify = domPlaceVerify;
    }

    public String getDomPlaceVerifyExplain() {
        return domPlaceVerifyExplain;
    }

    public void setDomPlaceVerifyExplain(String domPlaceVerifyExplain) {
        this.domPlaceVerifyExplain = domPlaceVerifyExplain;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getNowPlace() {
        return nowPlace;
    }

    public void setNowPlace(String nowPlace) {
        this.nowPlace = nowPlace;
    }

    public Integer getNowPlaceVerify() {
        return nowPlaceVerify;
    }

    public void setNowPlaceVerify(Integer nowPlaceVerify) {
        this.nowPlaceVerify = nowPlaceVerify;
    }

    public String getNowPlaceVerifyExplain() {
        return nowPlaceVerifyExplain;
    }

    public void setNowPlaceVerifyExplain(String nowPlaceVerifyExplain) {
        this.nowPlaceVerifyExplain = nowPlaceVerifyExplain;
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

    public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

    public String getChangeNameDate() {
        return changeNameDate;
    }

    public void setChangeNameDate(String changeNameDate) {
        this.changeNameDate = changeNameDate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
