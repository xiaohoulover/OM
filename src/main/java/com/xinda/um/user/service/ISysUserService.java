package com.xinda.um.user.service;

import com.xinda.system.sys.exception.BaseException;
import com.xinda.system.sys.exception.SysException;
import com.xinda.um.user.dto.SysUser;

import java.util.List;

/**
 * 用户处理Service.
 *
 * @Author Coundy.
 * @Date 2017/3/27 22:13
 */
public interface ISysUserService {


    /**
     * 根据userName查询用户信息.
     *
     * @param userName 用户名
     * @return
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 根据userId查询User信息.
     *
     * @param userId User对象
     * @return User信息
     */
    SysUser getSysUserById(int userId);

    /**
     * 获取User对象信息.
     *
     * @param sysUser
     * @param page
     * @param pageSize
     * @return SysUser
     */
    List<SysUser> querySysUser(SysUser sysUser, int page, int pageSize);

    /**
     * 校验登录User信息.
     *
     * @param sysUser
     * @return
     * @throws BaseException SysUser
     */
    SysUser validateLoginInfo(SysUser sysUser) throws SysException;

    /**
     * 保存用户信息.
     *
     * @param sysUser
     * @return
     * @throws SysException
     */
    SysUser createSysUser(SysUser sysUser) throws SysException;

    /**
     * 批量保存用户信息.
     *
     * @param sysUsers
     * @return
     */
    List<SysUser> batchSaveOrUpdateSysUsers(List<SysUser> sysUsers) throws SysException;

    /**
     * 删除用户.
     *
     * @param sysUsers
     * @return
     */
    List<SysUser> deleteSysUsers(List<SysUser> sysUsers) throws SysException;

}
