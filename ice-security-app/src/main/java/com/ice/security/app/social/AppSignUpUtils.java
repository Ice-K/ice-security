package com.ice.security.app.social;

import com.ice.security.app.AppSecretException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;


/**
 * Description：app环境下替换providerSignInUtils，避免由于没有session导致读不到社交用户信息的问题
 * Cteated by wangpeng
 * 2018/3/19 11:21
 */
@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * 缓存社交网站用户信息到redis
     * @param request 请求
     * @param connectionData 用户信息
     */
    public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
    }

    /**
     * 将缓存的社交网站用户信息与系统注册用户信息绑定
     * @param request 请求
     * @param userId    用户id
     */
    public void doPostSignUp(WebRequest request, String userId) {

        String key = getKey(request);
        if (!redisTemplate.hasKey(key)) {
            throw new AppSecretException("无法找到缓存的用户社交账号信息");
        }

        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId()).createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);
    }

    private String getKey(WebRequest request) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new AppSecretException("设备id参数不能为空");
        }

        return "ice:security:social.connect." + deviceId;
    }
}
