package com.frame.boot.frame.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 判断是否为空工具类
 * @author duancq
 * 2013-10-27 下午6:06:35
 */
public class EmptyUtil {

	private EmptyUtil() {}

	/**
	 * 验证字符串为空
	 * @param str
	 * @return 如果字符串为null或者去掉两端空格厚为空则返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str.trim());
	}

	/**
	 * 验证字符串不为空
	 * @param str
	 * @return 如果字符串不为null或者去掉两端空格厚为不空则返回true，否则返回false
	 */
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 验证Collection为空
	 * @param Collection
	 * @return 如果Collection为null或者里面没有对象，则返回true，否则返回false
	 */
	public static boolean isEmpty(Collection<?> list) {
		return null == list || list.isEmpty();
	}

	/**
	 * 验证Collection不为空
	 * @param Collection
	 * @return 如果Collection不为null或者里面存在对象，则返回true，否则返回false
	 */
	public static boolean notEmpty(Collection<?> list) {
		return !isEmpty(list);
	}

	/**
	 * 验证数组为空
	 * @param array
	 * @return 如果数组为null或者里面不存在对象，则返回true，否则返回false
	 */
	public static boolean isEmpty(Object[] array) {
		return null == array || array.length == 0;
	}

	/**
	 * 验证数组不为空
	 * @param array
	 * @return 如果数组不为null或者里面存在对象，则返回true，否则返回false
	 */
	public static boolean notEmpty(Object[] array) {
		return !isEmpty(array);
	}

	/**
	 * 验证map为空
	 * @param map
	 * @return 如果map为null或者里面没有对象，则返回true，否则返回false
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return null == map || map.isEmpty();
	}

	/**
	 * 验证map不为空
	 * @param map
	 * @return 如果map不为null或者里面存在对象，则返回true，否则返回false
	 */
	public static boolean notEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

}
