package com.xinda.system.login.service.impl;

import com.xinda.system.login.exception.SysException;
import com.xinda.system.login.service.ISysLoginService;
import com.xinda.system.login.service.IVerificationCodeService;
import com.xinda.system.sys.event.ReLoadCacheEvent;
import com.xinda.system.sys.exception.BaseException;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录实现类.
 *
 * @Author Coundy.
 * @Date 2017/3/27 23:10
 */
@Service
@Transactional
public class SysLoginServiceImpl implements ISysLoginService {


    private final Logger logger = LoggerFactory.getLogger(SysLoginServiceImpl.class);

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IVerificationCodeService verificationCodeService;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void beforeLoign(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public ModelAndView doLogin_MVC(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) throws SysException {
        ModelAndView view = new ModelAndView();
        view.setViewName("/login");
        // 记录用户输入的用户名，登录失败刷新页面时，不需要重新输入

        //try {
        beforeLoign(sysUser, request, response);
        //验证码校验
        //verificationCodeService.valiLoginVerificationCode(request);
        // 用户名及密码校验
        sysUser = sysUserService.validateLoginInfo(sysUser);
        // 日志
        logger.info("User:[{}] Login ...[{}]",
                sysUser.getUserName(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //保存session
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", sysUser.getUserId());
        session.setAttribute("userType", sysUser.getUserType());
        session.setAttribute("userName", sysUser.getUserName());
        view.setViewName("redirect:/");
        //afterLogin(sysUser, request, response);
        /*} catch (BaseException e) {
            view.addObject("_UserName", sysUser.getUserName());
            view.addObject("msg", e.getMessage());
            view.addObject("code", e.getCode());
        }*/
        return view;
    }

    @Override
    public ModelAndView doLogin(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/login");


        String code = SysException.MSG_ERROR_SYS_USERNAME_PASSWORD_ERROR;
        Throwable exception = (Exception) request.getAttribute("exception");

        if (exception instanceof BaseException) {
            code = ((BaseException) exception).getCode();
        }
        Boolean isError = (Boolean) request.getAttribute("error");
        if (isError && null != isError) {
            view.addObject("msg", code);
        }
        return view;
    }

    @Override
    public void afterLogin(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        String sqlId = "com.xinda.um.user.mapper.SysUserMapper.getSysUsers";
        applicationContext.publishEvent(new ReLoadCacheEvent(new Object(), "user", sqlId));
    }

    @Override
    public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ModelAndView("redirect:" + "/login");
    }
}
