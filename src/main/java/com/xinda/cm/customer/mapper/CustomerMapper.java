package com.xinda.cm.customer.mapper;

import com.xinda.cm.customer.dto.Customer;

import java.util.List;

/**
 * Customer映射接口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 15:29
 */
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     * 根据参数条件查询客户信息.
     *
     * @param customer
     * @return
     */
    List<Customer> queryCustomersByParams(Customer customer);

}
