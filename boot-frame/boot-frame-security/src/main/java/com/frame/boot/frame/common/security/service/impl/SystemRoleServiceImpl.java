package com.frame.security.service.impl;

import org.springframework.stereotype.Service;

import com.frame.common.base.service.impl.BaseServiceImpl;
import com.frame.security.entity.SystemRole;
import com.frame.security.service.SystemRoleService;

@Service("systemRoleService")
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRole, Long> implements SystemRoleService {

}
