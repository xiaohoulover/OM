package com.xinda.om.order.mapper;

import com.xinda.om.order.dto.SalesOrder;

import java.util.List;

/**
 * 订单映射借口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 14:44.
 */
public interface SalesOrderMapper {

    int deleteByPrimaryKey(Integer salesOrderId);

    int insert(SalesOrder record);

    int insertSelective(SalesOrder record);

    SalesOrder selectByPrimaryKey(Integer salesOrderId);

    int updateByPrimaryKeySelective(SalesOrder record);

    int updateByPrimaryKey(SalesOrder record);

    /**
     * 查询订单信息
     *
     * @param order 订单参数对象
     * @return List<Order>
     */
    List<SalesOrder> selectOrdersByParms(SalesOrder order);

    /**
     * 查询订单前后7天订单已完成与未完成数量.
     *
     * @param orderStatus 订单状态
     * @return List<Order>
     */
    List<SalesOrder> selectOrderNumFromHome(String orderStatus);

    /**
     * 查询所有订单信息.
     *
     * @return List<SalesOrder>
     */
    List<SalesOrder> selectAllOrders();

    /**
     * 导出Excel数据.
     *
     * @return List<Order>
     */
    List<SalesOrder> getExportExcelOrderData();

    /**
     * 查询并锁一行数据.
     *
     * @param salesOrderId 订单头Id
     * @return
     */
    SalesOrder selectByOrderIdForUpdate(Integer salesOrderId);

}
