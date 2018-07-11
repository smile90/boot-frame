package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysRoleUser;
import com.frame.boot.frame.security.mapper.SysRoleUserMapper;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleUserService extends ServiceImpl<SysRoleUserMapper, SysRoleUser> {

    public Integer deleteByRoleCode(String roleCode) {
        return baseMapper.deleteByRoleCode(roleCode);
    }
    public Integer deleteByUsername(String Username) {
        return baseMapper.deleteByUsername(Username);
    }

    public void updateRoleUsers(String username, List<String> roleCodes) {
        // 删除所有关联模块
        deleteByUsername(username);
        // 新增所有关联模块
        if (EmptyUtil.notEmpty(roleCodes)) {
            List<SysRoleUser> sysRoleUsers = new ArrayList<>();
            for (String roleCode : roleCodes) {
                SysRoleUser sysRoleUser = new SysRoleUser();
                sysRoleUser.setRoleCode(roleCode);
                sysRoleUser.setUsername(username);
                sysRoleUser.setCreateTime(new Date());
                sysRoleUser.setCreateUser(AuthUtil.getUsername());
                sysRoleUsers.add(sysRoleUser);
            }
            insertBatch(sysRoleUsers, 50);
        }
    }
}
