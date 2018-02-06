package com.xinda.um.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinda.sm.security.service.IEncryptionService;
import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.system.sys.exception.SysException;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.mapper.SysUserMapper;
import com.xinda.um.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

    private final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private IEncryptionService encryptionService;

    @Override
    public SysUser selectUserByUserName(String userName) {
        return sysUserMapper.selectByUserName(userName);
    }

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
    public SysUser createSysUser(SysUser sysUser) throws SysException {
        //加密存储
        sysUser.setPassword(encryptionService.encode(sysUser.getPassword()));
        //MD5 加盐加密
        try {
            sysUser.setPassword(encryptionService.encodeSalt(sysUser.getPassword(), "sysadmin", true));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (null == sysUser.getUserId()) {//insert
            //校验用户名是否同名
            SysUser user = new SysUser(null, sysUser.getUserName(), null, null, null, null);
            if (null != sysUserMapper.querySysUserBySysUser(user)) {//存在同名的用户名
                logger.debug("The UserName[{}] had exists!", user.getUserName());
                throw new SysException(SysException.MSG_ERROR_SYS_USER_NAME_HAD_EXISTS);
            }
            sysUserMapper.insertSelective(sysUser);
        } else {//update
            //校验旧密码是否输入正确
            SysUser user = sysUserMapper.selectByPrimaryKey(sysUser.getUserId());
            if (!user.getPassword().equals(encryptionService.encode(sysUser.getOldPassword()))) {
                logger.error("原密码输入错误!");
                throw new SysException(SysException.MSG_ERROR_SYS_USER_OLD_PASSWORD_NOT_EQUALS);
            }
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }
        return sysUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<SysUser> batchSaveOrUpdateSysUsers(List<SysUser> sysUsers) throws SysException {
        for (SysUser sysUser : sysUsers) {
            if (BaseConstants.BASE_DTO_DELETE.equals(sysUser.get__status())
                    && null == sysUser.getUserId()) {
                continue;
            }
            switch (sysUser.get__status()) {
                case BaseConstants.BASE_DTO_ADD:
                    sysUserMapper.insertSelective(sysUser);
                    break;
                case BaseConstants.BASE_DTO_UPDATE:
                    sysUserMapper.updateByPrimaryKeySelective(sysUser);
                    break;
                case BaseConstants.BASE_DTO_DELETE:
                    sysUserMapper.deleteByPrimaryKey(sysUser.getUserId());
                    break;
                default:
                    break;
            }
        }
        return sysUsers;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<SysUser> deleteSysUsers(List<SysUser> sysUsers) throws SysException {
        for (SysUser sysUser : sysUsers) {
            if (null == sysUser.getUserId()) {
                continue;
            }
            sysUserMapper.deleteByPrimaryKey(sysUser.getUserId());
        }
        return sysUsers;
    }
}
