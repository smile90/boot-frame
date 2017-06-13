package com.frame.boot.frame.security.service;

import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser create(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
        return sysUser;
    }

    /**
     * 查询用户
     * @param username
     * @return
     */
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    /**
     * 查询权限用户
     * @param username
     * @return
     */
    public SysUser findSecurityUserByUsername(String username) {
        return sysUserMapper.findSecurityUserByUsername(username);
    }
}
