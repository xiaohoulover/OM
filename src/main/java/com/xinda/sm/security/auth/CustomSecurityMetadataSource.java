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
 * 主要是定义Url资源与权限之间的关系，并提供一个通过资源获取所有权限的方法.
 *
 * @author Coundy.
 * @date 2017/7/6 11:21.
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private Logger logger = LoggerFactory.getLogger(CustomSecurityMetadataSource.class);
    private PathMatcher pathMatcher = new AntPathMatcher();

    public CustomSecurityMetadataSource() {
        loadResourceDefine();
    }

    /**
     * tomcat开启时加载一次，加载所有url资源和权限（或角色）的对应关系.
     * 中间关系数据有变化，那么就会存在问题.
     */
    private void loadResourceDefine() {
        logger.info("------------------Start CustomSecurityMetadataSource.loadResourceDefine--------------------------");
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> attributeList1 = new ArrayList<ConfigAttribute>();
        ConfigAttribute attribute1 = new SecurityConfig("ROLE_USER");
        attributeList1.add(attribute1);
        resourceMap.put("/home.html", attributeList1);

        Collection<ConfigAttribute> attributeList2 = new ArrayList<ConfigAttribute>();
        ConfigAttribute attribute2 = new SecurityConfig("ROLE_SYS");
        attributeList2.add(attribute2);
        resourceMap.put("/order/om_order_query.html", attributeList2);

        Collection<ConfigAttribute> attributesList3 = new ArrayList<ConfigAttribute>();
        ConfigAttribute attribute3 = new SecurityConfig("ROLE_USER");
        attributesList3.add(attribute3);
        resourceMap.put("/", attributesList3);

        Collection<ConfigAttribute> attributeList4 = new ArrayList<ConfigAttribute>();
        ConfigAttribute attribute4 = new SecurityConfig("ROLE_USER");
        attributeList4.add(attribute4);
        resourceMap.put("/order/om_order_create.html", attributeList4);

        logger.info("------------------End CustomSecurityMetadataSource.loadResourceDefine--------------------------");
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
        logger.info("------------------------Start CustomSecurityMetadataSource.getAttributes--------------------------");
        String reqUrl = ((FilterInvocation) o).getRequestUrl();
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String resourceUrl = iterator.next();
            if (urlMatcher(reqUrl, resourceUrl)) {
                //如果存在匹配的url则返回需具备的权限
                return resourceMap.get(resourceUrl);
            }
        }
        // 当系统中没配资源权限url，用户在访问这个资源的情况下，返回null 表示放行 ，
        // 如果当系统分配了资源url,但是这个用户立属的角色没有则 提示用户无权访问这个页面
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
