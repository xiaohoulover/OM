package com.xinda.sm.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 退出登录后处理Handler
 *
 * @author Coundy.
 * @date 2017/7/5 10:42.
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("------------------Logout:[IP:{}][HOST:{}][DATE[{}]]-----------------------",
                request.getRemoteAddr(), request.getRemoteHost(), new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").format(new Date()));
        super.onLogoutSuccess(request, response, authentication);
    }
}
