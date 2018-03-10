package com.ice.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/10 13:22
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //可以注入相应的dao
    //@Autowired

    @Autowired
    private PasswordEncoder passwordEncoder;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录名：" + username);

        //根据用户名查找用户信息（此处为dao）
        //根据查找到的用户信息判断用户是否被删除
        //根据查找到的用户信息判断用户是否过期
        //根据查找到的用户信息判断密码是否过期
        //根据查找到的用户信息判断用户是否被锁定

        //username为用户传来的，password为数据库中查出的，AuthorityUtils.commaxxxxx为授权代码
        String str = passwordEncoder.encode("123456");
        logger.info("密码为："+str);
        return new User(username,
                str,
                true,//用户是否被删除
                true,//用户是否过期
                true,//密码是否过期
                true,//用户是否被锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
