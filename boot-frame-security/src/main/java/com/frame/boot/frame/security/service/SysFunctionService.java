package com.frame.boot.frame.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.entity.SysFunction;
import com.frame.boot.frame.security.mapper.SysFunctionMapper;
import com.frame.common.frame.base.enums.DataStatus;
import com.frame.common.frame.base.enums.YesNo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysFunctionService extends ServiceImpl<SysFunctionMapper, SysFunction> {

    public List<SysFunction> findEnableListAll() {
        return selectList(new EntityWrapper<SysFunction>().eq("status", DataStatus.NORMAL.name())
                .eq("useable", YesNo.Y.name()));
    }
}
