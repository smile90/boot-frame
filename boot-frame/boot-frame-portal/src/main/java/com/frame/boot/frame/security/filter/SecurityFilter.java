package com.frame.boot.frame.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.portal.rpc.service.SecurityService;

import javax.servlet.*;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        JSONObject result = securityService.find("testUser");
        System.out.println(result);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
