package com.xinda.cm.customer.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinda.cm.customer.dto.Customer;
import com.xinda.cm.customer.mapper.CustomerMapper;
import com.xinda.cm.customer.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户信息功能处理实现类.
 *
 * @author coudy
 *         <p>
 *         2017年3月8日
 */
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> queryAllCustomers(int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return customerMapper.queryAllCustomers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Customer saveCustomer(Customer customer) {
        if (null == customer.getCustomerId()) {
            customerMapper.insertSelective(customer);
        } else {
            customerMapper.updateByPrimaryKeySelective(customer);
        }
        return customer;
    }

    @Override
    public Customer getCustomerDetails(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public List<Customer> selectCustomerByParms(int page, int pagesize, Customer customer) {
        PageHelper.startPage(page, pagesize);
        return customerMapper.selectCustomerByParms(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomer(List<Customer> customers) {
        for (Customer customer : customers) {
            customerMapper.deleteByPrimaryKey(customer.getCustomerId());
        }
    }
}
