package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectSecurityByUsername(String username);

    SysRole selectByCode(String code);
}