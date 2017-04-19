package com.xinda.rpt.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
     * @param requestParams 参数集合
     * @throws IOException
     */
    public void exportExcelData(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams)
            throws IOException;

    /**
     * 导出总表数据.
     *
     * @param request
     * @param response
     * @param requestParams 参数集合
     */
    public void exportSummaryTableData(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams)
            throws IOException;

    /**
     * 对账单数据导出.
     *
     * @param request
     * @param response
     * @param requestParams 参数集合
     * @throws IOException
     */
    public void exportStatementTableData(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams)
            throws IOException;

    /**
     * 调度记录导出.
     *
     * @param request
     * @param response
     * @param requestParams 参数集合
     * @throws IOException
     */
    public void exportSchedulingRecords(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams)
            throws IOException;


}
