package com.ice.dto;

import java.io.Serializable;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/9 15:24
 */

public class FileInfo implements Serializable {
    private static final long serialVersionUID = 8863533743104267886L;

    private String path;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
