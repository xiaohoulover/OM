package com.xinda.um.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinda.sm.security.service.IEncryptionService;
import com.xinda.system.sys.exception.SysException;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.mapper.SysUserMapper;
import com.xinda.um.user.service.ISysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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
    @Autowired
    private IEncryptionService encryptionService;

    @Override
    public SysUser getSysUserById(int userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysUser> querySysUser(SysUser sysUser, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return sysUserMapper.getSysUsers(sysUser);
    }

    @Override
    public SysUser validateLoginInfo(SysUser sysUser) throws SysException {
        // 校验登录信息
        if (StringUtils.isEmpty(sysUser.getUserName()) || StringUtils.isEmpty(sysUser.getPassword())) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_NOT_NULL);
        }
        SysUser user = sysUserMapper.querySysUserBySysUser(sysUser);
        if (null == user) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NOT_EXISTS);
        }
        if (!"Y".equals(user.getStatus())) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_INVALID);
        }
        if (!sysUser.getUserName().equals(user.getUserName())) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_ERROR);
        }
        if (!encryptionService.encode(sysUser.getPassword()).equalsIgnoreCase(user.getPassword())) {
            throw new SysException(SysException.MSG_ERROR_SYS_USER_PASSWORD_ERROR);
        }
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public SysUser saveSysUser(SysUser sysUser) throws SysException {
        //加密存储
        sysUser.setPassword(encryptionService.encode(sysUser.getPassword()));
        if (null == sysUser.getUserId()) {
            sysUserMapper.insertSelective(sysUser);
        } else {
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }
        return sysUser;
    }

    @Override
    public List<SysUser> deleteSysUsers(List<SysUser> sysUsers) {
        for (SysUser sysUser : sysUsers) {
            if (null == sysUser.getUserId()) {
                continue;
            }
            sysUserMapper.deleteByPrimaryKey(sysUser.getUserId());
        }
        return sysUsers;
    }
}
