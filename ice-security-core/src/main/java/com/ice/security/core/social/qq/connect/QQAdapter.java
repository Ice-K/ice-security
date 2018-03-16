package com.ice.security.core.social.qq.connect;

import com.ice.security.core.social.qq.api.QQ;
import com.ice.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Description: 将个性化的用户信息适配成标准的connection
 * Cteated by wangpeng
 * 2018/3/15 0:48
 */
public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 适配数据
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues connectionValues) {
        QQUserInfo userInfo = api.getQQUserInfo();

        //昵称
        connectionValues.setDisplayName(userInfo.getNickname());
        //头像40×40
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        //空间地址或者微博地址
        connectionValues.setProfileUrl(null);
        //openId
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 适配数据
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do noting
    }
}
