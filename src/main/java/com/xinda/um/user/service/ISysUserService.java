package com.xinda.um.user.service;

import com.xinda.system.sys.exception.BaseException;
import com.xinda.system.sys.exception.SysException;
import com.xinda.um.user.dto.SysUser;

/**
 * 用户处理Service.
 *
 * @Author Coundy.
 * @Date 2017/3/27 22:13
 */
public interface ISysUserService {

    /**
     * 查询User信息.
     *
     * @param userId User对象
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
     * @throws BaseException SysUser
     */
    SysUser validateLoginInfo(SysUser sysUser) throws SysException;

}
