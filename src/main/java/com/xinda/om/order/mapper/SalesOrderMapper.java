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
     * 查询页-根据参数查询订单详细信息(带分页).
     *
     * @param order 订单参数对象
     * @return List<Order>
     */
    List<SalesOrder> selectOrdersByParms(SalesOrder order);

    /**
     * 查询订单前后7天订单已完成与未完成数量.
     *
     * @return List<Order>
     */
    //List<SalesOrder> selectOrderNumFromHome(String orderStatus);
    List<SalesOrder> selectOrderNumFromHome();

    /**
     * 查询所有订单信息.
     *
     * @param order
     * @return List<SalesOrder>
     */
    List<SalesOrder> queryOrderByParams(SalesOrder order);

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
