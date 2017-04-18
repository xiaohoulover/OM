package com.xinda.rpt.report.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 导出报表数据工具类.
 *
 * @Author Coundy.
 * @Date 2017/4/15 19:32.
 */
public class ExportReportUtils {

    /**
     * 设计导出到Excel表格样式.
     *
     * @param exportData 导出数据
     * @param headers    标题栏
     * @return
     */
    public static HSSFWorkbook designExcelReportStyle(HSSFWorkbook workbook, String titleName, String[] headers, List<?> exportData) {
        //设置标题
        workbook = ExportReportUtils.designHeaders(workbook, headers, titleName);
        HSSFSheet sheet = workbook.getSheetAt(0);
        //合并第一行单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, headers.length));
        return workbook;
    }

    /**
     * 设置标题栏.
     *
     * @param workbook 当前工作簿
     * @param headers  标题栏信息
     * @return
     */
    public static HSSFWorkbook designHeaders(HSSFWorkbook workbook, String[] headers, String titleName) {
        HSSFSheet sheet = workbook.getSheetAt(0);
        //3.创建行
        HSSFRow row1 = sheet.createRow(0);
        //4.创建单元格
        HSSFCell cell = row1.createCell(1);
        cell.setCellStyle(designStyle(workbook, "宋体", (short) 16, Font.BOLDWEIGHT_BOLD, HSSFCellStyle.ALIGN_LEFT));
        cell.setCellValue(titleName);

        HSSFRow rowHeader = sheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            sheet.setColumnWidth(i, 32 * 120);
            HSSFCell cellHeader = rowHeader.createCell(i);
            HSSFRichTextString title = new HSSFRichTextString(headers[i]);
            cellHeader.setCellValue(title);
            cellHeader.setCellStyle(designStyle(workbook, "宋体", (short) 9, Font.BOLDWEIGHT_BOLD, HSSFCellStyle.ALIGN_CENTER));
        }
        return workbook;
    }

    /**
     * 设计单元格样式.
     *
     * @param workbook   当前工作簿
     * @param fontName   字体名称
     * @param fontSize   字体大小
     * @param boldWeight 字体粗细
     * @param alignment  字体位置
     * @return
     */
    public static HSSFCellStyle designStyle(HSSFWorkbook workbook, String fontName, short fontSize, short boldWeight, short alignment) {
        HSSFCellStyle style = workbook.createCellStyle();
        //设置边框:
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        //设置居中
        style.setAlignment(alignment);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //设置字体
        Font font = workbook.createFont();
        font.setBoldweight(boldWeight);
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);

        style.setFont(font);
        return style;
    }

    /**
     * 设置单元格格式.
     *
     * @param workbook  当前工作簿
     * @param formatObj 单元格对象
     * @param cellStyle 样式
     * @return
     */
    public static HSSFCellStyle designDataFormat(HSSFWorkbook workbook, Object formatObj, HSSFCellStyle cellStyle, HSSFDataFormat format) {
        if (formatObj instanceof String) {//字符串
            cellStyle.setDataFormat(format.getFormat("@"));
        }
        if (formatObj instanceof Date) {//日期
            cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
        }
        if (formatObj instanceof BigDecimal) {//BigDecimal 2位小数
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        }
        return cellStyle;
    }

}
