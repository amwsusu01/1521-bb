package com.hanergy.reportForms.dto.sf;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName SFPostData
 * @Description TODO sf请求接口传递参数
 * @Author DURONGHONG
 * @DATE 2018/9/20 10:58
 * @Version 1.0
 **/
@ApiModel(value = "SF系统传递数据", description = "SF系统传递数据请求参数")
public class SFPostData {

    @ApiModelProperty(value = "批量传输简历数据")
    private List<SFCadidate> resumes;

    public List<SFCadidate> getResumes() {
        return resumes;
    }

    public void setResumes(List<SFCadidate> resumes) {
        this.resumes = resumes;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
