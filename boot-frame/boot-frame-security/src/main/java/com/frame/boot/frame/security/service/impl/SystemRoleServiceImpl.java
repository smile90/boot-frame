package com.frame.boot.frame.security.service.impl;

import com.frame.boot.frame.core.service.impl.BaseServiceImpl;
import com.frame.boot.frame.security.entity.SystemRole;
import com.frame.boot.frame.security.service.SystemRoleService;
import org.springframework.stereotype.Service;

@Service("systemRoleService")
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRole, Long> implements SystemRoleService {

}
