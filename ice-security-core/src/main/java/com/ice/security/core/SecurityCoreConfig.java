package com.ice.security.core;

import com.ice.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description: 自定义properties属性注册类
 * Cteated by wangpeng
 * 2018/3/10 22:14
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

    /**
     * 自定义密码加密及校验
     * @return passwordEncoder密码校验器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new MD5PasswordEncoder();//自定义MD5加密
        //return new BCryptPasswordEncoder();//security自带加密
    }
}
