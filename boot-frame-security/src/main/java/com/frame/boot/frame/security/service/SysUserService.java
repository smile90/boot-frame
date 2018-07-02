package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysRoleUser;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.mapper.SysUserMapper;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("sysUserService")
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    public SysUser findByUsername(String username) {
        return selectOne(new EntityWrapper<SysUser>().eq("username", username));
    }

    /**
     * 查询权限用户
     *
     * @param username
     * @return
     */
    public SysUser findSecurityUserByUsername(String username) {
        SysUser sysUser = selectOne(new EntityWrapper<SysUser>().eq("username", username));
        if (sysUser == null) {
            return null;
        }

        // 查询用户对应角色
        List<SysRoleUser> sysRoleUsers = sysRoleUserService.selectList(new EntityWrapper<SysRoleUser>().eq("username", username));
        if (EmptyUtil.notEmpty(sysRoleUsers)) {
            Set<String> roleCodes = new HashSet<>();
            for (SysRoleUser sysRoleUser : sysRoleUsers) {
                roleCodes.add(sysRoleUser.getRoleCode());
            }

            List<SysRole> sysRoles = sysRoleService.selectList(new EntityWrapper<SysRole>().in("code", roleCodes));
            sysUser.setRoles(sysRoles);
        }
        return sysUser;
    }
}
