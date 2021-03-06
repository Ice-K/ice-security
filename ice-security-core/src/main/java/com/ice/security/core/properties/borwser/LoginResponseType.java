package com.ice.security.core.properties.borwser;

/**
 * Description:登录类型枚举，用来配置认证结果的返回（json数据或者跳转）
 * Cteated by wangpeng
 * 2018/3/10 23:58
 */
public enum LoginResponseType {
    /**
     * 跳转
     */
    REDIRECT,

    /**
     * 返回json
     */
    JSON
}
