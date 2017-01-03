package com.frame.boot.frame.security.service;

import com.frame.boot.frame.core.service.BaseService;
import com.frame.boot.frame.security.entity.SystemSecurityUser;

public interface SystemSecurityUserService extends BaseService<SystemSecurityUser, String> {

    SystemSecurityUser find(String username);
}
