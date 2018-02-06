package com.xinda.sm.security.service.impl;

import com.xinda.sm.security.service.IEncryptionService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    //指定DES加密解密的密钥
    private static String KEY_STR = "myKey";

    @Override
    public String encode(String password) {
        return DigestUtils.md5DigestAsHex(password.toUpperCase().getBytes()).toUpperCase();
    }

    @Override
    public String encodeSalt(String password, String salt, boolean isBase64) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String saltedPass = password + "{" + salt + "}";
        byte[] digest = messageDigest.digest(Utf8.encode(saltedPass));
        if (isBase64) {
            return Utf8.decode(Base64.encode(digest));
        } else {
            return new String(Hex.encode(digest));
        }
    }

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
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");//DES
            cipher.init(Cipher.DECRYPT_MODE, generateKey());
            decryptStrBytes = cipher.doFinal(decryptBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //可添加编码格式(utf8)
        return new String(decryptStrBytes);
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        /*String str[] = {"root", "xd_mysql"};
        for (String s : str) {
            System.out.println(getEncryptString(s));
        }*/
        EncryptionServiceImpl encryptionService = new EncryptionServiceImpl();
        String pw = encryptionService.encodeSalt("admin123", "sysadmin", true);
        System.out.println(pw);

        //  System.out.println(getDecryptString("WnplV/ietfQ="));
    }

}
