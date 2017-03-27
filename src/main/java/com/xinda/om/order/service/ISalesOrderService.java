package com.xinda.om.order.service;

import com.xinda.om.order.dto.SalesOrder;
import com.xinda.om.sys.exception.OrderException;

import java.util.List;

/**
 * 订单处理Service.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:01
 */
public interface ISalesOrderService {

    /**
     * 保存订单信息.
     *
     * @param order    订单对象
     * @param isSubmit 是否是提交
     * @return
     */
    public SalesOrder saveSalesOrder(SalesOrder order, boolean isSubmit) throws OrderException;

    /**
     * 查询前后7天订单信息.
     *
     * @return
     */
    public List<SalesOrder> selectOrderNumFromHome();

    /**
     * 根据参数查询订单信息.
     *
     * @param pageNum  页数
     * @param pageSize 显示条数.
     * @param order    订单对象
     * @return
     */
    public List<SalesOrder> selectOrdersByParms(int pageNum, int pageSize, SalesOrder order) throws OrderException;

    /**
     * 获取订单详情
     *
     * @param orderId 订单Id
     * @return
     */
    public SalesOrder selectOrderDetails(Integer orderId) throws OrderException;

}
