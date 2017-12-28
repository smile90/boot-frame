package com.frame.boot.frame.security.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectByUsername(String username);

    SysUser selectSecurityUserByUsername(String username);
}