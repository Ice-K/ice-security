package com.ice.security.core.social.qq.config;

import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.properties.social.qq.QQProperties;
import com.ice.security.core.social.qq.connet.QQconnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * Description: 自动配置QQ登录
 * Cteated by wangpeng
 * 2018/3/15 2:22
 */
@Configuration
@ConditionalOnProperty(prefix = "ice.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();

        return new QQconnectionFactory(qqConfig.getProviderId(),qqConfig.getAppId(),qqConfig.getAppSecret());
    }
}