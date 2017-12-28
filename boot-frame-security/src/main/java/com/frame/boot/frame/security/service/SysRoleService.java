package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.mapper.SysRoleMapper;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRoleModuleService sysRoleModuleService;
    @Autowired
    private SysModuleService sysModuleService;

    public List<SysRole> findByModuleCode(String moduleCode) {
        List<SysRole> sysRoles = new ArrayList<>();

        List<SysRoleModule> sysRoleModules = sysRoleModuleService.findByModuleCode(moduleCode);
        if (EmptyUtil.notEmpty(sysRoleModules)) {
            for (SysRoleModule sysRoleModule : sysRoleModules) {
                SysRole sysRole = findByCode(sysRoleModule.getRoleCode());
                if (sysRole != null) {
                    sysRoles.add(sysRole);
                }
            }
        }
        return sysRoles;
    }

    public SysRole findByCode(String code) {
        return baseMapper.selectByCode(code);
    }
}
