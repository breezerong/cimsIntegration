package com.plm.cimsi.bpm.entity;

import java.io.Serializable;

/**
 * 配置
 */
public class Settings implements Serializable {
    public String getBPMBaseURL() {
        return BPMBaseURL;
    }

    public void setBPMBaseURL(String BPMBaseURL) {
        this.BPMBaseURL = BPMBaseURL;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 工作流接口地址
     */
    private String BPMBaseURL;
    /**
     * 工作流访问后缀
     */
    private String suffix;
}
