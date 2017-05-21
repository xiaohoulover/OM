package com.xinda.system.sys.interceptor;

import com.xinda.system.sys.contant.RedisContants;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.service.ISysUserService;
import com.xinda.um.user.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 初始化加载Redis数据.
 *
 * @author Coundy.
 * @date 2017/5/21 21:57.
 */
public class LoadRedisDataInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ISysUserService sysUserServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //1.将所有所有User信息写入Redis中
        /*List<SysUser> userList = sysUserServiceImpl.queryAllUsers();
        ListOperations<String, SysUser> listOperations = redisTemplate.opsForList();
        listOperations.rightPushAll(RedisContants.OM_USER_KEY, userList);*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
