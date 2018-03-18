package com.ice.security.app.social.impl;

import com.ice.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/19 0:28
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler iceAuthenticationSuccessHandler;

    /**
     * (non-Javadoc)
     * @see com.ice.security.core.social.SocialAuthenticationFilterPostProcessor#process(org.springframework.social.security.SocialAuthenticationFilter)
     */
    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(iceAuthenticationSuccessHandler);
    }
}
