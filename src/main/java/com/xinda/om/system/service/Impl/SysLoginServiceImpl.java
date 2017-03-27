package com.xinda.om.system.service.Impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xinda.om.sys.exception.BaseException;
import com.xinda.om.system.dto.SysUser;
import com.xinda.om.system.service.ISysLoginService;
import com.xinda.om.system.service.ISysUserService;

/**
 * 登录实现类.
 * 
 * @author coudy
 *
 *         2017年3月5日
 */
@Service
public class SysLoginServiceImpl implements ISysLoginService {

	@Autowired
	private ISysUserService sysUserService;

	@Override
	public ModelAndView doLogin(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/login");
		// 记录用户输入的用户名，登录失败刷新页面时，不需要重新输入
		try {
			// 登录校验
			sysUser = sysUserService.validateLoginInfo(sysUser);
			//保存session
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", sysUser.getUserId());

			view.setViewName("redirect:/");
		} catch (BaseException e) {
			view.addObject("_UserName", sysUser.getUserName());
			view.addObject("msg", e.getMessage());
			view.addObject("code", e.getCode());
		}
		return view;
	}

}
