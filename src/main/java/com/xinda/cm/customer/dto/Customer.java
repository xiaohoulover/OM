package com.xinda.cm.customer.dto;

import com.xinda.system.sys.dto.BaseDto;

import java.util.List;

/**
 * 客户信息Dto.
 *
 * @Author Coundy.
 * @Date 2017/3/21 15:17
 */
public class Customer extends BaseDto {
    /**
     * 主键Id.
     */
    private Integer customerId;
    /**
     * 客户名称.
     */
    private String customerName;
    /**
     * 扩展属性1.
     */
    private String attibute1;
    /**
     * 扩展属性2.
     */
    private String attibute2;
    /**
     * 客户类型集合.
     */
    private List<CustomerType> customerTypeList;

    //add property
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
     * 收货方.
     */
    private String receiver;

    private CustomerType customerType;

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
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public List<CustomerType> getCustomerTypeList() {
        return customerTypeList;
    }

    public void setCustomerTypeList(List<CustomerType> customerTypeList) {
        this.customerTypeList = customerTypeList;
    }

    public String getAttibute1() {
        return attibute1;
    }

    public void setAttibute1(String attibute1) {
        this.attibute1 = attibute1;
    }

    public String getAttibute2() {
        return attibute2;
    }

    public void setAttibute2(String attibute2) {
        this.attibute2 = attibute2;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
