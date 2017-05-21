package com.xinda.rpt.report.service.impl;

import com.xinda.rpt.report.dto.ExportDataProperty;
import com.xinda.rpt.report.mapper.ExportDataMapper;
import com.xinda.rpt.report.service.IExportDataService;
import com.xinda.rpt.report.util.ExportReportUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Excel数据导出实现类.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:09
 */
@Service
@Transactional
public class ExportDataServiceImpl implements IExportDataService {

    private final Logger logger = LoggerFactory.getLogger(ExportDataServiceImpl.class);

    @Autowired
    private ExportDataMapper exportDataMapper;

    @Override
    public void exportExcelData(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams)
            throws IOException {
        switch (String.valueOf(requestParams.get("reportCode"))) {
            case "SUM"://总表
                exportSummaryTableData(request, response, requestParams);
                break;
            case "STM"://对账单
                exportStatementTableData(request, response, requestParams);
                break;
            case "SDR"://调度记录表
                exportSchedulingRecords(request, response, requestParams);
                break;
            default:
                break;
        }
    }

    @Override
    public void exportSummaryTableData(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams)
            throws IOException {
        logger.info("Start Export Summary.xls.Params:[{}]", requestParams.toString());
        //获取数据
        List<ExportDataProperty> exportDataPropertyList = exportDataMapper.getSummaryTableData(requestParams);
        //标题栏
        String[] headers = {"序号", "订单编号", "实际运输日期", "客户名称", "业务类型", "收货方", "货物名称", "提运单号",
                "交易单号", "罐柜号码", "出货重量", "签收重量", "磅差(K-J)*1000", "差异率", "车头编号", "车板编号", "司机",
                "押运员", "运输路径", "区间路程", "区间路桥费", "核定油耗（L）"};
        //1.创建WorkBook
        HSSFWorkbook work = new HSSFWorkbook();
        //2,创建Sheet
        HSSFSheet sheet = work.createSheet("总表");

        HSSFWorkbook workbook = ExportReportUtils.designExcelReportStyle(work, "广州番禺欣达运输有限公司-统计表", headers, exportDataPropertyList);
        //字体样式
        Font col1Font = workbook.createFont();
        col1Font.setFontName("宋体");   //字体类型
        col1Font.setFontHeightInPoints((short) 9);//字体大小
        col1Font.setColor(HSSFColor.RED.index); //字体颜色
        //格式化
        HSSFDataFormat format = workbook.createDataFormat();
        HSSFCellStyle cellDateStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle cellNumStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle cellStringStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < exportDataPropertyList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 2);
            for (int j = 0; j < headers.length; j++) {
                HSSFCell lineCell = row.createCell(j);
                if (2 == j) {
                    cellDateStyle.setDataFormat(format.getFormat("yyyy/m/d"));
                    lineCell.setCellStyle(cellDateStyle);
                } else if (10 == j || 11 == j || 12 == j || 13 == j || 19 == j || 20 == j || 21 == j) {
                    cellNumStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                    lineCell.setCellStyle(cellNumStyle);
                } else {
                    cellStringStyle.setDataFormat(format.getFormat("@"));
                    lineCell.setCellStyle(cellStringStyle);
                }
                switch (j) {
                    case 0:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getRowNum());
                        break;
                    case 1:
                        HSSFRichTextString richString = new HSSFRichTextString(exportDataPropertyList.get(i).getOrderNumber());
                        richString.applyFont(col1Font);
                        lineCell.setCellValue(richString);
                        break;
                    case 2:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getShippingDate());
                        break;
                    case 3:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getCustomerName());
                        break;
                    case 4:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getBusinessType());
                        break;
                    case 5:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getReceiver());
                        break;
                    case 6:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getItemName());
                        break;
                    case 7:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getWayBillNo());
                        break;
                    case 8:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getTransactionNo());
                        break;
                    case 9:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getTankNo());
                        break;
                    case 10:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getShipWeight().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 11:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getReceiptWeight().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 12:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getPoundsWorse().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 13:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getDifferenceRate().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 14:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getCarFrontNo());
                        break;
                    case 15:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getCarPlateNo());
                        break;
                    case 16:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getDriverName());
                        break;
                    case 17:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getEscortName());
                        break;
                    case 18:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getShipPath());
                        break;
                    case 19:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getDistance().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 20:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getRoadBridgeFee().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 21:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getFuelCosts().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    default:
                        break;
                }
            }
        }
        //设置单元格宽度
        ExportReportUtils.designAutoColumnWidth(sheet, 1, sheet.getLastRowNum(), headers.length);
        //输出
        OutputStream os = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=Summary.xls");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        workbook.write(os);
        os.flush();
        os.close();
    }

    @Override
    public void exportStatementTableData(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams)
            throws IOException {
        logger.info("Start Export StatementOfAccount.xls.Params:[{}]", requestParams.toString());
        //获取数据","
        List<ExportDataProperty> exportDataPropertyList = exportDataMapper.getStatementTableData(requestParams);
        String[] headers = {"序号", "订单编号", "日期", "提单号", "集装箱号", "业务类型", "货物名称", "出货重量", "签收重量", "压车费", "加热费", "报关费",
                "换单费", "码头堆存费", "代垫海运费", "过磅费", "罐柜维修费", "吊箱费", "标签费", "其他费用", "", "业务价格", "总费用", "备注", "工作号"};
        //1.创建WorkBook
        HSSFWorkbook work = new HSSFWorkbook();
        //2,创建Sheet
        HSSFSheet sheet = work.createSheet("对账表");
        String titleName = String.valueOf(requestParams.get("customerName"));
        HSSFWorkbook workbook = ExportReportUtils.designExcelReportStyle(work, titleName + "-对账单", headers, exportDataPropertyList);
        //格式化
        HSSFDataFormat format = workbook.createDataFormat();
        HSSFCellStyle cellDateStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle cellNumStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle cellStringStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < exportDataPropertyList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 2);
            for (int j = 0; j < headers.length; j++) {
                HSSFCell lineCell = row.createCell(j);
                if (2 == j) {
                    cellDateStyle.setDataFormat(format.getFormat("yyyy/m/d"));
                    lineCell.setCellStyle(cellDateStyle);
                } else if (j >= 6 && j <= 19) {
                    cellNumStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                    lineCell.setCellStyle(cellNumStyle);
                } else {
                    cellStringStyle.setDataFormat(format.getFormat("@"));
                    lineCell.setCellStyle(cellStringStyle);
                }
                //代垫费用
                String[] disbursementType = {};
                if (null != exportDataPropertyList.get(i).getDisbursementType()) {
                    disbursementType = exportDataPropertyList.get(i).getDisbursementType().split(";");
                }
                switch (j) {
                    case 0:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getRowNum());
                        break;
                    case 1:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getOrderNumber());
                        break;
                    case 2:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getShippingDate());
                        break;
                    case 3:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getWayBillNo());
                        break;
                    case 4:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getTankNo());
                        break;
                    case 5:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getBusinessType());
                        break;
                    case 6:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getItemName());
                        break;
                    case 7:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getShipWeight().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 8:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getReceiptWeight().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 9:
                        for (String s : disbursementType) {
                            if ("FC".equals(s.substring(0, 2))) {
                                lineCell.setCellValue(s.substring(2));
                                break;
                            }
                        }
                        break;
                    case 10:
                        for (String s : disbursementType) {
                            if ("HF".equals(s.substring(0, 2))) {
                                lineCell.setCellValue(s.substring(2));
                                break;
                            }
                        }
                        break;
                    case 11:
                        for (String s : disbursementType) {
                            if ("CF".equals(s.substring(0, 2))) {
                                lineCell.setCellValue(s.substring(2));
                                break;
                            }
                        }
                        break;
                    case 12:
                        for (String s : disbursementType) {
                            if ("FSF".equals(s.substring(0, 3))) {
                                lineCell.setCellValue(s.substring(3));
                                break;
                            }
                        }
                        break;
                    case 13:
                        for (String s : disbursementType) {
                            if ("TSF".equals(s.substring(0, 3))) {
                                lineCell.setCellValue(s.substring(3));
                                break;
                            }
                        }
                        break;
                    case 14:
                        for (String s : disbursementType) {
                            if ("BSF".equals(s.substring(0, 3))) {
                                lineCell.setCellValue(s.substring(3));
                                break;
                            }
                        }
                        break;
                    case 15:
                        for (String s : disbursementType) {
                            if ("WF".equals(s.substring(0, 2))) {
                                lineCell.setCellValue(s.substring(2));
                                break;
                            }
                        }
                        break;
                    case 16:
                        break;
                    case 17:
                        for (String s : disbursementType) {
                            if ("TMF".equals(s.substring(0, 3))) {
                                lineCell.setCellValue(s.substring(3));
                                break;
                            }
                        }
                        break;
                    case 18:
                        for (String s : disbursementType) {
                            if ("HBF".equals(s.substring(0, 3))) {
                                lineCell.setCellValue(s.substring(3));
                                break;
                            }
                        }
                        break;
                    case 19:
                        for (String s : disbursementType) {
                            if ("TF".equals(s.substring(0, 2))) {
                                lineCell.setCellValue(s.substring(2));
                                break;
                            }
                        }
                        break;
                    case 20:
                        for (String s : disbursementType) {
                            if ("OT".equals(s.substring(0, 2))) {
                                lineCell.setCellValue(s.substring(2));
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        //输出
        OutputStream os = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=StatementOfAccount.xls");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        workbook.write(os);
        os.flush();
        os.close();

    }

    @Override
    public void exportSchedulingRecords(HttpServletRequest request, HttpServletResponse response, Map<String, Object> requestParams) throws IOException {
        logger.info("Start Export SchedulingRecords.xls.Params:[{}]", requestParams.toString());
        //获取数据
        List<ExportDataProperty> exportDataPropertyList = exportDataMapper.getSchedulingRecords(requestParams);
        String[] headers = {"序号", "日期", "订单编号", "司机姓名", "押运员姓名", "车牌", "客户名称",
                "业务类型", "区间路径", "区间路桥费", "区间路程（KM）", "核定油耗(L)"};
        //1.创建WorkBook
        HSSFWorkbook work = new HSSFWorkbook();
        //2,创建Sheet
        HSSFSheet sheet = work.createSheet("统计表");
        HSSFWorkbook workbook = ExportReportUtils.designExcelReportStyle(work, "广州番禺欣达运输有限公司-调度记录表", headers, exportDataPropertyList);
        //第一列字体样式
        Font col1Font = workbook.createFont();
        col1Font.setFontName("宋体");   //字体类型
        col1Font.setFontHeightInPoints((short) 9);//字体大小
        col1Font.setColor(HSSFColor.RED.index); //字体颜色
        //格式化
        HSSFDataFormat format = workbook.createDataFormat();
        HSSFCellStyle cellDateStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle cellNumStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle cellStringStyle = ExportReportUtils.designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < exportDataPropertyList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 2);
            for (int j = 0; j < headers.length; j++) {
                HSSFCell lineCell = row.createCell(j);
                if (1 == j) {
                    cellDateStyle.setDataFormat(format.getFormat("yyyy/m/d"));
                    lineCell.setCellStyle(cellDateStyle);
                } else if (9 == j || 10 == j || 11 == j) {
                    cellNumStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                    lineCell.setCellStyle(cellNumStyle);
                } else {
                    cellStringStyle.setDataFormat(format.getFormat("@"));
                    lineCell.setCellStyle(cellStringStyle);
                }
                switch (j) {
                    case 0:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getRowNum());
                        break;
                    case 1:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getShippingDate());
                        break;
                    case 2:
                        HSSFRichTextString richString = new HSSFRichTextString(exportDataPropertyList.get(i).getOrderNumber());
                        richString.applyFont(col1Font);
                        lineCell.setCellValue(richString);
                        break;
                    case 3:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getDriverName());
                        break;
                    case 4:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getEscortName());
                        break;
                    case 5:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getCarFrontNo());
                        break;
                    case 6:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getCustomerName());
                        break;
                    case 7:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getBusinessType());
                        break;
                    case 8:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getShipPath());
                        break;
                    case 9:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getRoadBridgeFee().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 10:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getDistance().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    case 11:
                        lineCell.setCellValue(exportDataPropertyList.get(i).getFuelCosts().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        break;
                    default:
                        break;
                }
            }
        }
        //设置单元格宽度
        ExportReportUtils.designAutoColumnWidth(sheet, 1, sheet.getLastRowNum(), headers.length);
        //输出
        OutputStream os = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=SchedulingRecords.xls");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        workbook.write(os);
        os.flush();
        os.close();
    }

}
