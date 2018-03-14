package com.ice.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:封装短信验证码信息
 * Cteated by wangpeng
 * 2018/3/11 17:54
 */
public class ValidateCode implements Serializable{

    private static final long serialVersionUID = -1018268752607908812L;


    /** 验证码字符串. */
    private String code;

    /** 验证码有效时间单位（秒）. */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }


    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }


    /**
     * 验证是否过期
     * @return boolean
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
