package com.xinda.om.order.dto;

import com.xinda.om.system.dto.BaseDto;

/**
 * 订单行上-车辆信息.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:44.
 */
public class LineCarDto extends BaseDto {
    /**
     * 主键Id.
     */
    private Integer lineCarId;
    /**
     * 订单头Id.
     */
    private Integer salesOrderId;
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
    private Float distance;
    /**
     * 区间路桥费.
     */
    private Float roadBridgeFee;
    /**
     * 核定油耗.
     */
    private Float fuelCosts;
    /**
     * 备注.
     */
    private String remark;

    public Integer getLineCarId() {
        return lineCarId;
    }

    public void setLineCarId(Integer lineCarId) {
        this.lineCarId = lineCarId;
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getCarFrontNo() {
        return carFrontNo;
    }

    public void setCarFrontNo(String carFrontNo) {
        this.carFrontNo = carFrontNo == null ? null : carFrontNo.trim();
    }

    public String getCarPlateNo() {
        return carPlateNo;
    }

    public void setCarPlateNo(String carPlateNo) {
        this.carPlateNo = carPlateNo == null ? null : carPlateNo.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getEscortName() {
        return escortName;
    }

    public void setEscortName(String escortName) {
        this.escortName = escortName == null ? null : escortName.trim();
    }

    public String getShipPath() {
        return shipPath;
    }

    public void setShipPath(String shipPath) {
        this.shipPath = shipPath == null ? null : shipPath.trim();
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getRoadBridgeFee() {
        return roadBridgeFee;
    }

    public void setRoadBridgeFee(Float roadBridgeFee) {
        this.roadBridgeFee = roadBridgeFee;
    }

    public Float getFuelCosts() {
        return fuelCosts;
    }

    public void setFuelCosts(Float fuelCosts) {
        this.fuelCosts = fuelCosts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}
