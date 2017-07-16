package com.xinda.sm.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * 调度器，调度管理各个安全过滤器.
 *
 * @author Coundy.
 * @date 2017/7/6 11:39.
 */
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private Logger logger = LoggerFactory.getLogger(CustomFilterSecurityInterceptor.class);

    /**
     * 权限资源管理器.
     */
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.filterInvocationSecurityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 登陆后，每次访问资源都通过这个拦截器拦截.
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("------------------------Start CustomFilterSecurityInterceptor doFilter---------------------------------");
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
        logger.info("------------------------End CustomFilterSecurityInterceptor doFilter---------------------------------");
    }

    /**
     * fi里面有一个被拦截的url
     * 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     */
    public void invoke(FilterInvocation fi) {

        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {

    }

    public FilterInvocationSecurityMetadataSource getFilterInvocationSecurityMetadataSource() {
        return filterInvocationSecurityMetadataSource;
    }

    public void setFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
    }

}