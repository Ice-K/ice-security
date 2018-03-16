package com.ice.security.core.social.wechat.api;

/**
 * Description：微信API调用接口,返回微信用户的信息
 * Cteated by wangpeng
 * 2018/3/16 10:12
 */
public interface WeChat {

    WeChatUserInfo getUserInfo(String openId);
}
