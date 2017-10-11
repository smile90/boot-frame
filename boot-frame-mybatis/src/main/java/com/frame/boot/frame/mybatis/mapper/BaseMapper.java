package com.frame.boot.frame.mybatis.mapper;

import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.boot.frame.mybatis.bean.PageList;

import java.util.List;

public interface BaseMapper<T> {
    int delete(Long id);
    int insert(T record);
    int update(T record);
    int updateSelective(T record);
    T selectOne(Long id);
    List<T> selectAll();
    PageList<T> selectAll(PageBounds pageParam);
}