package com.example.demo.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */
public class MyIntercepter extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static List<String> excludes;

    private static PathMatcher pathMatcher = new AntPathMatcher();

    public MyIntercepter() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    static {
        excludes = new ArrayList<String>(){{
            add("/index");
        }};
    }
}
