package com.ice.security.core.properties;

import com.ice.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 自定义properties属性注册类
 * Cteated by wangpeng
 * 2018/3/10 22:14
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
