package com.ice.security.core.properties.social;

import com.ice.security.core.properties.social.qq.QQProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Description: 第三方授权登录配置
 * Cteated by wangpeng
 * 2018/3/15 2:16
 */
public class SocialProperties {

    /**QQ*/
    @NestedConfigurationProperty
    private QQProperties qq = new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
