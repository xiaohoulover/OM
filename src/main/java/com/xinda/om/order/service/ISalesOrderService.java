package com.xinda.om.order.service;

import com.xinda.om.order.dto.SalesOrder;
import com.xinda.system.sys.exception.OrderException;

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
     * 查询页-根据参数查询订单详细信息(带分页).
     *
     * @param pageNum  页数
     * @param pageSize 显示条数.
     * @param order    订单对象
     * @return
     */
    public List<SalesOrder> selectOrdersByParms(int pageNum, int pageSize, SalesOrder order) throws OrderException;

    /**
     * 根据参数查询订单信息.
     *
     * @param order
     * @return
     * @throws OrderException
     */
    public List<SalesOrder> queryOrdersByParms(SalesOrder order) throws OrderException;

    /**
     * 获取订单详情
     *
     * @param orderId 订单Id
     * @return
     */
    public SalesOrder selectOrderDetails(Integer orderId) throws OrderException;

    /**
     * 删除订单信息.
     *
     * @param orders
     * @throws OrderException
     */
    public void deleteOrders(List<SalesOrder> orders) throws OrderException;

}
