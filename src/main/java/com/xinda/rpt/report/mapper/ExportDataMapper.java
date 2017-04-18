package com.xinda.rpt.report.mapper;

import com.xinda.rpt.report.dto.ExportDataProperty;
import com.xinda.rpt.report.dto.ReportParamDto;

import java.util.List;

/**
 * 报表数据导出.
 *
 * @Author Coundy.
 * @Date 2017/4/14 18:31.
 */
public interface ExportDataMapper {

    /**
     * 导出总表数据.
     *
     * @param reportParamDto 报表参数
     * @return
     */
    List<ExportDataProperty> getSummaryTableData(ReportParamDto reportParamDto);

    /**
     * 导出调度记录数据.
     *
     * @param reportParamDto 报表参数
     * @return
     */
    List<ExportDataProperty> getSchedulingRecords(ReportParamDto reportParamDto);

    /**
     * @param reportParamDto 报表参数
     * @return
     */
    List<ExportDataProperty> getStatementTableData(ReportParamDto reportParamDto);

}
