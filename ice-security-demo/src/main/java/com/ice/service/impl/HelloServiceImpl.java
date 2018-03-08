package com.ice.service.impl;

import com.ice.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/8 17:47
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        return "Hello " + name;
    }
}
