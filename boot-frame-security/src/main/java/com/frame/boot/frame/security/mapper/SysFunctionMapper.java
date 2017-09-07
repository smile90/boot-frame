package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.security.entity.SysFunction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysFunctionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysFunction record);

    int insertSelective(SysFunction record);

    SysFunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysFunction record);

    int updateByPrimaryKey(SysFunction record);

    List<SysFunction> selectByModuleCode(String moduleCode);
}