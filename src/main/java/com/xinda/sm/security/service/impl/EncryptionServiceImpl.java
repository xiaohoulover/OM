package com.xinda.sm.security.service.impl;

import com.xinda.sm.security.service.IEncryptionService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 加密处理实现类.
 *
 * @author coudy
 *         <p>
 *         2017年3月8日
 */
@Service
public class EncryptionServiceImpl implements IEncryptionService {

    @Override
    public String encode(String password) {
        return DigestUtils.md5DigestAsHex(password.toUpperCase().getBytes()).toUpperCase();
    }

}
