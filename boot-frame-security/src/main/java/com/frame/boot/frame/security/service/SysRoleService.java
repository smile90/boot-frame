package com.frame.boot.frame.security.service;

import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.mybatis.service.BaseService;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.mapper.SysRoleMapper;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService extends BaseService<SysRole> {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public BaseMapper<SysRole> getBaseMapper() {
        return sysRoleMapper;
    }

    @Autowired
    private SysRoleModuleService sysRoleModuleService;
    @Autowired
    private SysModuleService sysModuleService;

    public List<SysRole> findByModuleCode(String moduleCode) {
        List<SysRole> sysRoles = new ArrayList<>();

        SysModule sysModule = sysModuleService.findByCode(moduleCode);
        if (sysModule != null) {
            List<SysRoleModule> sysRoleModules = sysRoleModuleService.findByModuleId(sysModule.getId());
            if (EmptyUtil.notEmpty(sysRoleModules)) {
                for (SysRoleModule sysRoleModule : sysRoleModules) {
                    SysRole sysRole = findOne(sysRoleModule.getRoleId());
                    if (sysRole != null) {
                        sysRoles.add(sysRole);
                    }
                }
            }
        }
        return sysRoles;
    }
}
