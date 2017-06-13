package com.frame.boot.frame.security.mapper.sys;

import com.frame.boot.frame.security.entity.sys.SysModule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysModule record);

    int insertSelective(SysModule record);

    SysModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);
}