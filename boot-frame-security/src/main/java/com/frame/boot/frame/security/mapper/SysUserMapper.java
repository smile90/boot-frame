package com.frame.boot.frame.security.mapper;


import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.boot.frame.mybatis.bean.PageList;
import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectByUsername(String username);

    PageList<SysUser> selectLikeRealname(PageBounds pageBounds, @Param("realname") String realname);

    SysUser selectSecurityUserByUsername(String username);
}