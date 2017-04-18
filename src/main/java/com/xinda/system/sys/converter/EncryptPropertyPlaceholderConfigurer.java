package com.xinda.system.sys.converter;

import com.xinda.sm.security.service.impl.EncryptionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 定义加载外部配置文件密文属性值转换的属性编辑器.
 *
 * @Author Coundy.
 * @Date 2017/4/11 22:56.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private final Logger logger = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);

    private String[] encryptPropNames = {"username", "password"};

    /**
     * 重写继承于PropertyPlaceholderConfigurer类属性转换方法.
     *
     * @param propertyName  配置文件中属性名称
     * @param propertyValue 属性值
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            //返回解密的属性值
            return EncryptionServiceImpl.getDecryptString(propertyValue);
        } else {
            return propertyValue;
        }
    }

    /**
     * 根据属性名称判断是否是需要解密处理的属性.
     *
     * @param propName
     * @return
     */
    private boolean isEncryptProp(String propName) {
        for (String encryptPropName : encryptPropNames) {
            if (encryptPropName.equals(propName)) {
                return true;
            }
        }
        return false;
    }

}
