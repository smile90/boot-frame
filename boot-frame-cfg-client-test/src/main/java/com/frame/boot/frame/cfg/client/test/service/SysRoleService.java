package com.frame.boot.frame.cfg.client.test.service;

import com.frame.boot.frame.cfg.client.test.entity.SysRole;
import com.frame.boot.frame.cfg.client.test.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    public List<SysRole> findAll() {
        return sysRoleMapper.selectAll();
    }
}
