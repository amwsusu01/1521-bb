package com.hanergy.reportForms.VO;

/**
 * @ClassName BeSelectedRequest
 * @Description TODO 候选人列表请求参数
 * @Author DURONGHONG
 * @DATE 2018/9/29 15:44
 * @Version 1.0
 **/
public class BeSelectedRequest {

    private String name;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
