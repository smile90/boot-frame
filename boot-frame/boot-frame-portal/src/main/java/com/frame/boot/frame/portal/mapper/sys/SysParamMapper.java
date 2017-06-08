package com.frame.boot.frame.portal.mapper.sys;

import com.frame.boot.frame.portal.entity.sys.SysParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysParamMapper {

    int deleteByPK(Integer id);

    int insert(SysParam record);

    int insertSelective(SysParam record);

    SysParam selectByPK(Integer id);

    List<SysParam> selectAll();

    int updateByPKSelective(SysParam record);

    int updateByPK(SysParam record);
}
