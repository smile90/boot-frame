package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.mapper.SysUserMapper;
import com.frame.common.frame.base.enums.DataStatus;
import com.frame.common.frame.base.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysUserService")
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysRoleModuleService sysRoleModuleService;
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
        sysUser.setRoles(sysRoleService.findByUsername(username));
        return sysUser;
    }

    @Transactional
    public void delete(SysUser sysUser) {
        if (sysUser != null) {
            // 删除关联关系
            sysRoleUserService.deleteByUsername(sysUser.getUsername());
            // 逻辑删除
            sysUser.setUserStatus(UserStatus.DELETED.name());
            sysUser.setStatus(DataStatus.DELETE.name());
            updateById(sysUser);
        }
    }
}
