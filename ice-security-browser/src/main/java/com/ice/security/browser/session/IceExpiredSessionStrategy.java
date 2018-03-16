package com.ice.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/16 17:31
 */
public class IceExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {


    /**
     * @param invalidSessionUrl
     */
    public IceExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
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
