package com.xinda.om.sys.exception;

/**
 * 系统异常声明.
 * 
 * @author coudy
 *
 *         2017年3月5日
 */
public class SysException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 系统模块消息Code.
	 */
	private static final String DEFAULT_CODE = "SYS";

	public static final String MSG_ERROR_SYS_USER_NAME_NOT_NULL = "用户名或密码不能为空!";

	public static final String MSG_ERROR_SYS_USER_NAME_ERROR = "用户名或密码错误!";

	public static final String MSG_ERROR_SYS_USER_NAME_INVALID = "用户已失效";

	/**
	 * 构造方法.用户代码中声明抛出异常信息.
	 * 
	 * @param resMsg
	 *            消息描述
	 */
	public SysException(String resMsg) {
		super(DEFAULT_CODE, resMsg);
	}

	public SysException(String code, String resMsg) {
		super(code, resMsg);
	}

}
