package com.lefu.boot.frame.hibernate.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "contextFilter", urlPatterns = "/*")
public class ContextFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String ctxPath;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ctxPath = filterConfig.getServletContext().getContextPath();
		if (ctxPath.endsWith("/")) {
			ctxPath = ctxPath.substring(0, ctxPath.length() - 1);
		}
		logger.info("create contextPath is ctxPath:{}", ctxPath);
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
