package com.hanergy.reportForms.service;

import java.util.List;

public interface IGenerateCodeService {

    /**
     * 批量获取id
     *
     * @param number
     * @return
     */
    List<Long> generatedIds(Integer number);

    /**
     * 获取一个id
     *
     * @return
     */
     Long getSingleId();
}
