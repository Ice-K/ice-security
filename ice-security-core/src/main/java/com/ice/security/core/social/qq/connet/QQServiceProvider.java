package com.ice.security.core.social.qq.connet;

import com.ice.security.core.social.qq.api.QQ;
import com.ice.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Description:装配ServiceProvider服务提供商
 * Cteated by wangpeng
 * 2018/3/15 0:15
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    /**应用的id*/
    private String appId;

    /**引导用户跳转的地址*/
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**获取token的地址*/
    private static final String URL_ACCESS_TOKEN ="https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
