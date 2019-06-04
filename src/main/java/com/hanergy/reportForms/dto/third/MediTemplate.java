package com.hanergy.reportForms.dto.third;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @ClassName MediTemplate
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/9 16:55
 * @Version 1.0
 **/
public class MediTemplate {

    private Long id;
    private String code;
    private String name;

    private List<MediaAgencyPlan> plans;

    public List<MediaAgencyPlan> getPlans() {
        return plans;
    }

    public void setPlans(List<MediaAgencyPlan> plans) {
        this.plans = plans;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
