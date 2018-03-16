package com.ice.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Description：这是配置QQ登录是否需要自定义注册或者绑定的类
 * 如果需要自定义注册页面，则不需要此类，
 * 如果不需要自定义注册页面，由系统默认注册，则需要此类
 * Cteated by wangpeng
 * 2018/3/16 9:42
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp  {


    @Override
    public String execute(Connection<?> connection) {

        //根据社交用户信息默认创建用户并放回用户唯一标识
        return connection.getDisplayName();
    }
}
