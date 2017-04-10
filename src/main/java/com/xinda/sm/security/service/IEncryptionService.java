package com.xinda.sm.security.service;

/**
 * 加密处理接口.
 * 
 * @author coudy
 *
 *         2017年3月8日
 */
public interface IEncryptionService {

    /**
     * MD5加密处理.
     * @param password 原始明文密码
     * @return
     */
    public String encode(String password);

}
