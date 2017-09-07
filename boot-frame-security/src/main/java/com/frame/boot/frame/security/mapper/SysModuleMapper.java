package com.frame.boot.frame.security.mapper;

import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.boot.frame.security.entity.SysModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysModuleMapper {
    int delete(Long id);

    int insert(SysModule record);

    SysModule selectByPrimaryKey(Long id);

    int updateSelective(SysModule record);

    int update(SysModule record);

    List<SysModule> selectByRole(@Param("roleId") Long roleId, @Param("typeCode") String typeCode);
    List<SysModule> selectByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode);
    List<SysModule> selectByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode, PageBounds pageBounds);

    List<SysModule> selectSecurityByRole(@Param("roleId") Long roleId, @Param("typeCode") String typeCode);
    List<SysModule> selectSecurityByUser(@Param("userId") Long userId, @Param("typeCode") String typeCode);
}