package com.xinda.om.order.mapper;

import com.xinda.om.order.dto.LineCustomer;

/**
 * 订单-行客户信息.
 *
 * @Author Coundy.
 * @Date 2017/4/12 16:48.
 */
public interface LineCustomerMapper {
    int deleteByPrimaryKey(Integer lineCustomerId);

    int insert(LineCustomer record);

    int insertSelective(LineCustomer record);

    LineCustomer selectByPrimaryKey(Integer lineCustomerId);

    int updateByPrimaryKeySelective(LineCustomer record);

    int updateByPrimaryKey(LineCustomer record);

    /**
     * 根据订单头Id查询订单客户信息.
     *
     * @param salesOrderId 订单头Id
     * @return
     */
    LineCustomer selectBySalesOrderId(Integer salesOrderId);
}
