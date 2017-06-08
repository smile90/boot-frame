package com.frame.boot.frame.portal.service.sys;

import com.frame.boot.frame.portal.entity.sys.SysParam;
import com.frame.boot.frame.portal.mapper.sys.SysParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysParamService {

    @Autowired
    private SysParamMapper sysParamMapper;

    public SysParam create(SysParam sysParam) {
        sysParamMapper.insert(sysParam);
        return sysParam;
    }

    public SysParam update(SysParam sysParam) {
        sysParamMapper.updateByPK(sysParam);
        return sysParam;
    }

    public SysParam find(Integer id) {
        return sysParamMapper.selectByPK(id);
    }

}