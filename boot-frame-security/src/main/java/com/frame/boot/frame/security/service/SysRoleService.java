package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.entity.SysRoleUser;
import com.frame.boot.frame.security.mapper.SysRoleMapper;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.base.enums.DataStatus;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRoleModuleService sysRoleModuleService;
    @Autowired
    private SysRoleUserService sysRoleUserService;

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

    @Transactional
    public void update(SysRole role, SysRole dbRole) {
        if (role != null && dbRole != null) {
            String username = AuthUtil.getUsername();
            Date time = new Date();

            // 更新关联关系
            if (!role.getCode().equals(dbRole.getCode())) {
                List<SysRoleModule> sysRoleModules = sysRoleModuleService.selectList(new EntityWrapper<SysRoleModule>().eq("role_code", dbRole.getCode()));
                if (EmptyUtil.notEmpty(sysRoleModules)) {
                    for (SysRoleModule sysRoleModule : sysRoleModules) {
                        sysRoleModule.setRoleCode(role.getCode());
                        sysRoleModule.setUpdateUser(dbRole.getUpdateUser());
                        sysRoleModule.setUpdateTime(dbRole.getUpdateTime());
                    }
                    sysRoleModuleService.updateAllColumnBatchById(sysRoleModules);
                }
                List<SysRoleUser> sysRoleUsers = sysRoleUserService.selectList(new EntityWrapper<SysRoleUser>().eq("role_code", dbRole.getCode()));
                if (EmptyUtil.notEmpty(sysRoleUsers)) {
                    for (SysRoleUser sysRoleUser : sysRoleUsers) {
                        sysRoleUser.setRoleCode(role.getCode());
                        sysRoleUser.setUpdateUser(dbRole.getUpdateUser());
                        sysRoleUser.setUpdateTime(dbRole.getUpdateTime());
                    }
                    sysRoleUserService.updateAllColumnBatchById(sysRoleUsers);
                }
            }

            // 更新角色
            dbRole.setTypeCode(role.getTypeCode());
            dbRole.setCode(role.getCode());
            dbRole.setName(role.getName());
            dbRole.setStatus(role.getStatus());
            dbRole.setOrders(role.getOrders());
            dbRole.setDescription(role.getDescription());
            dbRole.setUpdateUser(username);
            dbRole.setUpdateTime(time);
            updateById(dbRole);
        }
    }

    @Transactional
    public void delete(SysRole sysRole) {
        if (sysRole != null) {
            // 删除关联关系
            sysRoleUserService.deleteByRoleCode(sysRole.getCode());
            sysRoleModuleService.deleteByRoleCode(sysRole.getCode());
            // 删除自身
            deleteById(sysRole.getId());
        }
    }
}
