package com.frame.boot.frame.core.search;

import com.frame.boot.frame.common.search.enums.DataType;
import com.frame.boot.frame.core.exception.SearchException;
import com.frame.boot.frame.utils.DateUtil;

public class SearchUtil {

	public static Object getObjectValue(String value, DataType dataType) {
		try {
			switch (dataType) {
				case DATE: return DateUtil.parseDate(value, DateUtil.FORMAT_yyyy_MM_dd_HH_mm_ss);
				case DOUBLE: return Double.valueOf(value);
				case FLOAT: return Float.valueOf(value);
				case INTEGER: return Integer.valueOf(value);
				case LONG: return Long.valueOf(value);
				case STRING: return value;
				default: throw new SearchException(SearchException.ERROR_CODE_CONVERT_SEARCH_VALUE_ERROR,
							String.format("dataType not found. value:%s,dataType:%s", value, dataType),
							SearchException.SHOW_MSG_CONVERT_SEARCH_VALUE_ERROR);
			}
		} catch (Exception e) {
			throw new SearchException(SearchException.ERROR_CODE_CONVERT_SEARCH_VALUE_ERROR,
					String.format("getObjectValue error. value:%s,dataType:%s", value, dataType),
					SearchException.SHOW_MSG_CONVERT_SEARCH_VALUE_ERROR);
		}
	}
}
