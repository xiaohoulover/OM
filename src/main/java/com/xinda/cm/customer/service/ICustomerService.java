package com.xinda.cm.customer.service;

import com.xinda.cm.customer.dto.Customer;
import com.xinda.cm.customer.dto.CustomerType;
import com.xinda.om.order.dto.LineCustomer;
import com.xinda.cm.customer.exception.CustomerException;

import java.util.List;


/**
 * 客户信息处理Service接口.
 *
 * @Author Coundy.
 * @Date 2017/4/6
 */
public interface ICustomerService {

    /**
     * 查询客户信息.
     *
     * @param customer
     * @return
     */
    public List<Customer> queryCustomersByParams(Customer customer);

    /**
     * 保存客户信息.
     *
     * @param customer 客户对象
     * @return
     */
    public Customer saveCustomer(Customer customer);

    /**
     * 获取Customer详情.
     *
     * @param customerId 客户对象Id
     * @return
     * @throws CustomerException 客户统一异常
     */
    public Customer getCustomerDetails(Integer customerId) throws CustomerException;

    /**
     * 根据参数条件查询客户信息.
     *
     * @param page         页码
     * @param pagesize     页显示条数
     * @param customerType 参数客户对象
     * @return
     */
    public List<CustomerType> selectCustomerByParms(int page, int pagesize, CustomerType customerType);

    /**
     * 删除客户类型信息.
     *
     * @param customerTypes 参数集合对象
     */
    public void deleteCustomerType(List<CustomerType> customerTypes);

    /**
     * 删除客户信息.
     *
     * @param customerId 对象Id
     */
    public void deleteCustomer(Integer customerId);

    /**
     * 查询订单上客户信息.
     *
     * @param lineCustomer
     * @return
     */
    public List<LineCustomer> queryAllLineCustomers(LineCustomer lineCustomer);

}
