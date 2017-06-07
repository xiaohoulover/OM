package com.xinda.system.sys.contant;

/**
 * Redis相关常量值.
 *
 * @author Coundy.
 * @date 2017/5/21 22:27.
 */
public class RedisContants {

    /**
     *
     */
    public static final String DEFAULT_KEY = "xd:om:";
    /**
     * user存储Key值.
     */
    public static final String OM_USER_KEY = "xd:om:user";
    /**
     * 验证码Key.
     */
    public static final String SYS_VERIFICATION_KEY = "xd:om:verifi";
    /**
     * 验证码失效时间.
     */
    public static final int VERIFICATION_CODE_EXPIRED = 1 * 60;

}
