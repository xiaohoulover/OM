package com.xinda.sm.security.service.impl;

import com.xinda.sm.security.service.IEncryptionService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 加密处理实现类.
 *
 * @author coudy
 *         <p>
 *         2017年3月8日
 */
@Service
public class EncryptionServiceImpl implements IEncryptionService {

    /**
     * MD5非对称加密处理.(明文加密后无法解密为原明文)
     *
     * @param password 原始明文字符串
     * @return
     */
    @Override
    public String encode(String password) {
        return DigestUtils.md5DigestAsHex(password.toUpperCase().getBytes()).toUpperCase();
    }

    //指定DES加密解密的密钥
    private static String KEY_STR = "myKey";

    private static Key generateKey() {
        Key key = null;
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(new SecureRandom(KEY_STR.getBytes()));
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    /**
     * DES加密.返回Base64编码的加密字符串
     *
     * @param encryptStr 明文字符串
     * @return
     */
    public static String getEncryptString(String encryptStr) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte[] encryptBytes = null;
        try {
            //可添加编码格式(utf8)
            byte[] b = encryptStr.getBytes();
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, generateKey());
            encryptBytes = cipher.doFinal(b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return base64Encoder.encode(encryptBytes);
    }

    /**
     * DES解密.对Base64编码的字符串解密.
     *
     * @param decryptStr Base64编码DES加密的密文字符串
     * @return
     */
    public static String getDecryptString(String decryptStr) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] decryptStrBytes = null;
        try {
            byte[] decryptBytes = base64Decoder.decodeBuffer(decryptStr);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, generateKey());
            decryptStrBytes = cipher.doFinal(decryptBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //可添加编码格式(utf8)
        return new String(decryptStrBytes);
    }

    public static void main (String[] args) {
        String str[] = {"sysadmin"};
        for (String s : str) {
            System.out.println(getEncryptString(s));
        }
      //  System.out.println(getDecryptString("WnplV/ietfQ="));
    }

}
