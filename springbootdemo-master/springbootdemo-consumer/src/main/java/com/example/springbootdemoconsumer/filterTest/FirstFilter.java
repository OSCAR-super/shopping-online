package com.example.springbootdemoconsumer.filterTest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(filterName="firstFilter", urlPatterns="/*")
@Component
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("first filter xss");
        XssFilterWrapper xssFilterWrapper=new XssFilterWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(xssFilterWrapper,servletResponse);
        System.out.println("first filter xss finish");
    }

    @Override
    public void destroy() {

    }
}
