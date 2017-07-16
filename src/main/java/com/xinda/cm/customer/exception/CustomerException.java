package com.xinda.cm.customer.exception;

import com.xinda.system.sys.exception.BaseException;

/**
 * 客户管理异常类.
 *
 * @author Coundy.
 * @date 2017/5/4 11:53.
 */
public class CustomerException extends BaseException {

    public static final String MSG_ERROR_CM_CUSTOMER_INFO_HAD_DELETED = "客户信息已删除";
    /**
     * 客户管理模块消息Code.
     */
    private static final String DEFAULT_CODE = "CM";

    /**
     * 构造方法.用户代码中声明抛出异常信息.
     *
     * @param resMsg 消息描述
     */
    public CustomerException(String resMsg) {
        super(DEFAULT_CODE, resMsg);
    }

    public CustomerException(String code, String resMsg) {
        super(code, resMsg);
    }

}
