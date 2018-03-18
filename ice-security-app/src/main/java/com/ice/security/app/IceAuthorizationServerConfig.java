package com.ice.security.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Description:认证服务器
 * 通过@EnableAuthorizationServer其实就已经在security中实现了认证服务器
 * Cteated by wangpeng
 * 2018/3/18 17:58
 */
@Configuration
@EnableAuthorizationServer
public class IceAuthorizationServerConfig {
}
