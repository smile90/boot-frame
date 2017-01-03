package com.frame.boot.frame.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.portal.constants.RemoteServiceConstant;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private RestTemplate restTemplate;

    public SecurityFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(restTemplate.getForEntity(RemoteServiceConstant.SECURITY_SERVICE_FIND_USER + "/testUser", JSONObject.class));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
