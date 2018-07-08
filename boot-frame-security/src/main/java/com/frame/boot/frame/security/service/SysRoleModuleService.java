package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.mapper.SysRoleModuleMapper;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleModuleService extends ServiceImpl<SysRoleModuleMapper, SysRoleModule> {

    public Integer deleteByModuleCode(String moduleCode) {
        return baseMapper.deleteByModuleCode(moduleCode);
    }
    public Integer deleteByRoleCode(String roleCode) {
        return baseMapper.deleteByRoleCode(roleCode);
    }
    public void updateRoleModules(String roleCode, List<String> moduleCodes) {
        // 删除所有关联模块
        deleteByRoleCode(roleCode);
        // 新增所有关联模块
        if (EmptyUtil.notEmpty(moduleCodes)) {
            List<SysRoleModule> sysRoleModules = new ArrayList<>();
            for (String moduleCode : moduleCodes) {
                SysRoleModule sysRoleModule = new SysRoleModule();
                sysRoleModule.setRoleCode(roleCode);
                sysRoleModule.setModuleCode(moduleCode);
                sysRoleModule.setCreateTime(new Date());
                sysRoleModule.setCreateUser(AuthUtil.getUsername());
                sysRoleModules.add(sysRoleModule);
            }
            insertBatch(sysRoleModules, 50);
        }
    }
}
