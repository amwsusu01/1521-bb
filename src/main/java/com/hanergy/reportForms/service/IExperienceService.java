package com.hanergy.reportForms.service;

import com.hanergy.reportForms.mapper.entity.Experience;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工作经历 服务类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
public interface IExperienceService extends IService<Experience> {

    /**
     * 查询工作经历,附带背调信息
     *
     * @param experienceId
     * @return
     */
    Experience getExperienceWithCheckInfo(Long experienceId);

    /**
     * 通过候选人查询工作经历
     *
     * @param beSelectedId
     * @return
     */
    List<Experience> getExperienceByBeSelectId(Long beSelectedId);

}
