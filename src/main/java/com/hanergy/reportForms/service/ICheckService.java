package com.hanergy.reportForms.service;

import com.hanergy.reportForms.mapper.entity.Check;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 背调信息 服务类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
public interface ICheckService extends IService<Check> {

    /**
     * 验证所有背调是否全部处于终版状态
     *
     * @param beSelected
     * @return
     */
    Boolean checkReportEnd(Long beSelected);

}
