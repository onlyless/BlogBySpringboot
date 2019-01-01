package com.djangoz.blog.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = {
//        "/admin/","/admin/update/*","/admin/write","/admin/delete/*","/admin/save","/admin/logout"
//},filterName = "authFilter",initParams = {
//        @WebInitParam(name = "LOGIN_VIEW",value = "/admin/login")
//})
public class AuthFilter implements Filter {

    private static String LOGIN_VIEW = "/admin/login";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        LOGIN_VIEW = filterConfig.getInitParameter("LOGIN_VIEW");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        if(path.startsWith("/admin/login")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else if(request.getSession().getAttribute("user")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.sendRedirect(LOGIN_VIEW);
        }
    }

    @Override
    public void destroy() {

    }
}
