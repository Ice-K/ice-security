package com.ice.security.core.social.qq.connet;

import com.ice.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Description: connectionFactory
 * Cteated by wangpeng
 * 2018/3/15 1:00
 */
public class QQconnectionFactory extends OAuth2ConnectionFactory<QQ> {


    public QQconnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
