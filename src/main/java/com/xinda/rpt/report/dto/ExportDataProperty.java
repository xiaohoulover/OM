package com.xinda.rpt.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinda.system.sys.contant.BaseConstants;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 报表导出字段.
 *
 * @Author Coundy.
 * @Date 2017/4/14 18:42.
 */
public class ExportDataProperty {

    /**
     * 订单头Id.
     */
    private Integer rowNum;
    /**
     * 订单编号.
     */
    private String orderNumber;
    /**
     * 订单状态.
     */
    private String orderStatus;
    /**
     * 订单创建日期.
     */
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT, timezone = BaseConstants.TIME_ZONE)
    private Date createDate;
    /**
     * 订单运输日期.
     */
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT, timezone = BaseConstants.TIME_ZONE)
    private Date shippingDate;

    //Customer
    /**
     * 客户名称.
     */
    private String customerName;
    /**
     * 业务类型.
     */
    private String businessType;
    /**
     * 业务价格.
     */
    private BigDecimal businessPrice;
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
    private String customerRemark;

    //Item
    /**
     * 提运单号.
     */
    private String wayBillNo;
    /**
     * 交易单号.
     */
    private String transactionNo;
    /**
     * 罐柜号.
     */
    private String tankNo;
    /**
     * 货物名称.
     */
    private String itemName;
    /**
     * 罐柜类型.
     */
    private String tankType;
    /**
     * 危险类别.
     */
    private String hazardCategory;
    /**
     * unNo.
     */
    private String unNo;
    /**
     * 出货重量.
     */
    private BigDecimal shipWeight;
    /**
     * 签收重量.
     */
    private BigDecimal receiptWeight;
    /**
     * 磅差.
     */
    private BigDecimal poundsWorse;
    /**
     * 差异率.
     */
    private BigDecimal differenceRate;
    /**
     * 备注.
     */
    private String itemRemark;

    //Car
    /**
     * 车头编号.
     */
    private String carFrontNo;
    /**
     * 车板编号.
     */
    private String carPlateNo;
    /**
     * 司机姓名.
     */
    private String driverName;
    /**
     * 押运人姓名.
     */
    private String escortName;
    /**
     * 运输路径.
     */
    private String shipPath;
    /**
     * 区间路程.
     */
    private BigDecimal distance;
    /**
     * 区间路桥费.
     */
    private BigDecimal roadBridgeFee;
    /**
     * 核定油耗.
     */
    private BigDecimal fuelCosts;
    /**
     * 备注.
     */
    private String carRemark;

    //Disbursement
    /**
     * 类型.
     */
    private String disbursementType;
    /**
     * 金额.
     */
    private BigDecimal amount;
    /**
     * 备注.
     */
    private String disbursementRemark;

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTankNo() {
        return tankNo;
    }

    public void setTankNo(String tankNo) {
        this.tankNo = tankNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTankType() {
        return tankType;
    }

    public void setTankType(String tankType) {
        this.tankType = tankType;
    }

    public String getHazardCategory() {
        return hazardCategory;
    }

    public void setHazardCategory(String hazardCategory) {
        this.hazardCategory = hazardCategory;
    }

    public String getUnNo() {
        return unNo;
    }

    public void setUnNo(String unNo) {
        this.unNo = unNo;
    }

    public BigDecimal getShipWeight() {
        return shipWeight;
    }

    public void setShipWeight(BigDecimal shipWeight) {
        this.shipWeight = shipWeight;
    }

    public BigDecimal getReceiptWeight() {
        return receiptWeight;
    }

    public void setReceiptWeight(BigDecimal receiptWeight) {
        this.receiptWeight = receiptWeight;
    }

    public BigDecimal getPoundsWorse() {
        return poundsWorse;
    }

    public void setPoundsWorse(BigDecimal poundsWorse) {
        this.poundsWorse = poundsWorse;
    }

    public BigDecimal getDifferenceRate() {
        return differenceRate;
    }

    public void setDifferenceRate(BigDecimal differenceRate) {
        this.differenceRate = differenceRate;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getCarFrontNo() {
        return carFrontNo;
    }

    public void setCarFrontNo(String carFrontNo) {
        this.carFrontNo = carFrontNo;
    }

    public String getCarPlateNo() {
        return carPlateNo;
    }

    public void setCarPlateNo(String carPlateNo) {
        this.carPlateNo = carPlateNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getEscortName() {
        return escortName;
    }

    public void setEscortName(String escortName) {
        this.escortName = escortName;
    }

    public String getShipPath() {
        return shipPath;
    }

    public void setShipPath(String shipPath) {
        this.shipPath = shipPath;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getRoadBridgeFee() {
        return roadBridgeFee;
    }

    public void setRoadBridgeFee(BigDecimal roadBridgeFee) {
        this.roadBridgeFee = roadBridgeFee;
    }

    public BigDecimal getFuelCosts() {
        return fuelCosts;
    }

    public void setFuelCosts(BigDecimal fuelCosts) {
        this.fuelCosts = fuelCosts;
    }

    public String getCarRemark() {
        return carRemark;
    }

    public void setCarRemark(String carRemark) {
        this.carRemark = carRemark;
    }

    public String getDisbursementType() {
        return disbursementType;
    }

    public void setDisbursementType(String disbursementType) {
        this.disbursementType = disbursementType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDisbursementRemark() {
        return disbursementRemark;
    }

    public void setDisbursementRemark(String disbursementRemark) {
        this.disbursementRemark = disbursementRemark;
    }
}
