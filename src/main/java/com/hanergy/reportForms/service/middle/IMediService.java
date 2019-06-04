package com.hanergy.reportForms.service.middle;

import com.hanergy.reportForms.dto.third.MediCadidate;
import com.hanergy.reportForms.entity.entrust.report.UpLoadReportEntity;
import com.hanergy.reportForms.entity.template.CheckItemEntity;
import com.hanergy.reportForms.mapper.entity.Check;
import com.hanergy.reportForms.mapper.entity.User;

import java.util.List;

/**
 * 中介服务接口类
 */
public interface IMediService {

    /**
     * 上传报告,获取中介生成的订单id
     *
     * @param cadidate   SF系统候选人的简历信息
     * @param checkItems 候选人对应方案的检查项目
     * @return 第三方生成的订单编号
     */
    LjbdResult uploadReportToAgency(MediCadidate cadidate, List<CheckItemEntity> checkItems) throws Exception;

    /**
     * 根据中介提供的订单id,获取报告
     *
     * @param orderId 第三方订单编号
     * @return 背调报告
     */
    UpLoadReportEntity getReportFromAgency(String orderId, User user, Check check) throws Exception;

    /**
     * 获取报告状态
     *
     * @param order
     * @return
     */
    ReportStatusResult getReportStatus(String order) throws Exception;

}
