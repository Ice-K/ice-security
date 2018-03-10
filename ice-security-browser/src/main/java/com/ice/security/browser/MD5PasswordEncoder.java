package com.ice.security.browser;

import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.Random;

/**
 * Description:处理密码加密校验
 * Cteated by wangpeng
 * 2018/3/10 13:51
 */
public class MD5PasswordEncoder implements PasswordEncoder {
    private static final String STR = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * MD5加盐加密
     * @param charSequence 用户输入的密码
     * @return  加盐后的密码
     */
    @Override
    public String encode(CharSequence charSequence) {
        String password = charSequence.toString();
        String salt = getSalt();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            assert password != null;
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 校验加盐后是否和原文一直
     * @param charSequence 源密码
     * @param md5   加盐后的密码
     * @return  验证结果
     */
    @Override
    public boolean matches(CharSequence charSequence, String md5) {
        if (md5.length()<48) {
            return false;
        } else {
            char[] cs1 = new char[32];
            char[] cs2 = new char[16];
            for (int i = 0; i < 48; i += 3) {
                cs1[i / 3 * 2] = md5.charAt(i);
                cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
                cs2[i / 3] = md5.charAt(i + 1);
            }
            String salt = new String(cs2);
            return Objects.equals(md5Hex(charSequence.toString() + salt), new String(cs1));
        }
    }


    /**
     * 随机得到16位盐
     * @return 盐值
     */
    private String getSalt() {
        Random r = new Random();
        StringBuilder salt = new StringBuilder(16);
        for (int i = 0; i < 16; i ++) {
            salt.append(STR.charAt(r.nextInt(STR.length())));
        }

        return salt.toString();
    }



    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

}
