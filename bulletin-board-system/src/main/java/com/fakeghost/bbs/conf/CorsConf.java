package com.fakeghost.bbs.conf;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
 
@Component
@Order(value=0)
public class CorsConf implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException { }

  @Override
  public void doFilter(ServletRequest sreq, ServletResponse servletResponse,
    FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    HttpServletRequest req= (HttpServletRequest) sreq;
    res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
    res.setHeader("Access-Control-Max-Age", "1728000");
    res.setHeader("Access-Control-Allow-Headers",
        "Authentication, Authorization, content-type, Accept, x-requested-with, Cache-Control");
    filterChain.doFilter(req, res);
  }
 
  @Override
  public void destroy() {}
  
}

