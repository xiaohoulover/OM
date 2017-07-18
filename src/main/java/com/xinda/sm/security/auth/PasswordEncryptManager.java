package com.xinda.sm.security.auth;

import com.github.pagehelper.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 密码加密处理.
 * 盐值不需要用户提供，每次随机生成；多重加密——迭代SHA算法+密钥+随机盐来对密码加密，大大增加密码破解难度，加密后得到的密码是80位
 *
 * @author Coundy.
 * @date 2017/7/17 8:13.
 */
public class PasswordEncryptManager implements PasswordEncoder {

    private PasswordEncoder passwordEncoder = new StandardPasswordEncoder("my-secret-key");//密钥值

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder("my-secret-key");//密钥值
        System.out.println(passwordEncoder.encode("admin123"));
    }

    @Override
    public String encode(CharSequence charSequence) {
        return passwordEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        if (StringUtil.isEmpty(encodedPassword)) {
            return false;
        }
        return passwordEncoder.matches(charSequence, encodedPassword);
    }
}
