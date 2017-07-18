package com.xinda.sm.security.service;

/**
 * 加密处理接口.
 *
 * @author coudy
 *         <p>
 *         2017年3月8日
 */
public interface IEncryptionService {

    /**
     * MD5非对称加密处理.(明文加密后无法解密为原明文)
     *
     * @param password 原始明文密码
     * @return
     */
    public String encode(String password);

}
