package com.ice.security.app.social;

import com.ice.security.core.properties.SecurityConstants;
import com.ice.security.core.social.IceSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Description：在spring容器初始化之前和之后执行下面两个方法
 * Cteated by wangpeng
 * 2018/3/19 11:41
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "iceSocialSecurityConfig")) {
            IceSpringSocialConfigurer configurer = (IceSpringSocialConfigurer) bean;
            configurer.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            return configurer;
        }
        return bean;
    }
}
