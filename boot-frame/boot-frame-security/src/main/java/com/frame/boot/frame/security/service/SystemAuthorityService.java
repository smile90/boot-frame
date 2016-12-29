package com.frame.boot.frame.security.service;

import com.alibaba.fastjson.JSONArray;
import com.frame.boot.frame.core.service.BaseService;
import com.frame.boot.frame.security.entity.SystemAuthority;

public interface SystemAuthorityService extends BaseService<SystemAuthority, Long> {

	public JSONArray findArrayJsonByParentCode(String parentCode);
}
