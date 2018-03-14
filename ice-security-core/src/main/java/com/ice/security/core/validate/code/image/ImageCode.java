package com.ice.security.core.validate.code.image;

import com.ice.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:封装图片验证码信息
 * Cteated by wangpeng
 * 2018/3/11 17:54
 */
public class ImageCode extends ValidateCode implements Serializable{

    private static final long serialVersionUID = -1018268752607908812L;

    /** 验证码图片. */
    private BufferedImage image;

    /** 验证码有效时间单位（秒）. */
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }


    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
