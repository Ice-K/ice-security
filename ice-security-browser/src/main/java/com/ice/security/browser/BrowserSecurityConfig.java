package com.ice.security.browser;



import com.ice.security.core.authentication.FormAuthenticationConfig;
import com.ice.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ice.security.core.authorize.AuthorizeConfigManager;
import com.ice.security.core.properties.SecurityProperties;
import com.ice.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * Description: 浏览器环境下安全配置主类
 * Cteated by wangpeng
 * 2018/3/10 1:01
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer iceSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)//校验码相关配置
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)//短信登录相关配置
                .and()
            .apply(iceSocialSecurityConfig)//第三方登录
                .and()
            //记住我配置，如果想在'记住我'登录时记录日志，可以注册一个InteractiveAuthenticationSuccessEvent事件的监听器
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)//session过期
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())//同一个用户登录的最大数量
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())//当session数量达到最大后组织后面的用户登录
                .expiredSessionStrategy(sessionInformationExpiredStrategy)//session并发
                .and()
                .and()
            .logout()
                .logoutUrl("/logout")//退出请求路径
                //.logoutSuccessUrl("ice-logout.html")//退出成功后的路径
                .logoutSuccessHandler(logoutSuccessHandler)//退出成功处理器
                .deleteCookies("JSESSIONID")//退出时从当前浏览器中删除sessionId
                .and()

            .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }


    /**
     * 记住我功能的token存取器配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);//在启动的时候自动创建表(存储user和token)
        return tokenRepository;
    }

}
