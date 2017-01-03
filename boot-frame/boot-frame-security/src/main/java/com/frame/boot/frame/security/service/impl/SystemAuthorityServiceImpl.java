package com.frame.boot.frame.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.frame.boot.frame.core.search.Criteria;
import com.frame.boot.frame.core.service.impl.BaseServiceImpl;
import com.frame.boot.frame.security.entity.SystemAuthority;
import com.frame.boot.frame.security.service.SystemAuthorityService;
import com.frame.boot.frame.utils.EmptyUtil;
import com.frame.boot.frame.utils.json.SimpleKeyMapProcessFilter;
import com.frame.common.frame.base.enums.SearchType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemAuthorityService")
public class SystemAuthorityServiceImpl extends BaseServiceImpl<SystemAuthority, String> implements SystemAuthorityService {

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
