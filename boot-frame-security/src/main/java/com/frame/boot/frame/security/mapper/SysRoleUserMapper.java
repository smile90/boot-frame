package com.frame.boot.frame.security.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysRoleUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

    @Delete("delete from sys_role_user where role_code = #{roleCode}")
    Integer deleteByRoleCode(String roleCode);
    @Delete("delete from sys_role_user where username = #{username}")
    Integer deleteByUsername(String username);
}