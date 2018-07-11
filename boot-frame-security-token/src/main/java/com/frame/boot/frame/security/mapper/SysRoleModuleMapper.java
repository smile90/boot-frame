package com.frame.boot.frame.security.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysRoleModule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleModuleMapper extends BaseMapper<SysRoleModule> {

    @Delete("delete from sys_role_module where module_code = #{moduleCode}")
    Integer deleteByModuleCode(String moduleCode);
    @Delete("delete from sys_role_module where role_code = #{roleCode}")
    Integer deleteByRoleCode(String roleCode);

}