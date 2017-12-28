package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.mapper.SysRoleModuleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleModuleService extends ServiceImpl<SysRoleModuleMapper, SysRoleModule> {

    public List<SysRoleModule> findByModuleCode(String moduleCode) {
        return baseMapper.selectByModuleCode(moduleCode);
    }

}
