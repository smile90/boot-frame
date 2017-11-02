package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysRoleModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleModuleMapper extends BaseMapper<SysRoleModule> {

    public List<SysRoleModule> selectByModuleId(Long moduleId);
}