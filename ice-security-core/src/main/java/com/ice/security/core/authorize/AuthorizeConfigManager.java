package com.ice.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Description:授权信息管理器， 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * Cteated by wangpeng
 * 2018/3/20 23:08
 */
public interface AuthorizeConfigManager {

    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
