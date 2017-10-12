package com.frame.boot.frame.mybatis.service;

import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.boot.frame.mybatis.bean.PageList;
import com.frame.boot.frame.mybatis.mapper.BaseMapper;

import java.util.List;

public abstract class BaseService<T> {
    public abstract BaseMapper<T> getBaseMapper();

    public int delete(Long id) {
        return getBaseMapper().delete(id);
    }

    public T create(T record) {
        getBaseMapper().insert(record);
        return record;
    }

    public T update(T record) {
        getBaseMapper().update(record);
        return record;
    }

    public T updateSelective(T record) {
        getBaseMapper().updateSelective(record);
        return record;
    }

    public T findOne(Long id) {
        return getBaseMapper().selectOne(id);
    }

    public List<T> findAll() {
        return getBaseMapper().selectAll();
    }

    public PageList<T> findAll(PageBounds pageBounds) {
        return getBaseMapper().selectAll(pageBounds);
    }
}
