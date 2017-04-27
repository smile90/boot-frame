package com.frame.common.frame.utils;

import java.math.BigDecimal;

/**
 * 数字工具类
 * 由于Java的简单类型不能够精确的对浮点数进行运算
 * 这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入。
 * @author duancq
 * 2015-1-25 下午2:47:10
 */
public class NumberUtil {

	private NumberUtil() {}
	
	/**
	 * 提供精确的加法运算
	 * @param augend	被加数
	 * @param addends	加数
	 * @return			两个参数的和
	 */
	public static double add(double augend, double... addends) {
		BigDecimal sum = BigDecimal.valueOf(augend);
		for (double addend : addends) {
			sum = sum.add(BigDecimal.valueOf(addend));
		}
		return sum.doubleValue();
	}

	/**
	 * 提供精确的减法运算
	 * @param minuend		被减数
	 * @param subtrahends	减数
	 * @return				两个参数的差
	 */
	public static double subtract(double minuend, double... subtrahends) {
		BigDecimal sum = BigDecimal.valueOf(minuend);
		for (double subtrahend : subtrahends) {
			sum = sum.subtract(BigDecimal.valueOf(subtrahend));
		}
		return sum.doubleValue();
	}

	/**
	 * 提供精确的乘法运算
	 * @param multiplicand	被乘数
	 * @param multipliers	乘数
	 * @return				两个参数的积
	 */
	public static double multiply(double multiplicand, double... multipliers) {
		BigDecimal sum = BigDecimal.valueOf(multiplicand);
		for (double multiplier : multipliers) {
			sum = sum.multiply(BigDecimal.valueOf(multiplier));
		}
		return sum.doubleValue();
	}

	/**
	 * 提供精确的乘法运算，由scale参数指定精度，以后的数字四舍五入。
	 * @param multiplicand	被乘数
	 * @param multiplier	乘数
	 * @return				两个参数的积
	 */
	public static double multiply(double multiplicand, double multiplier, int scale) {
		return round(multiply(multiplicand, multiplier), scale);
	}

	/**
	 * 提供（相对）精确的除法运算
	 * 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 * @param dividend	被除数
	 * @param divisor	除数
	 * @param scale		表示表示需要精确到小数点以后几位
	 * @return			两个参数的商
	 */
	public static double divide(double dividend, double divisor, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = BigDecimal.valueOf(dividend);
		BigDecimal b2 = BigDecimal.valueOf(divisor);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param number 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double number, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = BigDecimal.valueOf(number);
		BigDecimal one = BigDecimal.valueOf(1L);
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位的进一法处理
	 * @param number 需要进一法的数字
	 * @param scale 小数点后保留几位
	 * @return 进一法后的结果
	 */
	public static double roundUp(double number, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = BigDecimal.valueOf(number);
		BigDecimal one = BigDecimal.valueOf(1L);
		return b.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位的去尾处理
	 * @param number 需要去尾的数字
	 * @param scale 小数点后保留几位
	 * @return 去尾后的结果
	 */
	public static double roundDown(double number, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = BigDecimal.valueOf(number);
		BigDecimal one = BigDecimal.valueOf(1L);
		return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 判断第一个参数是否等于第二个参数
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static boolean eq(double n1, double n2) {
		BigDecimal b1 = BigDecimal.valueOf(n1);
		BigDecimal b2 = BigDecimal.valueOf(n2);
		return b1.compareTo(b2) == 0;
	}

	/**
	 * 判断第一个参数是否大于第二个参数
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static boolean gt(double n1, double n2) {
		BigDecimal b1 = BigDecimal.valueOf(n1);
		BigDecimal b2 = BigDecimal.valueOf(n2);
		return b1.compareTo(b2) > 0;
	}

	/**
	 * 判断第一个参数是否大于等于第二个参数
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static boolean ge(double n1, double n2) {
		BigDecimal b1 = BigDecimal.valueOf(n1);
		BigDecimal b2 = BigDecimal.valueOf(n2);
		return b1.compareTo(b2) >= 0;
	}

	/**
	 * 判断第一个参数是否小于第二个参数
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static boolean lt(double n1, double n2) {
		BigDecimal b1 = BigDecimal.valueOf(n1);
		BigDecimal b2 = BigDecimal.valueOf(n2);
		return b1.compareTo(b2) < 0;
	}

	/**
	 * 判断第一个参数是否小于等于第二个参数
	 * @param n1
	 * @param n2
	 * @return
	 */
	public static boolean le(double n1, double n2) {
		BigDecimal b1 = BigDecimal.valueOf(n1);
		BigDecimal b2 = BigDecimal.valueOf(n2);
		return b1.compareTo(b2) <= 0;
	}
	
}
