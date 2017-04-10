package com.xinda.cm.customer.dto;

import com.xinda.system.sys.dto.BaseDto;

/**
 * 客户类型Dto.
 *
 * @Author Coundy.
 * @Date 2017/4/6 20:34.
 */
public class CustomerType extends BaseDto {

    /**
     * 主键Id.
     */
    private Integer typeId;
    /**
     * 业务类型.
     */
    private String businessType;
    /**
     * 业务价格.
     */
    private Float businessPrice;
    /**
     * 经办人.
     */
    private String managerName;
    /**
     * 经办人电话.
     */
    private String managerPhone;
    /**
     * 收货方.
     */
    private String receiver;
    /**
     * 收获联系人.
     */
    private String receivingContact;
    /**
     * 联系电话.
     */
    private String contactPhone;
    /**
     * 收获地址.
     */
    private String receiptLocation;
    /**
     * 提柜地点.
     */
    private String billBoardLocation;
    /**
     * 装货地点..
     */
    private String loadingLocation;
    /**
     * 卸货地点.
     */
    private String dischargeLocation;
    /**
     * 还柜地点.
     */
    private String counterLocation;
    /**
     * 注意事项.
     */
    private String remark;
    /**
     * 客户信息表主键Id.
     */
    private Integer customerId;

    //add property
    /**
     * 客户名称.
     */
    private String customerName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Float getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(Float businessPrice) {
        this.businessPrice = businessPrice;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceivingContact() {
        return receivingContact;
    }

    public void setReceivingContact(String receivingContact) {
        this.receivingContact = receivingContact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getReceiptLocation() {
        return receiptLocation;
    }

    public void setReceiptLocation(String receiptLocation) {
        this.receiptLocation = receiptLocation;
    }

    public String getBillBoardLocation() {
        return billBoardLocation;
    }

    public void setBillBoardLocation(String billBoardLocation) {
        this.billBoardLocation = billBoardLocation;
    }

    public String getLoadingLocation() {
        return loadingLocation;
    }

    public void setLoadingLocation(String loadingLocation) {
        this.loadingLocation = loadingLocation;
    }

    public String getDischargeLocation() {
        return dischargeLocation;
    }

    public void setDischargeLocation(String dischargeLocation) {
        this.dischargeLocation = dischargeLocation;
    }

    public String getCounterLocation() {
        return counterLocation;
    }

    public void setCounterLocation(String counterLocation) {
        this.counterLocation = counterLocation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
