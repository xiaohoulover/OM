package com.xinda.fm.file.dto;

import com.xinda.system.sys.dto.BaseDto;

import javax.persistence.Id;

/**
 * 文件对象Dto.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:31
 */
public class FileManagerDto extends BaseDto {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id.
     */
    @Id
    private Integer fileId;

    /**
     * 文件名.
     */
    private String fileName;

    /**
     * 文件路径.
     */
    private String filePath;

    /**
     * 关联订单id.
     */
    private Integer salesOrderId;

    /**
     * 文件类型.
     */
    private String fileType;

    /**
     * 文件大小.
     */
    private Long fileLength;

    //Add Columns
    /**
     * 订单编号.
     */
    private String orderNumber;

    public FileManagerDto() {
    }

    public FileManagerDto(String fileName, String filePath, Integer salesOrderId, String fileType, Long fileLength) {
        super();
        this.fileName = fileName;
        this.filePath = filePath;
        this.salesOrderId = salesOrderId;
        this.fileType = fileType;
        this.fileLength = fileLength;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
