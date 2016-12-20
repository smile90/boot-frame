package com.frame.boot.frame.common.security.service.impl;

import org.springframework.stereotype.Service;

import com.frame.common.base.service.impl.BaseServiceImpl;
import com.frame.boot.frame.common.security.entity.SystemRole;
import com.frame.boot.frame.common.security.service.SystemRoleService;

@Service("systemRoleService")
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRole, Long> implements SystemRoleService {

}
