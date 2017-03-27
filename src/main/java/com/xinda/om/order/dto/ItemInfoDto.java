package com.xinda.om.order.dto;

import com.xinda.om.system.dto.BaseDto;

/**
 * 运输商品信息DTO.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:20
 */
public class ItemInfoDto extends BaseDto {

    /**
     * 主键Id.
     */
    private Integer itemId;
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
    private Float shipWeight;
    /**
     * 收获重量.
     */
    private Float receiptWeight;
    /**
     * 磅差.
     */
    private Integer poundsWorse;
    /**
     * 差异率.
     */
    private Float differenceRate;
    /**
     * 备注.
     */
    private String remark;
    /**
     * 外键，订单头Id.
     */
    private Integer salesOrderId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo == null ? null : wayBillNo.trim();
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo == null ? null : transactionNo.trim();
    }

    public String getTankNo() {
        return tankNo;
    }

    public void setTankNo(String tankNo) {
        this.tankNo = tankNo == null ? null : tankNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getTankType() {
        return tankType;
    }

    public void setTankType(String tankType) {
        this.tankType = tankType == null ? null : tankType.trim();
    }

    public String getHazardCategory() {
        return hazardCategory;
    }

    public void setHazardCategory(String hazardCategory) {
        this.hazardCategory = hazardCategory == null ? null : hazardCategory.trim();
    }

    public String getUnNo() {
        return unNo;
    }

    public void setUnNo(String unNo) {
        this.unNo = unNo == null ? null : unNo.trim();
    }

    public Float getShipWeight() {
        return shipWeight;
    }

    public void setShipWeight(Float shipWeight) {
        this.shipWeight = shipWeight;
    }

    public Float getReceiptWeight() {
        return receiptWeight;
    }

    public void setReceiptWeight(Float receiptWeight) {
        this.receiptWeight = receiptWeight;
    }

    public Integer getPoundsWorse() {
        return poundsWorse;
    }

    public void setPoundsWorse(Integer poundsWorse) {
        this.poundsWorse = poundsWorse;
    }

    public Float getDifferenceRate() {
        return differenceRate;
    }

    public void setDifferenceRate(Float differenceRate) {
        this.differenceRate = differenceRate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

}