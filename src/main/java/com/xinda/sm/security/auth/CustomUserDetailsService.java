package com.xinda.sm.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 认证管理器.
 * 该类主要是处理用户登录信息，在用户输入用户名和密码后，
 * spring security会带着用户名调用类里面的loadUserByUsername(usrename)方法，
 * 通过用户名查出用户信息，然后把数据库中查出的用户密码和刚刚用户输入的存储在session中的密码做比较，然后判断该用户是否合法！
 *
 * @author Coundy.
 * @date 2017/7/5 13:35.
 */
public class CustomUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    /**
     * 登陆验证时，通过username获取用户的所有权限信息，
     * 并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     *
     * @param userName 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("------------------------Start CustomUserDetailsService----------------------------------");
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

        SimpleGrantedAuthority auth1 = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_USER");

        auths.add(auth1);
        auths.add(auth2);

        User userDetails = new User(userName, "123", true, true, true, true, auths);

        logger.info("------------------------End CustomUserDetailsService----------------------------------");
        return userDetails;
    }
}
