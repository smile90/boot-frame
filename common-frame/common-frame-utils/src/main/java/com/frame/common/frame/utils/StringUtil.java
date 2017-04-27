package com.frame.common.frame.utils;

import java.util.UUID;

/**
 * 字符串工具类
 * 提供了String的常用操作，基于其他开源组件提供二次封装。
 * @author duancq
 * 2014-1-10 下午11:59:39
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

	private StringUtil() {}

	/**
	 * 获取UUID
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
