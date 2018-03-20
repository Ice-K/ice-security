package com.ice.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ice.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 默认的退出成功处理器，如果设置了imooc.security.browser.signOutUrl，则跳到配置的地址上，
 *              如果没配置，则返回json格式的响应。
 * Cteated by wangpeng
 * 2018/3/18 15:42
 */
public class IceLogoutSuccessHandler implements LogoutSuccessHandler{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String logoutSuccessPage;
    private ObjectMapper objectMapper = new ObjectMapper();

    public IceLogoutSuccessHandler(String logoutSuccessPage) {
        this.logoutSuccessPage = logoutSuccessPage;
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        logger.info("退出成功");
        if (StringUtils.isBlank(logoutSuccessPage)) {//如果没有配置退出后的页面则返回json数据
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        } else {
            httpServletResponse.sendRedirect(logoutSuccessPage);//重定向
        }
    }
}
