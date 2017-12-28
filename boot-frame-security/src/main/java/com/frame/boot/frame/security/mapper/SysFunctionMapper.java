package com.frame.boot.frame.security.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysFunction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysFunctionMapper extends BaseMapper<SysFunction> {

    List<SysFunction> selectByModuleCode(String moduleCode);
}