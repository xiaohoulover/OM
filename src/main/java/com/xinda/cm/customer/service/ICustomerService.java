package com.xinda.cm.customer.service;

import com.xinda.cm.customer.dto.Customer;

import java.util.List;

/**
 * 客户信息处理Service接口.
 *
 * @author coudy
 *         <p>
 *         2017年3月8日
 */
public interface ICustomerService {

    /**
     * 查询所有的客户信息.
     *
     * @param page     页码
     * @param pagesize 页显示条数
     * @return
     */
    public List<Customer> queryAllCustomers(int page, int pagesize);

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
     */
    public Customer getCustomerDetails(Integer customerId);

    /**
     * 根据参数条件查询客户信息.
     *
     * @param page     页码
     * @param pagesize 页显示条数
     * @param customer 参数客户对象
     * @return
     */
    public List<Customer> selectCustomerByParms(int page, int pagesize, Customer customer);

    /**
     * 删除客户信息.
     *
     * @param customers 参数集合对象
     */
    public void deleteCustomer(List<Customer> customers);

}
