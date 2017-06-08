package com.frame.boot.frame.portal.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "defaultCtxFilter", urlPatterns = "/*")
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
