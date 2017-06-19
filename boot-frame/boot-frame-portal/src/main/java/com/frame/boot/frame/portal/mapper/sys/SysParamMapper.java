package com.frame.boot.frame.portal.mapper.sys;

import com.frame.boot.frame.portal.entity.sys.SysParam;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysParamMapper {

    int deleteByPK(Long id);

    int insert(SysParam record);

    int insertSelective(SysParam record);

    SysParam selectByPK(Long id);

    List<SysParam> selectAll();

    PageList<SysParam> selectAll(PageBounds pageParam);

    int updateByPKSelective(SysParam record);

    int updateByPK(SysParam record);
}
