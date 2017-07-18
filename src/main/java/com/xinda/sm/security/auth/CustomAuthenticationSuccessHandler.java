package com.xinda.sm.security.auth;

import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Spring Security拦截处理登录成功后处理Handler.
 *
 * @author Coundy.
 * @date 2017/7/4 12:07.
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        logger.info("---------------------Start CustomAuthenticationSuccessHandler-------------------------");
        //获取CustomUserDetailsService中存储到Spring全局变量SecurityContextHolder中设置到Authentication中的值
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.selectUserByUserName(userDetails.getUsername());
        //保存session
        HttpSession session = request.getSession(true);
        session.setAttribute(BaseConstants.SYS_USER_FIELD_ID, sysUser.getUserId());
        session.setAttribute(BaseConstants.SYS_USER_FIELD_NAME, sysUser.getUserName());
        session.setAttribute(BaseConstants.SYS_USER_FIELD_TYPE, sysUser.getUserType());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
