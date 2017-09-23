package com.frame.boot.frame.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("securityMetadataSource")
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        logger.info("{}", object);
        logger.info("object的类型为:{}", object.getClass());
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String paramUrl = filterInvocation.getRequestUrl();
        String url = filterInvocation.getRequest().getServletPath();
        logger.info("访问的URL地址为:{},{}", paramUrl, url);
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        logger.info("{}", "----getAllConfigAttributes()----");
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
