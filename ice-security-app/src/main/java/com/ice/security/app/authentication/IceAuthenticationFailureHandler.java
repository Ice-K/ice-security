package com.ice.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ice.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Description:认证失败后的处理
 * Cteated by wangpeng
 * 2018/3/10 23:42
 */
@Component("iceAuthenticationFailureHandler")
public class IceAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");

        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());//500
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));

    }
}
