package com.kingtrustcloud.familytree.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号配置
 */
@Data
@ConfigurationProperties(prefix = "sms.tencent")
public class SmsProperties {

    private String secretid;

    private String secretkey;

    private String appid;

    private String appkey;

    private String sign;

    private String templateid;

}
