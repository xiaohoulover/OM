package com.xinda.om.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinda.cm.customer.dto.Customer;
import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.system.sys.dto.BaseDto;

import java.util.Date;
import java.util.List;

/**
 * 订单对象.
 *
 * @Author Coundy.
 * @Date 2017/3/21 14:41
 */
public class SalesOrder extends BaseDto {

    /**
     * 订单头Id.
     */
    private Integer salesOrderId;
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
    /**
     * 客户Id.
     */
    private Integer customerId;
    /**
     * 客户类型Id.
     */
    private Integer customerTypeId;
    /**
     * 客户信息.
     */
    private Customer customer;
    /**
     * 商品集合.
     */
    private List<ItemInfoDto> itemInfoDtos;
    /**
     * 行-车辆信息.
     */
    private List<LineCarDto> lineCarDtos;
    /**
     * 代垫费用集合.
     */
    private List<DisbursementDto> disbursementDtos;

    //ADD properties
    /**
     * 待处理数量.
     */
    private Long saveNum;
    /**
     * 已完成数量.
     */
    private Long compNum;
    /**
     * 订单总数.
     */
    private Long orderCount;
    /**
     * 查询条件-实际运输日期从.
     */
    private Date shippingDateFrom;
    /**
     * 查询条件-实际运输日期至.
     */
    private Date shippingDateTo;
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
     * 车头编号.
     */
    private String carFrontNo;
    /**
     * 客户名称.
     */
    private String customerName;
    /**
     * 收货方.
     */
    private String receiver;

    private int orderStatusAcce;
    private int orderStatusShip;
    private int orderStatusFdbk;
    private int orderStatusComp;

    public SalesOrder() {
    }

    public SalesOrder(Date shippingDate, int orderStatusAcce, int orderStatusShip, int orderStatusFdbk, int orderStatusComp) {
        super();
        this.shippingDate = shippingDate;
        this.orderStatusAcce = orderStatusAcce;
        this.orderStatusShip = orderStatusShip;
        this.orderStatusFdbk = orderStatusFdbk;
        this.orderStatusComp = orderStatusComp;
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
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

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<DisbursementDto> getDisbursementDtos() {
        return disbursementDtos;
    }

    public void setDisbursementDtos(List<DisbursementDto> disbursementDtos) {
        this.disbursementDtos = disbursementDtos;
    }

    public List<ItemInfoDto> getItemInfoDtos() {
        return itemInfoDtos;
    }

    public void setItemInfoDtos(List<ItemInfoDto> itemInfoDtos) {
        this.itemInfoDtos = itemInfoDtos;
    }

    public List<LineCarDto> getLineCarDtos() {
        return lineCarDtos;
    }

    public void setLineCarDtos(List<LineCarDto> lineCarDtos) {
        this.lineCarDtos = lineCarDtos;
    }

    public Long getSaveNum() {
        return saveNum;
    }

    public void setSaveNum(Long saveNum) {
        this.saveNum = saveNum;
    }

    public Long getCompNum() {
        return compNum;
    }

    public void setCompNum(Long compNum) {
        this.compNum = compNum;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Date getShippingDateFrom() {
        return shippingDateFrom;
    }

    public void setShippingDateFrom(Date shippingDateFrom) {
        this.shippingDateFrom = shippingDateFrom;
    }

    public Date getShippingDateTo() {
        return shippingDateTo;
    }

    public void setShippingDateTo(Date shippingDateTo) {
        this.shippingDateTo = shippingDateTo;
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

    public String getCarFrontNo() {
        return carFrontNo;
    }

    public void setCarFrontNo(String carFrontNo) {
        this.carFrontNo = carFrontNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getOrderStatusAcce() {
        return orderStatusAcce;
    }

    public void setOrderStatusAcce(int orderStatusAcce) {
        this.orderStatusAcce = orderStatusAcce;
    }

    public int getOrderStatusShip() {
        return orderStatusShip;
    }

    public void setOrderStatusShip(int orderStatusShip) {
        this.orderStatusShip = orderStatusShip;
    }

    public int getOrderStatusFdbk() {
        return orderStatusFdbk;
    }

    public void setOrderStatusFdbk(int orderStatusFdbk) {
        this.orderStatusFdbk = orderStatusFdbk;
    }

    public int getOrderStatusComp() {
        return orderStatusComp;
    }

    public void setOrderStatusComp(int orderStatusComp) {
        this.orderStatusComp = orderStatusComp;
    }

    @Override
    public String toString() {
        return "SalesOrder{" +
                "salesOrderId=" + salesOrderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", createDate=" + createDate +
                ", shippingDate=" + shippingDate +
                ", customerId=" + customerId +
                '}';
    }
}
