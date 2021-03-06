package com.ice.security.core.properties.OAuth2;

/**
 * Description：认证服务器注册的第三方应用配置项
 * Cteated by wangpeng
 * 2018/3/19 14:18
 */
public class OAuth2ClientProperties {

    /**第三方应用appId.*/
    private String clientId;

    /**第三方应用appSecret.*/
    private String clientSecret;

    /**针对此应用发出的token的有效时间，单位秒.*/
    private int accessTokenValiditySeconds = 7200;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }
}
