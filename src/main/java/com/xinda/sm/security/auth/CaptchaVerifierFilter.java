package com.xinda.sm.security.auth;

import com.xinda.system.login.exception.SysException;
import com.xinda.system.login.service.IVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码Filter。
 * 继承OncePerRequestFilter是为了确保在一次请求只通过一次filter，而不需要重复执行。
 *
 * @author Coundy.
 * @date 2017/7/16 12:12.
 */
public class CaptchaVerifierFilter extends OncePerRequestFilter {

    @Autowired
    private IVerificationCodeService verificationCodeService;

    private RequestMatcher urlRequestMatcher = new AntPathRequestMatcher("/login");

    private String loginUrl = "/loginFailed";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //只有登录时才校验验证码
        if (urlRequestMatcher.matches(request) && "POST".equals(request.getMethod())) {
            if (!verificationCodeService.beforeLoginVerificationCode(request)) {
                request.setAttribute("error", true);
                request.setAttribute("code", "CAPTCHA_INVALID");
                request.setAttribute("exception",
                        new SysException(SysException.MSG_ERROR_SYS_VERIFICATION_CODE_ERROR, null));
                request.getRequestDispatcher(loginUrl).forward(request, response);
                return;
            }
        }
        //继续执行下一个Filter
        filterChain.doFilter(request, response);
    }
}
