package com.frame.common.frame.utils;

import java.net.InetAddress;

/**
 * 服务器信息
 * @author duancq
 * 2015年4月4日下午1:42:58
 */
public class ServerIdentityUtil {

	private ServerIdentityUtil() {}
	
	/*
	 * 获取本地IP
	 */
	public static String getLocalHostIP() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostAddress();
		} catch (Exception e) {
			return "unknown";
		}
	}

	/*
	 * 获取本地主机名称
	 */
	public static String getLocalHostName() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostName();
		} catch (Exception e) {
			return "unknown";
		}
	}

	/*
	 * 获取主机所有IP
	 */
	public static String[] getAllHostIPs(String hostName) {
		String[] ips = null;
		try {
			InetAddress[] addrs = InetAddress.getAllByName(hostName);
			if (null != addrs) {
				ips = new String[addrs.length];
				for (int i = 0; i < addrs.length; i++) {
					ips[i] = addrs[i].getHostAddress();
				}
			}
		} catch (Exception e) {
			ips = null;
		}
		return ips;
	}

}