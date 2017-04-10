package com.xinda.cm.customer.mapper;

import com.xinda.cm.customer.dto.CustomerType;

import java.util.List;

/**
 * 客户类型Mapper接口.
 *
 * @Author Coundy.
 * @Date 2017/4/6 20:41.
 */
public interface CustomerTypeMapper {

    int deleteByPrimaryKey(Integer typeId);

    int insert(CustomerType record);

    int insertSelective(CustomerType record);

    CustomerType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(CustomerType record);

    int updateByPrimaryKey(CustomerType record);

    /**
     * 根据客户Id查询客户类型.
     *
     * @param customerId 客户信息主键id
     * @return
     */
    List<CustomerType> getCustomerTypesByCustomerId(Integer customerId);


    /**
     * 根据参数条件查询客户信息.
     *
     * @param customerType 客户类型对象参数
     * @return
     */
    List<CustomerType> selectCustomerByParms(CustomerType customerType);

}
