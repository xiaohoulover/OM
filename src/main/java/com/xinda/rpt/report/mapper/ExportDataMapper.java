package com.xinda.rpt.report.mapper;

import com.xinda.rpt.report.dto.ExportDataProperty;

import java.util.List;
import java.util.Map;

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
     * @param requestParams 参数集合
     * @return
     */
    List<ExportDataProperty> getSummaryTableData(Map<String, Object> requestParams);

    /**
     * 导出调度记录数据.
     *
     * @param requestParams 报表参数集合
     * @return
     */
    List<ExportDataProperty> getSchedulingRecords(Map<String, Object> requestParams);

    /**
     * @param requestParams 报表参数集合
     * @return
     */
    List<ExportDataProperty> getStatementTableData(Map<String, Object> requestParams);

}
