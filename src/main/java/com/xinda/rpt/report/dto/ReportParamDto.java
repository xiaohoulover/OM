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
     * 报表名称.
     */
    private String reportName;
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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
                "reportName='" + reportName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
