package com.hanergy.reportForms.dto.third;

import com.hanergy.reportForms.entity.template.CheckItemEntity;

import java.util.List;

/**
 * @ClassName MediaAgencyPlan
 * @Description TODO 方案中介
 * @Author DURONGHONG
 * @DATE 2018/10/9 17:54
 * @Version 1.0
 **/
public class MediaAgencyPlan {

    // 方案中介id
    private Long id;
    // 中介类型
    private Integer agencyType;

    private List<CheckItemEntity> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(Integer agencyType) {
        this.agencyType = agencyType;
    }

    public List<CheckItemEntity> getItems() {
        return items;
    }

    public void setItems(List<CheckItemEntity> items) {
        this.items = items;
    }
}
