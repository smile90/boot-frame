package com.frame.boot.frame.security.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysRoleModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleModuleMapper extends BaseMapper<SysRoleModule> {

    List<SysRoleModule> selectByModuleCode(String moduleCode);
}