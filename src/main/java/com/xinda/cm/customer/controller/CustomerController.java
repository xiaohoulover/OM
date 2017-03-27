package com.xinda.cm.customer.controller;

import com.xinda.cm.customer.dto.Customer;
import com.xinda.cm.customer.service.ICustomerService;
import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.system.sys.dto.ResponseJsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xinda.system.sys.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户处理Controller.
 *
 * @author coudy
 *         <p>
 *         2017年3月8日
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private ICustomerService customerService;

    /**
     * 查询所有的客户信息.
     *
     * @param page     页码
     * @param pagesize 页显示条数
     * @return
     */
    @RequestMapping("/queryAllCustomers")
    @ResponseBody
    public ResponseJsonData queryAllCustomers(@RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_NUM) int page,
                                              @RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_SIZE) int pagesize) {
        return new ResponseJsonData(customerService.queryAllCustomers(page, pagesize));
    }

    /**
     * 保存客户信息.
     *
     * @param customer 客户对象信息
     * @return
     */
    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonData saveCustomer(@RequestBody Customer customer) {
        return new ResponseJsonData(customerService.saveCustomer(customer));
    }

    /**
     * 根据customerId获取Customer详情.
     *
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/getCustomerDetails", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJsonData getCustomerDetails(Integer customerId) {
        return new ResponseJsonData(customerService.getCustomerDetails(customerId));
    }

    /**
     * 根据参数条件查询客户信息.
     *
     * @param customer 客户参数对象
     * @return
     */
    @RequestMapping(value = "/selectCustomerByParms", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonData selectCustomerByParms(@RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_NUM) int page,
                                                  @RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_SIZE) int pagesize,
                                                  Customer customer) {
        return new ResponseJsonData(customerService.selectCustomerByParms(page, pagesize, customer));
    }

    /**
     * 删除客户信息.
     *
     * @param customers 参数对象
     * @return
     */
    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonData deleteCustomer(@RequestBody List<Customer> customers) {
        customerService.deleteCustomer(customers);
        return new ResponseJsonData(true);
    }

}
