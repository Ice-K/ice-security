package com.ice.security.app;

import com.ice.security.app.social.AppSignUpUtils;
import com.ice.security.core.properties.SecurityConstants;
import com.ice.security.core.social.SocialController;
import com.ice.security.core.social.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/19 11:48
 */
@RestController
public class AppSecurityController extends SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    /**
     * 需要注册时跳到这里，返回401和用户信息给前端
     * @param request 请求
     * @return  401 用户信息
     */
    @GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)//401
    private SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
        return buildSocialUserInfo(connection);
    }
}
