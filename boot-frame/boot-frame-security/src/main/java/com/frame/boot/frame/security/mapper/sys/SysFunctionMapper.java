package com.frame.boot.frame.security.mapper.sys;

import com.frame.boot.frame.security.entity.sys.SysFunction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysFunctionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysFunction record);

    int insertSelective(SysFunction record);

    SysFunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysFunction record);

    int updateByPrimaryKey(SysFunction record);
}