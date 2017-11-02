package com.frame.boot.frame.security.service;

import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.mybatis.service.BaseService;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("sysUserService")
public class SysUserService extends BaseService<SysUser> {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public BaseMapper<SysUser> getBaseMapper() {
        return sysUserMapper;
    }

    public SysUser create(SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        sysUserMapper.insert(sysUser);
        return sysUser;
    }

    public SysUser update(SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        sysUserMapper.update(sysUser);
        return sysUser;
    }

    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    /**
     * 查询权限用户
     *
     * @param username
     * @return
     */
    public SysUser findSecurityUserByUsername(String username) {
        return sysUserMapper.findSecurityUserByUsername(username);
    }
}
