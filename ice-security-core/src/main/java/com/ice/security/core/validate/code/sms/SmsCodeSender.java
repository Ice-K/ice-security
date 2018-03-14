package com.ice.security.core.validate.code.sms;

/**
 * Description：短信服务供应商接口
 * Cteated by wangpeng
 * 2018/3/12 17:40
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
