package com.hanergy.reportForms.service.middle;

import com.hanergy.reportForms.commons.utils.BaseResult;

/**
 * @ClassName ReportStatusResult
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/26 10:07
 * @Version 1.0
 **/
public class ReportStatusResult extends BaseResult {

    // -1 无
    // 1 终版
    // 0 初版
    private String reportStatus;

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }
}
