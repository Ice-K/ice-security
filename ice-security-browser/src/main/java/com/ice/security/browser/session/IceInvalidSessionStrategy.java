package com.ice.security.browser.session;

import com.ice.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description：默认的session失效处理策略
 * Cteated by wangpeng
 * 2018/3/16 18:42
 */
public class IceInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {
    /**
     * @param securityProperties
     */
    public IceInvalidSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        onSessionInvalid(httpServletRequest, httpServletResponse);
    }
}
