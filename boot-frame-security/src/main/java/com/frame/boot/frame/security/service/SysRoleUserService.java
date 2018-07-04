package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRoleUser;
import com.frame.boot.frame.security.mapper.SysRoleUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleUserService extends ServiceImpl<SysRoleUserMapper, SysRoleUser> {

    public Integer deleteByRoleCode(String roleCode) {
        return baseMapper.deleteByRoleCode(roleCode);
    }
}
