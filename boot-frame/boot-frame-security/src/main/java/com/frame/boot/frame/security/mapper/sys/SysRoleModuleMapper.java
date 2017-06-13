package com.frame.boot.frame.security.mapper.sys;

import com.frame.boot.frame.security.entity.sys.SysRoleModule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleModule record);

    int insertSelective(SysRoleModule record);

    SysRoleModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleModule record);

    int updateByPrimaryKey(SysRoleModule record);
}