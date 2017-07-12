package com.xinda.sm.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * 决策访问器(授权管理器).
 * 根据用户角色去判断是否有足够的权限去访问资源.
 * 通过登录用户的权限信息、资源、获取资源所需的权限来根据不同的授权策略来判断用户是否有权限访问资源。
 *
 * @author Coundy.
 * @date 2017/7/6 11:29.
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {

    private Logger logger = LoggerFactory.getLogger(CustomAccessDecisionManager.class);

    /**
     * 检查用户是否够权限访问资源.
     *
     * @param authentication   spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
     * @param o                封装有url的对象
     * @param configAttributes CustomInvocationSecurityMetadataSource.getAttributes中获取的 所需权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        logger.info("---------------------Start CustomAccessDecisionManager------------------------------");
        if (configAttributes == null) {
            return;
        }
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig) ca).getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) {
                    logger.info("---------------------End CustomAccessDecisionManager------------------------------");
                    return;
                }
            }
        }
        //注意：执行这里，后台是会抛异常的，但是界面会跳转到所配的access-denied-page页面
        //throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
