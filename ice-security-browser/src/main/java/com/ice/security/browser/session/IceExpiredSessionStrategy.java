package com.ice.security.browser.session;

import com.ice.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Description：并发登录导致session失效时，默认的处理策略
 * Cteated by wangpeng
 * 2018/3/16 17:31
 */
public class IceExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {


    /**
     * @param securityProperties
     */
    public IceExpiredSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    /**
     * (non-Javadoc)
     * @see org.springframework.security.web.session.SessionInformationExpiredStrategy#onExpiredSessionDetected(org.springframework.security.web.session.SessionInformationExpiredEvent)
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    /**
     * (non-Javadoc)
     * @see com.ice.security.browser.session.AbstractSessionStrategy#isConcurrency()
     */
    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
