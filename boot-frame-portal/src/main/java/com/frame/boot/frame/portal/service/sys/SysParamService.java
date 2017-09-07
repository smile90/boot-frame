package com.frame.boot.frame.portal.service.sys;

import com.frame.boot.frame.portal.entity.sys.SysParam;
import com.frame.boot.frame.portal.mapper.sys.SysParamMapper;
import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.boot.frame.mybatis.bean.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public SysParam find(Long id) {
        return sysParamMapper.selectByPK(id);
    }

    public List<SysParam> findAll() {
        return sysParamMapper.selectAll();
    }

    public PageList<SysParam> findAll(PageBounds pageParam) {
        return sysParamMapper.selectAll(pageParam);
    }

}
