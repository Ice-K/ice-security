package com.ice.security.core.support;

/**
 * Description: 简单响应的封装类
 * Cteated by wangpeng
 * 2018/3/10 21:35
 */
public class SimpleResponse {

    public SimpleResponse(Object content){
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
