package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.mapper.SysRoleMapper;
import com.frame.common.frame.base.enums.DataStatus;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRoleModuleService sysRoleModuleService;

    public List<SysRole> findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    public List<SysRole> findByModuleCode(Collection<String> moduleCodes) {
        if (EmptyUtil.notEmpty(moduleCodes)) {
            List<SysRoleModule> sysRoleModules = sysRoleModuleService.selectList(new EntityWrapper<SysRoleModule>().in("module_code", moduleCodes));
            if (EmptyUtil.notEmpty(sysRoleModules)) {
                Set<String> roleCodes = new HashSet<>();
                for (SysRoleModule SysRoleModule : sysRoleModules) {
                    roleCodes.add(SysRoleModule.getRoleCode());
                }
                return selectList(new EntityWrapper<SysRole>().in("code", roleCodes).eq("status", DataStatus.NORMAL.name()));
            }
        }
        return null;
    }
}
