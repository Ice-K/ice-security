package com.ice.security.core.social.wechat.config;

import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.properties.social.wechat.WeChatProperties;
import com.ice.security.core.social.IceConnectView;
import com.ice.security.core.social.wechat.connect.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * Description：微信登录配置
 *
 * Cteated by wangpeng
 * 2018/3/16 10:28
 */
@Configuration
@ConditionalOnProperty(prefix = "ice.security.social.wechat", name = "app-id")
public class WeChatAutoConfiguration extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * (non-Javadoc)
     *
     * @see
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     * #createConnectionFactory()
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeChatProperties weChatConfig = securityProperties.getSocial().getWechat();
        return new WeChatConnectionFactory(weChatConfig.getProviderId(), weChatConfig.getAppId(),
                weChatConfig.getAppSecret());
    }

    /**connect/weixinConnected绑定
     * connect/weixinConnect解绑
     * @return
     */
    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weChatConnectedView() {
        return new IceConnectView();
    }
}
