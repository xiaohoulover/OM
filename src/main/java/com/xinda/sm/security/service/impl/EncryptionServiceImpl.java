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
 * DES对称加解密处理实现类.
 * 对称加密算法：加解密的密钥只有一个.
 * DES加密算法：
 * 对于加密:<p>
 * 因为DES是块加密，数据长度必须是8的倍数，然而实际上加密前的明文getBytes()后基本不会恰好是8的倍数，所以一般需要进行填充.<p>
 * 这个只需要设置参数 PKCS5Padding ，JDK就帮你填充了，若不填充，且数据长度不是8倍数，则会抛异常；<p>
 * 对于解密:<p>
 * 一般来说加密的数据长度本身就是8的倍数，所以只需要NoPadding就可以了，若加密的数据长度不是8，就需要用PKCS5Padding，<p>
 * 否则解密出来后的明文尾巴的会比原明文的尾巴多出好几位填充数据。<p>
 * Base64编码：Base64被定义为：Base64内容传送编码被设计用来把任意序列的8位字节描述为一种不易被人直接识别的形式。
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
    private static Key key = null;
    private static String KEY_STR = "myKey";

    /**
     * 创建密钥.
     */
    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(new SecureRandom(KEY_STR.getBytes()));
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
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
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//DES
            cipher.init(Cipher.DECRYPT_MODE, key);
            decryptStrBytes = cipher.doFinal(decryptBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //可添加编码格式(utf8)
        return new String(decryptStrBytes);
    }

    public static void main(String[] args) {
        String str[] = {"sysadmin", "sysadmin"};
        for (String s : str) {
            System.out.println(getEncryptString(s));
        }
        //  System.out.println(getDecryptString("WnplV/ietfQ="));
    }

}
