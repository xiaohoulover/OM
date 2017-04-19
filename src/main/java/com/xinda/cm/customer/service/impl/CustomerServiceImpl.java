package com.xinda.cm.customer.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinda.cm.customer.dto.Customer;
import com.xinda.cm.customer.dto.CustomerType;
import com.xinda.cm.customer.mapper.CustomerMapper;
import com.xinda.cm.customer.mapper.CustomerTypeMapper;
import com.xinda.cm.customer.service.ICustomerService;
import com.xinda.om.order.dto.LineCustomer;
import com.xinda.om.order.mapper.LineCustomerMapper;
import com.xinda.system.sys.contant.BaseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Line;
import java.util.List;

/**
 * 客户信息功能处理实现类.
 *
 * @Author Coundy.
 * @Date 2017/4/6
 */
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerTypeMapper customerTypeMapper;
    @Autowired
    private LineCustomerMapper lineCustomerMapper;

    @Override
    public List<Customer> queryCustomersByParams(Customer customer) {
        List<Customer> customers = customerMapper.queryCustomersByParams(customer);
        for (Customer customerDto : customers) {
            customerDto.setCustomerTypeList(customerTypeMapper.getCustomerTypesByCustomerId(customerDto.getCustomerId()));
        }
        return customers;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Customer saveCustomer(Customer customer) {
        //保存客户信息
        if (null == customer.getCustomerId()) {
            customerMapper.insertSelective(customer);
        } else {
            customerMapper.updateByPrimaryKeySelective(customer);
        }
        List<CustomerType> customerTypes = customer.getCustomerTypeList();
        //保存客户类型
        for (CustomerType customerType : customerTypes) {
            if (null == customerType.getTypeId()) {
                if (BaseConstants.BASE_DTO_DELETE.equals(customerType.get__status())) {
                    continue;
                }
            }
            customerType.setCustomerId(customer.getCustomerId());
            switch (customerType.get__status()) {
                case BaseConstants.BASE_DTO_ADD:
                    customerTypeMapper.insertSelective(customerType);
                    break;
                case BaseConstants.BASE_DTO_DELETE:
                    customerTypeMapper.deleteByPrimaryKey(customerType.getTypeId());
                    break;
                case BaseConstants.BASE_DTO_UPDATE:
                    customerTypeMapper.updateByPrimaryKeySelective(customerType);
                    break;
                default:
                    break;
            }
        }
        return customer;
    }

    @Override
    public Customer getCustomerDetails(Integer customerId) {
        //获取客户信息
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        //获取客户类型
        customer.setCustomerTypeList(customerTypeMapper.getCustomerTypesByCustomerId(customerId));
        return customer;
    }

    @Override
    public List<CustomerType> selectCustomerByParms(int page, int pagesize, CustomerType customerType) {
        PageHelper.startPage(page, pagesize);
        return customerTypeMapper.selectCustomerByParms(customerType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomerType(List<CustomerType> customerTypes) {
        for (CustomerType customerType : customerTypes) {
            customerTypeMapper.deleteByPrimaryKey(customerType.getTypeId());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomer(Integer customerId) {
        customerMapper.deleteByPrimaryKey(customerId);
    }

    @Override
    public List<LineCustomer> queryAllLineCustomers(LineCustomer lineCustomer) {
        return lineCustomerMapper.queryAllLineCustomer(lineCustomer);
    }
}
