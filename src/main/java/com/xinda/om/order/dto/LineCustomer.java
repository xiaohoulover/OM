package com.xinda.om.order.dto;

import com.xinda.system.sys.dto.BaseDto;

import java.math.BigDecimal;

/**
 * 订单-行上客户信息.
 *
 * @Author Coundy.
 * @Date 2017/4/12 16:46.
 */
public class LineCustomer extends BaseDto {
    private Integer lineCustomerId;

    private Integer salesOrderId;

    private String customerName;

    private String businessType;

    private BigDecimal businessPrice;

    private String managerName;

    private String managerPhone;

    private String receiver;

    private String receivingContact;

    private String contactPhone;

    private String receiptLocation;

    private String billBoardLocation;

    private String loadingLocation;

    private String dischargeLocation;

    private String counterLocation;

    private String remark;
    /**
     * 客户表主键Id.
     */
    private Integer customerId;
    /**
     * 客户类型表主键Id.
     */
    private Integer customerTypeId;

    public Integer getLineCustomerId() {
        return lineCustomerId;
    }

    public void setLineCustomerId(Integer lineCustomerId) {
        this.lineCustomerId = lineCustomerId;
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public BigDecimal getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(BigDecimal businessPrice) {
        this.businessPrice = businessPrice;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone == null ? null : managerPhone.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getReceivingContact() {
        return receivingContact;
    }

    public void setReceivingContact(String receivingContact) {
        this.receivingContact = receivingContact == null ? null : receivingContact.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getReceiptLocation() {
        return receiptLocation;
    }

    public void setReceiptLocation(String receiptLocation) {
        this.receiptLocation = receiptLocation == null ? null : receiptLocation.trim();
    }

    public String getBillBoardLocation() {
        return billBoardLocation;
    }

    public void setBillBoardLocation(String billBoardLocation) {
        this.billBoardLocation = billBoardLocation == null ? null : billBoardLocation.trim();
    }

    public String getLoadingLocation() {
        return loadingLocation;
    }

    public void setLoadingLocation(String loadingLocation) {
        this.loadingLocation = loadingLocation == null ? null : loadingLocation.trim();
    }

    public String getDischargeLocation() {
        return dischargeLocation;
    }

    public void setDischargeLocation(String dischargeLocation) {
        this.dischargeLocation = dischargeLocation == null ? null : dischargeLocation.trim();
    }

    public String getCounterLocation() {
        return counterLocation;
    }

    public void setCounterLocation(String counterLocation) {
        this.counterLocation = counterLocation == null ? null : counterLocation.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }
}
