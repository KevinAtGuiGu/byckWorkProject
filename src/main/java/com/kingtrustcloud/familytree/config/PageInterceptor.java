package com.kingtrustcloud.familytree.config;

import com.kingtrustcloud.familytree.common.SessionManage;
import com.kingtrustcloud.familytree.common.SessionUserInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 页面拦截器
 *
 */
@Component
@Slf4j
@EnableConfigurationProperties(PageInterceptor.IgnoreUrlConfigurationProperties.class)
public class PageInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IgnoreUrlConfigurationProperties ignoreUrlConfigurationProperties;

    @Value("${familytree.loginUrl}")
    private String loginUrl;

    @Autowired
    private SessionManage sessionManage;

    /**
     * 前置检查
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return super.preHandle(request, response, handler);
        }
        String pathInfo = request.getServletPath();
        // 判断是否已登陆，未登陆跳转到登陆页登陆
        if(handler instanceof HandlerMethod) {
            SessionUserInfo sessionUserInfo = sessionManage.getSessionUserInfo();
            if(sessionUserInfo!=null && sessionUserInfo.getMemberBasicInfoEntity()!=null) {
                return true;
            }
            if(validUrl(pathInfo)) {
                return true;
            } else {
                //判断如果是ajax，则需要设置response参数，告诉ajax这是重定向
                //这里设置完之后，需要到common.js中设置jquery-ajax默认设置，详细查看$.ajaxSetup方法
                String type = request.getHeader("X-Requested-With")==null?"":request.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equals(type)) {
                    response.setHeader("REDIRECT", "REDIRECT");//告诉ajax这是重定向
                    response.setHeader("CONTEXTPATH", loginUrl);//重定向地址
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                } else {
                    response.sendRedirect(loginUrl);
                }
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 根据配置文件配置的url规则进行校验
     * @param path
     * @return
     */
    private Boolean validUrl(String path) {
        for(String url:ignoreUrlConfigurationProperties.getIgnoreUrls()) {
            if(path.contains(url)) {
                return true;
            }
        }
        for (String url:ignoreUrlConfigurationProperties.getWxUrls()) {
            if(path.contains(url)) {
                // 微信调用接口
                return true;
            }
        }
        return false;
    }


    @Data
    @ConfigurationProperties(prefix = "familytree")
    public static class IgnoreUrlConfigurationProperties {

        /**
         * 微信用户调用接口 url
         */
        private List<String> wxUrls = new ArrayList<>();

        /**
         * 忽略验证 url
         */
        private List<String> ignoreUrls = new ArrayList<>();
    }

}