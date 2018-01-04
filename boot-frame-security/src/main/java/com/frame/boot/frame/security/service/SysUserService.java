package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("sysUserService")
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findSecurityUserByUsername(username);
    }

    public SysUser create(SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        baseMapper.insert(sysUser);
        return sysUser;
    }

    public SysUser update(SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        baseMapper.updateById(sysUser);
        return sysUser;
    }

    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    public SysUser findByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    /**
     * 查询权限用户
     *
     * @param username
     * @return
     */
    public SysUser findSecurityUserByUsername(String username) {
        return baseMapper.selectSecurityUserByUsername(username);
    }

}
