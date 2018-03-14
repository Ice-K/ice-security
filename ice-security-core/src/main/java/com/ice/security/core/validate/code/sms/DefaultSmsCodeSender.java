package com.ice.security.core.validate.code.sms;

/**
 * Description：默认短信验证码生成
 * Cteated by wangpeng
 * 2018/3/12 17:42
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机->"+mobile+",发送短信验证码->"+code);
    }
}
