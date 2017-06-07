package com.xinda.system.login.service;

import com.xinda.system.login.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码Service.
 *
 * @Author Coundy.
 * @Date 2017/3/28 14:29.
 */
public interface IVerificationCodeService {

    /**
     * 生成保存到Cookie中的验证码Key.
     *
     * @return
     */
    public String generateVerificationKey();

    /**
     * 生成验证码.
     *
     * @return
     */
    public String generateVerificationCode();

    /**
     * 根据 code 生成图片数据,写入指定的输出流.
     *
     * @param request
     * @param response 应答参数
     */
    public void generateVerification(HttpServletRequest request, HttpServletResponse response, String verificationKey)
            throws IOException;

    /**
     * 验证码校验.
     *
     * @param request
     * @return
     */
    public void valiLoginVerificationCode(HttpServletRequest request) throws LoginException;

}
