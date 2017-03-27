package com.xinda.om.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xinda.om.sys.controller.BaseController;
import com.xinda.om.system.dto.SysUser;
import com.xinda.om.system.service.ISysLoginService;

/**
 * 用户登录.
 * 
 * @author coudy
 *
 *         2017年3月5日
 */
@Controller
public class SysLoginController extends BaseController {

	@Autowired
	private ISysLoginService sysLoginService;

	/**
	 * 登陆.
	 * 
	 * @param sysUser
	 *            登录用户对象
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(final SysUser sysUser, final HttpServletRequest request,
			final HttpServletResponse response) {
		return sysLoginService.doLogin(sysUser, request, response);
	}

	/**
	 * 显示登陆界面.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return view
	 */
	@RequestMapping(value = { "/login.html", "/login" }, method = RequestMethod.GET)
	public ModelAndView loginView(final HttpServletRequest request, final HttpServletResponse response) {
		return new ModelAndView("/login");
	}

}
