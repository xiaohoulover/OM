package com.xinda.om.sys.exception;

/**
 * 订单管理异常类.
 * 
 * @author coudy
 *
 *         2017年2月25日
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

	/**
	 * 构造方法.用户代码中声明抛出异常信息.
	 * 
	 * @param resMsg
	 *            消息描述
	 */
	public OrderException(String resMsg) {
		super(DEFAULT_CODE, resMsg);
	}

	public OrderException(String code, String resMsg) {
		super(code, resMsg);
	}

}
