package com.xinda.rpt.report.controller;

import com.xinda.rpt.report.dto.ReportParamDto;
import com.xinda.rpt.report.service.IExportDataService;
import com.xinda.system.sys.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Excel数据导出.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:10
 */
@Controller
@RequestMapping("/export")
public class ExportDataController extends BaseController {


    @Autowired
    private IExportDataService exportDataService;

    /**
     * 导出Excel数据.
     *
     * @param request
     * @param response
     * @param reportParamDto 参数对象
     * @throws IOException
     */
    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void exportExcelData(HttpServletRequest request,
                                HttpServletResponse response,
                                ReportParamDto reportParamDto) throws IOException {
        exportDataService.exportExcelData(request, response, reportParamDto);
    }

}