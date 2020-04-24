package com.kingtrustcloud.familytree.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号配置
 */
@Data
@ConfigurationProperties(prefix = "wx.mp")
public class MpProperties {
    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;
}
