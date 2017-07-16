package com.xinda.system.sys.service;

import com.xinda.system.sys.exception.SysException;

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
    public void generateVerificationCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException;

    /**
     * 验证码校验.
     *
     * @param request
     * @return
     */
    public void valiLoginVerificationCode(HttpServletRequest request) throws SysException;

    /**
     * 登录之前校验.(Spring Security Filter 在登陆之前拦截处理)
     *
     * @param request
     * @return
     */
    public boolean beforeLoginVerificationCode(HttpServletRequest request);

}
