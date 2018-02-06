package com.xinda.sm.security.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
     * @param password 原始明文字符串
     * @return
     */
    public String encode(String password);


    /**
     * MD5 加盐 加密.
     *
     * @param password 待加密明文
     * @param salt     盐值
     * @param isBase64 是否Base64加密
     * @return 加密后密码
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String encodeSalt(String password, String salt, boolean isBase64) throws NoSuchAlgorithmException, UnsupportedEncodingException;


}
