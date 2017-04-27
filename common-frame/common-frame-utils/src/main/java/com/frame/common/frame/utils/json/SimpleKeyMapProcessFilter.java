package com.frame.common.frame.utils.json;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.serializer.NameFilter;

/**
 * key映射处理过滤器
 * @author duancq
 * 2016年7月27日 下午7:10:51
 */
public class SimpleKeyMapProcessFilter implements NameFilter {

	private List<String> oldKeys;
	private List<String> newKeys;

	public SimpleKeyMapProcessFilter(String[] oldKeys, String[] newKeys) {
		this.oldKeys = Arrays.asList(oldKeys);
		this.newKeys = Arrays.asList(newKeys);
	}

	@Override
	public String process(Object object, String name, Object value) {
		int index = oldKeys.indexOf(name);
		if (index >= 0) {
			return newKeys.get(oldKeys.indexOf(name));
		} else {
			return name;
		}
	}

}
