package com.xinda.om.order.dto;

import com.xinda.om.system.dto.BaseDto;

import java.math.BigDecimal;

/**
 * 代垫费用DTO.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:36
 */
public class DisbursementDto extends BaseDto {
    /**
     * 主键Id.
     */
    private Integer disbursementId;
    /**
     * 类型.
     */
    private String type;
    /**
     * 金额.
     */
    private BigDecimal amount;
    /**
     * 备注.
     */
    private String remark;
    /**
     * 订单头Id.
     */
    private Integer salesOrderId;

    public Integer getDisbursementId() {
        return disbursementId;
    }

    public void setDisbursementId(Integer disbursementId) {
        this.disbursementId = disbursementId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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