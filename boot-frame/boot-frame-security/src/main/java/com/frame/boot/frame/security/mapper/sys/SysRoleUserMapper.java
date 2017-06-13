package com.frame.boot.frame.security.mapper.sys;

import com.frame.boot.frame.security.entity.sys.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
}