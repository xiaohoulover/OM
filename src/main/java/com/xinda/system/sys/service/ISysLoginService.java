package com.xinda.system.sys.service;

import com.xinda.um.user.dto.SysUser;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录接口.
 *
 * @Author Coundy.
 * @Date 2017/3/27 22:13
 */
public interface ISysLoginService {

    /**
     * 登陆.
     *
     * @param sysUser  登陆账号对象
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return view
     */
    ModelAndView doLogin(SysUser sysUser, HttpServletRequest request, HttpServletResponse response);

    /**
     * 登出.
     *
     * @Author Coundy.
     * @Date 2017/3/27 22:18
     */
    ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response);

}
