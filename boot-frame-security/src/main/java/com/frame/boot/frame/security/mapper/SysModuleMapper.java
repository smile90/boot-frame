package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysModuleMapper extends BaseMapper<SysModule> {

    SysModule selectByCode(@Param("code") String code);

    List<SysModule> selectByRole(@Param("roleId") Long roleId, @Param("typeCode") String typeCode);
    List<SysModule> selectByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode);
    List<SysModule> selectByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode, PageBounds pageBounds);

    List<SysModule> selectSecurityByRole(@Param("roleId") Long roleId, @Param("typeCode") String typeCode);
    List<SysModule> selectSecurityByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode);
}