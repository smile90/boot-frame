package com.frame.security.service;

import com.alibaba.fastjson.JSONArray;
import com.frame.common.base.service.BaseService;
import com.frame.security.entity.SystemAuthority;

public interface SystemAuthorityService extends BaseService<SystemAuthority, Long> {

	public JSONArray findArrayJsonByParentCode(String parentCode);
}
