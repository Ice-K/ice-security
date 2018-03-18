package com.ice.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Description: 处理用户获取逻辑
 * Cteated by wangpeng
 * 2018/3/10 13:22
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //可以注入相应的dao
    //@Autowired

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 表单登录
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名：" + username);

        //根据用户名查找用户信息（此处为dao）
        //根据查找到的用户信息判断用户是否被删除
        //根据查找到的用户信息判断用户是否过期
        //根据查找到的用户信息判断密码是否过期
        //根据查找到的用户信息判断用户是否被锁定

        //username为用户传来的，password为数据库中查出的，AuthorityUtils.commaxxxxx为授权代码
        return buildUser(username);
    }




    /**
     * 社交登录
     * @param userId 用户id
     * @return SocialUserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交用户id：" + userId);

        //根据用户名查找用户信息（此处为dao）
        //根据查找到的用户信息判断用户是否被删除
        //根据查找到的用户信息判断用户是否过期
        //根据查找到的用户信息判断密码是否过期
        //根据查找到的用户信息判断用户是否被锁定

        //username为用户传来的，password为数据库中查出的，AuthorityUtils.commaxxxxx为授权代码
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        String password = passwordEncoder.encode("123456");
        logger.info("密码为：" + password);
        return new SocialUser(userId,
                password,
                true,//用户是否被删除
                true,//用户是否过期
                true,//密码是否过期
                true,//用户是否被锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }
}
