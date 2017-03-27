package com.xinda.om.system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.xinda.om.system.dto.SysUser;

/**
 * 登录接口.
 * 
 * @author coudy
 *
 *         2017年3月5日
 */
public interface ISysLoginService {

	/**
	 * 登陆.
	 * 
	 * @param sysUser
	 *            登陆账号对象
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return view
	 */
	ModelAndView doLogin(SysUser sysUser, HttpServletRequest request, HttpServletResponse response);

}
