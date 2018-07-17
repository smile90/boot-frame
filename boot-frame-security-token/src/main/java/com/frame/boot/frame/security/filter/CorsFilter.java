package com.frame.boot.frame.security.filter;

import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CorsFilter extends GenericFilterBean {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "*");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", systemSecurityProperties.getJwt().getRequestKey() + ",cache-control,content-type,hash-referer,x-requested-with");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", systemSecurityProperties.getCors().getAccessControlAllowOrigin());
        ((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
        filterChain.doFilter(request, response);
    }
}
