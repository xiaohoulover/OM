package com.xinda.sm.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败后处理.
 *
 * @author Coundy.
 * @date 2017/7/16 17:05.
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        logger.info("----------Login Failed-----------");
        httpServletRequest.setAttribute("error", true);
        httpServletRequest.setAttribute("code", "LOGIN_NOT_MATCH");
        httpServletRequest.setAttribute("exception", e);
        httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
    }
}
