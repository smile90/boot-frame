package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.security.entity.SysModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysModule record);

    int insertSelective(SysModule record);

    SysModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);

    List<SysModule> selectSecurityByRoleId(Long roleId);
}