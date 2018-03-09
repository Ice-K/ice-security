package com.ice.exception;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/9 9:02
 */
public class MyException extends RuntimeException {
    private static final long serialVersionUID = -6013645302905976487L;

    private Integer code;

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
