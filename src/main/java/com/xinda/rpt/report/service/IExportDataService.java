package com.xinda.rpt.report.service;

import com.xinda.rpt.report.dto.ReportParamDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Excel数据导出.
 *
 * @Author Coundy.
 * @Date 2017/3/27 23:11
 */
public interface IExportDataService {





    /**
     * 导出Excel数据.
     *
     * @param request
     * @param response
     * @param reportParamDto 参数对象
     * @throws IOException
     */
    public void exportExcelData(HttpServletRequest request, HttpServletResponse response, ReportParamDto reportParamDto)
            throws IOException;

    /**
     * 导出总表数据.
     *
     * @param request
     * @param response
     * @param reportParamDto 参数对象
     */
    public void exportSummaryTableData(HttpServletRequest request, HttpServletResponse response, ReportParamDto reportParamDto)
            throws IOException;

    /**
     * 对账单数据导出.
     *
     * @param request
     * @param response
     * @param reportParamDto
     * @throws IOException 参数对象
     */
    public void exportStatementTableData(HttpServletRequest request, HttpServletResponse response, ReportParamDto reportParamDto)
            throws IOException;

    /**
     * 调度记录导出.
     *
     * @param request
     * @param response
     * @param reportParamDto
     * @throws IOException 参数对象
     */
    public void exportSchedulingRecords(HttpServletRequest request, HttpServletResponse response, ReportParamDto reportParamDto)
            throws IOException;


}
