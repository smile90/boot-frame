package com.frame.boot.frame.security.service;

import com.frame.boot.frame.mybatis.mapper.BaseMapper;
import com.frame.boot.frame.mybatis.service.BaseService;
import com.frame.boot.frame.security.entity.SysFunction;
import com.frame.boot.frame.security.mapper.SysFunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysFunctionService extends BaseService<SysFunction> {

    @Autowired
    private SysFunctionMapper sysFunctionMapper;

    @Override
    public BaseMapper<SysFunction> getBaseMapper() {
        return sysFunctionMapper;
    }
}
