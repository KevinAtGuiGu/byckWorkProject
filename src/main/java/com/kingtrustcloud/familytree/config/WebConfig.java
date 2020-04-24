package com.kingtrustcloud.familytree.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring MVC配置类
 * @version: V1.0
 * @author: tan
 * @date: 2019-09-25 17:14:05
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PageInterceptor pageInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageInterceptor);
    }

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources/static/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/static/")
                // 下面这句是匹配swaggerui的静态资源映射
                .addResourceLocations("classpath:/META-INF/resources/static/")
                // 解决前端请求路径问题
                .addResourceLocations("classpath:/static/static/");
    }
}
