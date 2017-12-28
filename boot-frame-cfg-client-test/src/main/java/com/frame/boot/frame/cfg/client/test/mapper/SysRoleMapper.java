package com.frame.boot.frame.cfg.client.test.mapper;

import com.frame.boot.frame.cfg.client.test.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    List<SysRole> selectAll();
}