package com.ice.security.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * Description:SocialAuthenticationFilter后处理器，用于在不同环境下个性化社交登录的配置
 * Cteated by wangpeng
 * 2018/3/19 0:13
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * @param socialAuthenticationFilter
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
