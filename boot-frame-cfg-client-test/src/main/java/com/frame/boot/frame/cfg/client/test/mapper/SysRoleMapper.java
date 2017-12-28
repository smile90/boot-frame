package com.frame.boot.frame.cfg.client.test.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frame.boot.frame.cfg.client.test.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select * from sys_role")
    List<SysRole> selectAll();
}