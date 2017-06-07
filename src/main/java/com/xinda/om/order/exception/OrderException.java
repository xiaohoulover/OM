package com.xinda.om.order.exception;

import com.xinda.system.sys.exception.BaseException;

/**
 * 订单管理异常类.
 *
 * @Author Coundy.
 * @Date 2017/3/27 23:09
 */
public class OrderException extends BaseException {

    /**
     * 订单模块消息Code.
     */
    private static final String DEFAULT_CODE = "OM";

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String MSG_ERROR_OM_ORDER_NO_NOT_NULL = "订单编号不能为空";

    public static final String MSG_ERROR_OM_SHIPPING_DATE_NOT_NULL = "订单编号不能为空";

    public static final String MSG_ERROR_OM_ORDER_INFO_HAD_DELETED = "订单信息已被删除，请刷新页面";

    public static final String MSG_ERROR_OM_ORDER_INFO_HAD_CHANGED = "订单信息已改变，请刷新页面";

    /**
     * 构造方法.用户代码中声明抛出异常信息.
     *
     * @param resMsg 消息描述
     */
    public OrderException(String resMsg) {
        super(DEFAULT_CODE, resMsg);
    }

    public OrderException(String code, String resMsg) {
        super(code, resMsg);
    }

}
