package com.frame.boot.frame.security.filter;


import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class ContextFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String STATIC_HOST_CODE = "staticHost";

    private String staticHost;
    private String ctxPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ctxPath = filterConfig.getServletContext().getContextPath();
        if (ctxPath.endsWith("/")) {
            ctxPath = ctxPath.substring(0, ctxPath.length() - 1);
        }
        staticHost = filterConfig.getInitParameter(STATIC_HOST_CODE);
        logger.info("create ctxPath:{}, staticHost:{}", ctxPath, staticHost);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute("ctxPath", ctxPath);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
