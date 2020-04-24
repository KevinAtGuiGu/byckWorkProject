package com.kingtrustcloud.familytree.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @version V1.0
 * @Title: AppConfig
 * @Package com.kingtrustcloud.familytree.config
 * @Description: (全局配置类)
 * @author: tanyong
 * @date: 2020/3/4 17:37
 */
@Configuration
public class AppConfig {

    @Value("${familytree.maxCount}")
    private String maxCount;

    @Bean
    public FilterRegistrationBean appFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new AppFilter());
        registration.addUrlPatterns("/*");
        registration.setName("appFilter");
        registration.setOrder(Integer.MAX_VALUE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("maxCount", maxCount);
        registration.setInitParameters(initParameters);
        return registration;
    }

    /**
     * 图形验证码配置类
     * @return
     */
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.image.height", "34");
        properties.put("kaptcha.image.width", "100");
        properties.put("kaptcha.textproducer.font.size", "25");// Arial cmr10
        properties.put("kaptcha.textproducer.char.string", "0123456789");
        properties.put("kaptcha.textproducer.font.names", "cmr10"); // linux 字体cmex10 cmmi10 cmr10 cmsy10 esint10 eufm10
        // msam10 msbm10 rsfs10 stmary10 wasy10
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
