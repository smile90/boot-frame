package com.frame..boot.frame.hibernate.utils;

import com.frame..common.frame.utils.EmptyUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


/**
 * 请求工具类
 *
 * @author duancq 2014-1-26 上午10:13:43
 */
public class RequestUtil {

	private RequestUtil() {}

	/**
	 * 获取请求的路径（不包含IP:端口与应用名称，例如：/admin/login.action）
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		// 去掉requestURI中的ContextPath部分
		return request.getRequestURI().substring(getContextPath(request).length());
	}

	/**
	 * 获取请求的头（仅仅包含IP:端口与应用名称，例如：http://127.0.0.1:9080/fw）
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestHead(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + getContextPath(request);
	}

	/**
	 * 获取应用名称（仅仅包含应用名称:例如：/fw）
	 *
	 * @param request
	 * @return
	 */
	public static String getContextPath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		// 返回
		return contextPath;
	}

	/**
	 * 获取应用真实路径
	 *
	 * @param context
	 * @return
	 */
	public static String getContextRealPath(ServletContext context) {
		String realPath = context.getRealPath("/");
		if (realPath.endsWith("/")) {
			realPath = realPath.substring(0, realPath.length() - 1);
		}
		// 返回
		return realPath;
	}

	/**
	 * 获取访问者IP
	 *
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 *
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 *
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!EmptyUtil.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!EmptyUtil.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 获得请求的session id
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestedSessionId(HttpServletRequest request) {
		return request.getRequestedSessionId();
	}
}
