package com.hanergy.reportForms.VO;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName AgencyRequest
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/26 10:48
 * @Version 1.0
 **/
public class AgencyRequest implements Serializable {


    private Long id;

    private List<AgencyVO> agencys;

    public List<AgencyVO> getAgencys() {
        return agencys;
    }

    public void setAgencys(List<AgencyVO> agencys) {
        this.agencys = agencys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(hidden = true)
    public boolean isNull() {
        return this.id != null && !CollectionUtils.isEmpty(agencys);
    }
}
