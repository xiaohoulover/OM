package com.xinda.system.sys.exception;

/**
 * 系统异常声明.
 *
 * @Author Coundy.
 * @Date 2017/3/27 23:09
 */
public class SysException extends BaseException {

    public static final String MSG_ERROR_SYS_USER_NOT_EXISTS = "用户不存在!";
    public static final String MSG_ERROR_SYS_USER_NAME_NOT_NULL = "用户名或密码不能为空!";
    public static final String MSG_ERROR_SYS_USERNAME_PASSWORD_ERROR = "用户名或密码输入错误!";
    public static final String MSG_ERROR_SYS_USER_NAME_ERROR = "用户名输入错误!";
    public static final String MSG_ERROR_SYS_USER_PASSWORD_ERROR = "密码输入错误!";
    public static final String MSG_ERROR_SYS_USER_NAME_INVALID = "用户已失效!";
    public static final String MSG_ERROR_SYS_VERIFICATION_CODE_ERROR = "验证码输入错误!";
    public static final String MSG_ERROR_SYS_USER_OLD_PASSWORD_NOT_EQUALS = "原密码输入错误!";
    public static final String MSG_ERROR_SYS_USER_NAME_HAD_EXISTS = "用户名重复!";
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 系统模块消息Code.
     */
    private static final String DEFAULT_CODE = "SYS";

    /**
     * 构造方法.用户代码中声明抛出异常信息.
     *
     * @param resMsg 消息描述
     */
    public SysException(String resMsg) {
        super(DEFAULT_CODE, resMsg);
    }

    public SysException(String code, String resMsg) {
        super(code, resMsg);
    }

}
