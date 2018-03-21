package com.ice.security.core.social;

import com.ice.security.core.social.support.SocialUserInfo;
import org.springframework.social.connect.Connection;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/20 22:47
 */
public abstract class SocialController {

    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadImage(connection.getImageUrl());
        return userInfo;
    }
}
