package com.ice.security.browser.support;

/**
 * Description: 封装BrowserSecurityController返回的数据
 * Cteated by wangpeng
 * 2018/3/10 21:35
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getObject() {
        return content;
    }

    public void setObject(Object content) {
        this.content = content;
    }
}
