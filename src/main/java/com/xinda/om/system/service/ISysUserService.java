package com.xinda.om.system.service;

import com.xinda.om.sys.exception.BaseException;
import com.xinda.om.sys.exception.SysException;
import com.xinda.om.system.dto.SysUser;

/**
 * 用户处理Service.
 * 
 * @author coudy
 *
 *         2017年2月24日
 */
public interface ISysUserService {

	/**
	 * 查询User信息.
	 * 
	 * @param user
	 *            User对象
	 * @return User信息
	 */
	SysUser getSysUserById(int userId);

	/**
	 * 获取User对象信息.
	 * 
	 * @param sysUser
	 * @return SysUser
	 */
	SysUser getSysUser(SysUser sysUser);

	/**
	 * 校验登录User信息.
	 * 
	 * @param sysUser
	 * @return
	 * @throws BaseException
	 *             SysUser
	 */
	SysUser validateLoginInfo(SysUser sysUser) throws SysException;

}
