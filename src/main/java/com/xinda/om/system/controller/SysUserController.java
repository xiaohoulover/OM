package com.xinda.om.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinda.om.system.dto.SysUser;
import com.xinda.om.system.service.ISysUserService;

/**
 * 用户Controller.
 * 
 * @author coudy
 *
 *         2017年2月24日
 */
@Controller
public class SysUserController {

	Logger logger = Logger.getLogger(SysUserController.class);

	@Autowired
	private ISysUserService sysUserService;

	@RequestMapping("/user/getUserById")
	@ResponseBody
	public String toUserHtml() {
		logger.info("Controller ...");
		SysUser user = sysUserService.getSysUserById(1);
		logger.info(user);
		return "index";
	}

}
