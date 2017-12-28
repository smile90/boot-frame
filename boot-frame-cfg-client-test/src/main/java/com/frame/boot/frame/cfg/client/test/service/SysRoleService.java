package com.frame.boot.frame.cfg.client.test.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.cfg.client.test.entity.SysRole;
import com.frame.boot.frame.cfg.client.test.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    public List<SysRole> findList() {
        return sysRoleMapper.selectList(null);
    }

    public List<SysRole> findAll() {
        return sysRoleMapper.selectAll();
    }
}
