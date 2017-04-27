package com.frame.common.frame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author duancq
 * 2013-10-27 下午4:31:51
 */
public class DateUtil {

	public static final String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String FORMAT_yyyy_MM = "yyyy-MM";
	public static final String FORMAT_yyyyMMdd = "yyyyMMdd";
	public static final String FORMAT_yyyyMM = "yyyyMM";
	public static final String FORMAT_HH_mm_ss = "HH:mm:ss";
	public static final String FORMAT_HHmmss = "HHmmss";
	public static final String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String FORMAT_yyyy_MM_dd_HH_mm_ss_S = "yyyy-MM-dd HH:mm:ss.S";

	private DateUtil() {}

	/**
	 * 格式化时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 转换时间
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(date);
	}

	/**
	 * 添加指定毫秒
	 * @param date
	 * @param milliSeconds
	 * @return
	 */
	public static Date addMilliSecondToDate(Date date, int milliSeconds) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MILLISECOND, milliSeconds);
		return ca.getTime();
	}

	/**
	 * 添加指定分钟
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinuteToDate(Date date, int minutes) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MINUTE, minutes);
		return ca.getTime();
	}

	/**
	 * 添加指定天
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDayToDate(Date date, int days){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_YEAR, days);
		return ca.getTime();
	}

	/**
	 * 添加指定月
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonthToDate(Date date, int months){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, months);
		return ca.getTime();
	}

	/**
	 * 添加指定年
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYearToDate(Date date, int years){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.YEAR, years);
		return ca.getTime();
	}

	/**
	 * 获取当前最小时间
	 * 例如：2016-04-01 12:00:00则为：2016-04-01 00:00:00
	 * @param date
	 * @return
	 */
	public static Date getCurrentMinDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当前最大时间
	 * 例如：2016-04-01 12:00:00则为：2016-04-01 23:59:59.999
	 * @param date
	 * @return
	 */
	public static Date getCurrentMaxDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取多个时间中的最小时间
	 * @param dates
	 * @return
	 */
	public static Date getMinDate(Date... dates) {
		if (dates == null || EmptyUtil.isEmpty(dates)) {
			return null;
		}
		Date minDate = null;
		for (Date date : dates) {
			minDate = (minDate == null ? date : (minDate.before(date) ? minDate : date));
		}
		return minDate;
	}

	/**
	 * 获取多个时间中的最大时间
	 * @param dates
	 * @return
	 */
	public static Date getMaxDate(Date... dates) {
		if (dates == null || EmptyUtil.isEmpty(dates)) {
			return null;
		}
		Date maxDate = null;
		for (Date date : dates) {
			maxDate = (maxDate == null ? date : (maxDate.after(date) ? maxDate : date));
		}
		return maxDate;
	}
}
