package com.frame.boot.frame.security.service;

import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.mybatis.service.BaseService;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.mapper.SysRoleModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleModuleService extends BaseService<SysRoleModule> {

    @Autowired
    private SysRoleModuleMapper sysRoleModuleMapper;

    @Override
    public BaseMapper<SysRoleModule> getBaseMapper() {
        return sysRoleModuleMapper;
    }

    public List<SysRoleModule> findByModuleCode(String moduleCode) {
        return sysRoleModuleMapper.selectByModuleCode(moduleCode);
    }

}
