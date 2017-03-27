package com.xinda.om.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinda.cm.customer.dto.Customer;
import com.xinda.cm.customer.mapper.CustomerMapper;
import com.xinda.om.order.dto.DisbursementDto;
import com.xinda.om.order.dto.ItemInfoDto;
import com.xinda.om.order.dto.LineCarDto;
import com.xinda.om.order.dto.SalesOrder;
import com.xinda.om.order.mapper.DisbursementMapper;
import com.xinda.om.order.mapper.ItemInfoMapper;
import com.xinda.om.order.mapper.LineCarMapper;
import com.xinda.om.order.mapper.SalesOrderMapper;
import com.xinda.om.order.service.ISalesOrderService;
import com.xinda.om.sys.contant.BaseConstants;
import com.xinda.om.sys.exception.OrderException;
import com.xinda.om.system.dto.SysSequence;
import com.xinda.om.system.service.ISysSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单处理ServiceImpl.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:11
 */
@Service
@Transactional
public class SalesOrderServiceImpl implements ISalesOrderService {

    private final Logger logger = LoggerFactory.getLogger(SalesOrderServiceImpl.class);

    @Autowired
    private SalesOrderMapper salesOrderMapper;
    @Autowired
    private ISysSequenceService sysSequenceService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ItemInfoMapper itemInfoMapper;
    @Autowired
    private LineCarMapper lineCarMapper;
    @Autowired
    private DisbursementMapper disbursementMapper;

    /**
     * 生成订单编号.
     *
     * @param order 订单信息
     * @return
     * @throws OrderException
     */
    private String createOrderNumber(SalesOrder order) throws OrderException {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(BaseConstants.ORDER_NUMBER_PRECODE);
        sBuilder.append(new SimpleDateFormat(BaseConstants.ORDER_NUMBER_DATECODE).format(order.getShippingDate()));
        return sysSequenceService.getSequenceNumber(new SysSequence(BaseConstants.SEQ_TYPE_OM,
                BaseConstants.OM_INIT_SEQ_VALUE, BaseConstants.OM_SEQ_STEP_LENGTH, BaseConstants.OM_SEQ_LENGTH,
                sBuilder.toString(), null, null, null));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public SalesOrder saveSalesOrder(SalesOrder order, boolean isSubmit) throws OrderException {
        logger.info("Starting save SalesOrder ...[{}]", order.toString());
        // 1.保存订单头信息和客户Id
        // 2.保存客户信息
        logger.info(order.toString());
        if (null == order.getSalesOrderId()) {// insert
            order.setOrderNumber(createOrderNumber(order));
            salesOrderMapper.insertSelective(order);
        } else {// update
            salesOrderMapper.updateByPrimaryKeySelective(order);
        }
        // 3.保存商品信息
        if (order.getItemInfoDtos() != null) {
            for (ItemInfoDto item : order.getItemInfoDtos()) {
                item.setSalesOrderId(order.getSalesOrderId());
                switch (item.get__status()) {
                    case BaseConstants.BASE_DTO_ADD:
                        // 保存商品信息
                        itemInfoMapper.insertSelective(item);
                        break;
                    case BaseConstants.BASE_DTO_DELETE:
                        // 删除
                        if (null != item.getItemId()) {
                            itemInfoMapper.deleteByPrimaryKey(item.getItemId());
                        }
                        break;
                    case BaseConstants.BASE_DTO_UPDATE:
                        // 更新商品信息
                        itemInfoMapper.updateByPrimaryKeySelective(item);
                        break;
                }
            }
        }
        // 4.保存车辆信息
        if (order.getLineCarDtos() != null) {
            for (LineCarDto lineCarDto : order.getLineCarDtos()) {
                lineCarDto.setSalesOrderId(order.getSalesOrderId());
                switch (lineCarDto.get__status()) {
                    case BaseConstants.BASE_DTO_ADD:
                        lineCarMapper.insertSelective(lineCarDto);
                        break;
                    case BaseConstants.BASE_DTO_DELETE:
                        if (null != lineCarDto.getLineCarId()) {
                            lineCarMapper.deleteByPrimaryKey(lineCarDto.getLineCarId());
                        }
                        break;
                    case BaseConstants.BASE_DTO_UPDATE:
                        lineCarMapper.updateByPrimaryKeySelective(lineCarDto);
                        break;
                    default:
                        break;
                }
            }
        }
        // 5.保存代垫费用
        if (order.getDisbursementDtos() != null) {
            for (DisbursementDto disbursement : order.getDisbursementDtos()) {
                if (null == disbursement.getDisbursementId() && BigDecimal.ZERO.compareTo(disbursement.getAmount()) < 0) {
                    continue;
                }
                if (null != disbursement.getDisbursementId()) {
                    if (null == disbursement.getAmount() || BigDecimal.ZERO.equals(disbursement.getAmount())) {
                        disbursement.set__status(BaseConstants.BASE_DTO_DELETE);
                    } else {
                        disbursement.set__status(BaseConstants.BASE_DTO_UPDATE);
                    }
                }
                switch (disbursement.get__status()) {
                    case BaseConstants.BASE_DTO_ADD:
                        disbursement.setSalesOrderId(order.getSalesOrderId());
                        disbursementMapper.insertSelective(disbursement);
                        break;
                    case BaseConstants.BASE_DTO_DELETE:
                        disbursementMapper.deleteByPrimaryKey(disbursement.getDisbursementId());
                        break;
                    case BaseConstants.BASE_DTO_UPDATE:
                        disbursementMapper.updateByPrimaryKeySelective(disbursement);
                        break;
                }
            }
        }
        logger.info("Ended save SalesOrder.");
        return order;
    }

    @Override
    public List<SalesOrder> selectOrderNumFromHome() {
        List<SalesOrder> orders = new ArrayList<SalesOrder>();
        List<SalesOrder> saveList = salesOrderMapper.selectOrderNumFromHome(BaseConstants.ORDER_STATUS_SAVE);
        List<SalesOrder> compList = salesOrderMapper.selectOrderNumFromHome(BaseConstants.ORDER_STATUS_COMP);
        for (SalesOrder saveOrder : saveList) {
            for (SalesOrder compOrder : compList) {
                if (saveOrder.getShippingDate().equals(compOrder.getShippingDate())) {
                    orders.add(new SalesOrder(saveOrder.getShippingDate(), saveOrder.getOrderCount(),
                            compOrder.getOrderCount()));
                    break;
                }
            }
        }
        return orders;
    }

    @Override
    public List<SalesOrder> selectOrdersByParms(int pageNum, int pageSize, SalesOrder order) throws OrderException {
        PageHelper.startPage(pageNum, pageSize);
        return salesOrderMapper.selectOrdersByParms(order);
    }

    @Override
    public SalesOrder selectOrderDetails(Integer orderId) throws OrderException {
        // 订单信息
        SalesOrder order = salesOrderMapper.selectByPrimaryKey(orderId);
        // 客户信息
        Customer customer = new Customer();
        customer.setCustomerId(order.getCustomerId());
        List<Customer> customers = customerMapper.selectCustomerByParms(customer);
        order.setCustomer(customers.get(0));
        // 运输商品信息
        ItemInfoDto itemInfoDto = new ItemInfoDto();
        itemInfoDto.setSalesOrderId(orderId);
        order.setItemInfoDtos(itemInfoMapper.selectItemInfoByParms(itemInfoDto));
        // 车辆信息
        LineCarDto lineCarDto = new LineCarDto();
        lineCarDto.setSalesOrderId(orderId);
        order.setLineCarDtos(lineCarMapper.selectLineCarsByParms(lineCarDto));
        // 代垫费用
        DisbursementDto disbursementDto = new DisbursementDto();
        disbursementDto.setSalesOrderId(orderId);
        order.setDisbursementDtos(disbursementMapper.selectDisbursementDtosByParms(disbursementDto));

        return order;
    }
}
