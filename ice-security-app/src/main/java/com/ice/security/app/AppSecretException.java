package com.ice.security.app;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/19 11:28
 */
public class AppSecretException extends RuntimeException {

    private static final long serialVersionUID = 6631619451459713928L;

    public AppSecretException(String message) {
        super(message);
    }
}
