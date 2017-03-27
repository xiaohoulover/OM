package com.xinda.system.sys.service.Impl;

import com.xinda.system.sys.exception.BaseException;
import com.xinda.system.sys.service.ISysLoginService;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class SysLoginServiceImpl implements ISysLoginService {

    private Logger logger = LoggerFactory.getLogger(SysLoginServiceImpl.class);

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
            // 日志
            logger.info("User:[{}] Login ...[{}]",
                    sysUser.getUserName(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //保存session
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", sysUser.getUserId());
            session.setAttribute("userName", sysUser.getUserName());

            view.setViewName("redirect:/");
        } catch (BaseException e) {
            view.addObject("_UserName", sysUser.getUserName());
            view.addObject("msg", e.getMessage());
            view.addObject("code", e.getCode());
        }
        return view;
    }

    @Override
    public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ModelAndView("redirect:/" + "login");
    }
}
