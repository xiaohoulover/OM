package com.xinda.om.system.mapper;

import com.xinda.om.system.dto.SysUser;

/**
 * 用户UserMapper.
 * 
 * @author coudy
 *
 *         2017年2月27日
 */
public interface SysUserMapper {

	int deleteByPrimaryKey(Integer userId);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	/**
	 * 获取User信息.
	 * 
	 * @param sysUser
	 *            User参数对象
	 * @return SysUser
	 */
	SysUser getSysUser(SysUser sysUser);

}
