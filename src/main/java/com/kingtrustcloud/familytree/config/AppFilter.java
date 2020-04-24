package com.kingtrustcloud.familytree.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version V1.0
 * @Title: AppFilter
 * @Package com.kingtrustcloud.familytree.config
 * @Description: (过滤器，拦截请求，可以做一些操作)
 * @author: tanyong
 * @date: 2020/3/4 17:28
 */
@Slf4j
public class AppFilter implements Filter {

    private int maxCount=0;

    private AtomicInteger count = new AtomicInteger(0);

    private final static String RETURN_HTML = "<html>" +
            "<head>" +
            "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />" +
            "\n<meta charset='utf-8'>" +
            "<title>族谱</title>" +
            "</head><body>" +
            "<center>挤爆了、请稍等...</center>" +
            "</body>" +
            "<script type='text/javascript'>(function(){function reload(){ window.location.reload(); setTimeout(reload, 1500);} setTimeout(reload, 1500);})();" +
            "</script>" +
            "</html>";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String count = filterConfig.getInitParameter("maxCount");
        if(count!=null) {
            maxCount = Integer.valueOf(count);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(maxCount>0 && count.get()>maxCount) {
            servletResponse.setContentType("text/html");
            servletResponse.setCharacterEncoding("utf-8");
            servletResponse.getWriter().write(RETURN_HTML);
        } else {
            count.incrementAndGet();
            filterChain.doFilter(servletRequest,servletResponse);
            count.decrementAndGet();
        }
    }
}
