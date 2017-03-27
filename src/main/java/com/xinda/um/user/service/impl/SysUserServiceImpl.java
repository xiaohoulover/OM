package com.xinda.um.user.service.impl;

import com.xinda.system.sys.exception.SysException;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.mapper.SysUserMapper;
import com.xinda.um.user.service.ISysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * User实现类.
 *
 * @Author Coundy.
 * @Date 2017/3/27 22:12
 */
@Service
@Transactional
public class SysUserServiceImpl implements ISysUserService {

    Logger logger = Logger.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getSysUserById(int userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser getSysUser(SysUser sysUser) {
        return null;
    }

    @Override
    public SysUser validateLoginInfo(SysUser sysUser) throws SysException {
        // 校验登录信息
        if (StringUtils.isEmpty(sysUser.getUserName()) || StringUtils.isEmpty(sysUser.getPassword())) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_NOT_NULL);
        }
        SysUser user = sysUserMapper.getSysUser(sysUser);
        if (null == user) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_ERROR);
        }
        if (!"Y".equals(user.getStatus())) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_INVALID);
        }
        if (!sysUser.getUserName().equals(user.getUserName()) || !sysUser.getPassword().equals(user.getPassword())) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_ERROR);
        }
        return user;
    }

}
