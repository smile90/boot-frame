package com.frame.boot.frame.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.portal.rpc.service.SecurityService;
import com.frame.common.frame.base.bean.ResponseBean;

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
        ResponseBean<JSONObject> result = securityService.find("testUser");
        System.out.println(result);
        System.out.println(result.getResult());
        System.out.println(result.getCode());
        System.out.println(result.getDescription());
        System.out.println(result.getShowMsg());
        System.out.println(result.getObject());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
