package com.frame.security.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.frame.common.base.search.Criteria;
import com.frame.common.base.search.enums.SearchType;
import com.frame.common.base.service.impl.BaseServiceImpl;
import com.frame.common.utils.EmptyUtil;
import com.frame.common.utils.json.SimpleKeyMapProcessFilter;
import com.frame.security.entity.SystemAuthority;
import com.frame.security.service.SystemAuthorityService;

@Service("systemAuthorityService")
public class SystemAuthorityServiceImpl extends BaseServiceImpl<SystemAuthority, Long> implements SystemAuthorityService {

	private SerializeFilter mapKeys = new SimpleKeyMapProcessFilter(new String[] {"id", "code", "parentCode"}, new String[] {"tid", "id", "pid"});
	private SerializeFilter showKeys = new SimplePropertyPreFilter("id", "code", "name", "icon", "menu", "display", "validate", "enable", "parentCode", "children");

	@Override
	public JSONArray findArrayJsonByParentCode(String parentCode) {
		List<SystemAuthority> list = findAll(new Criteria<SystemAuthority>("parentCode", SearchType.EQ, parentCode));
		if (EmptyUtil.isEmpty(list)) {
			return null;
		} else {
			return JSONArray.parseArray(JSON.toJSONString(list, new SerializeFilter[] {showKeys, mapKeys}));
		}
	}
}
