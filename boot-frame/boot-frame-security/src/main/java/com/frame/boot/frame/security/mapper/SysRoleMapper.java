package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.security.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}