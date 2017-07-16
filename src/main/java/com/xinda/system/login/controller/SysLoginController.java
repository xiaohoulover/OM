package com.xinda.system.login.controller;

import com.xinda.system.login.service.ISysLoginService;
import com.xinda.system.login.service.IVerificationCodeService;
import com.xinda.system.sys.controller.BaseController;
import com.xinda.um.user.dto.SysUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户登录与登出.
 *
 * @Author Coundy.
 * @Date 2017/3/27 22:05
 */
@Controller
public class SysLoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    @Autowired
    private ISysLoginService sysLoginService;
    @Autowired
    private IVerificationCodeService verificationCodeService;

    /**
     * 登陆.
     *
     * @param sysUser  登录用户对象
     * @param request  HttpServletRequest 请求参数
     * @param response HttpServletResponse 应答参数
     * @return view
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ModelAndView login(final SysUser sysUser, final HttpServletRequest request,
                              final HttpServletResponse response) {
        return sysLoginService.doLogin(sysUser, request, response);
    }

    /**
     * 显示登陆界面.
     *
     * @param request  HttpServletRequest 请求参数
     * @param response HttpServletResponse 应答参数
     * @return view
     */
    @RequestMapping(value = {"/login.html"})
    public ModelAndView loginView(final HttpServletRequest request, final HttpServletResponse response) {
        return new ModelAndView("/login");
    }

    /**
     * 登出系统.
     *
     * @param request  请求参数
     * @param response 应答参数
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutView(final HttpServletRequest request, final HttpServletResponse response) {
        logger.info("User:[{}], Logout ...[{}]",
                request.getSession().getAttribute("userName"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return sysLoginService.doLogout(request, response);
    }

    /**
     * 生成验证码图片.
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/verificationCode", method = RequestMethod.GET)
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Create verificationCode ...");
        //生成随机的key值放到Cookie中
        String key = verificationCodeService.generateVerificationKey();
        Cookie cookie = new Cookie("cookieVeriKey", key);
        cookie.setPath(StringUtils.defaultIfEmpty(request.getContextPath(), "/"));
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        verificationCodeService.generateVerification(request, response, key);
    }

}
