package com.xinda.sm.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

/**
 * 资源权限管理器.
 * 主要是定义所有资源与用户权限之间的关系，并提供一个通过资源获取所有权限的方法.
 *
 * @author Coundy.
 * @date 2017/7/6 11:21.
 */
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(CustomInvocationSecurityMetadataSource.class);

    private PathMatcher pathMatcher = new AntPathMatcher();

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public CustomInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    /**
     * tomcat开启时加载一次，加载所有url资源和权限（或角色）的对应关系.
     * 中间关系数据有变化，那么就会存在问题.
     */
    private void loadResourceDefine() {
        logger.info("------------------Start CustomInvocationSecurityMetadataSource.loadResourceDefine--------------------------");
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> attributes1 = new ArrayList<ConfigAttribute>();
        ConfigAttribute attribute1 = new SecurityConfig("ROLE_USER");
        attributes1.add(attribute1);
        resourceMap.put("/home.html", attributes1);
        Collection<ConfigAttribute> attributes2 = new ArrayList<ConfigAttribute>();
        ConfigAttribute attribute2 = new SecurityConfig("ROLE_NO");
        attributes2.add(attribute2);
        resourceMap.put("/order/om_order_query.html", attributes2);
        logger.info("------------------End CustomInvocationSecurityMetadataSource.loadResourceDefine--------------------------");
    }

    /**
     * 查询资源对应权限关系.
     *
     * @param o 封装有访问的url的对象
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        logger.info("------------------------Start CustomInvocationSecurityMetadataSource.getAttributes--------------------------");
        String reqUrl = ((FilterInvocation) o).getRequestUrl();
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String resourceUrl = iterator.next();

            if (urlMatcher(reqUrl, resourceUrl)) {
                logger.info("-----------------End CustomInvocationSecurityMetadataSource.getAttributes-------------------");
                return resourceMap.get(resourceUrl);
            }
        }
        return null;
    }

    public boolean urlMatcher(String path, String url) {
        if (("/**".equals(path)) || ("**".equals(path))) {
            return true;
        }
        if (pathMatcher.match(path, url)) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
