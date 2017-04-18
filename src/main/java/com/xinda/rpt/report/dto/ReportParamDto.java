package com.xinda.rpt.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinda.system.sys.contant.BaseConstants;

import java.util.Date;

/**
 * 导出报表参数对象.
 *
 * @Author Coundy.
 * @Date 2017/4/14 18:04.
 */
public class ReportParamDto {

    /**
     * 类型.
     */
    private String reportType;

    /**
     * 起始日期.
     */
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT, timezone = BaseConstants.TIME_ZONE)
    private Date startDate;
    /**
     * 终止日期.
     */
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT, timezone = BaseConstants.TIME_ZONE)
    private Date endDate;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ReportParamDto{" +
                "reportType='" + reportType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
