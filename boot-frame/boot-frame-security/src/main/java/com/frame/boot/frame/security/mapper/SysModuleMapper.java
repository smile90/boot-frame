package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.security.entity.SysModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysModule record);

    int insertSelective(SysModule record);

    SysModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysModule record);

    int updateByPrimaryKey(SysModule record);

    List<SysModule> selectByRole(@Param("roleId") Long roleId, @Param("typeCode") String typeCode);
    List<SysModule> selectByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode);

    List<SysModule> selectSecurityByRole(@Param("roleId") Long roleId, @Param("typeCode") String typeCode);
    List<SysModule> selectSecurityByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode);
}