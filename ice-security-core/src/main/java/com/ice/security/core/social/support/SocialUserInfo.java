package com.ice.security.core.social.support;

/**
 * Description：社交用户信息
 * Cteated by wangpeng
 * 2018/3/16 9:19
 */
public class SocialUserInfo {

    private String providerId;
    private String providerUserId;
    private String nickname;
    private String headimage;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }
}
