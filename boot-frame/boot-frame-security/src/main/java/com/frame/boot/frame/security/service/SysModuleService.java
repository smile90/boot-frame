package com.frame.boot.frame.security.service;

import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.mapper.SysModuleMapper;
import com.frame.boot.frame.security.properties.FrameSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysModuleService {

    @Autowired
    private FrameSecurityProperties frameSecurityProperties;
    @Autowired
    private SysModuleMapper sysModuleMapper;

    public List<SysModule> findMenuByUserId(Long userId) {
        return sysModuleMapper.selectByUser(userId, frameSecurityProperties.getMenuTypeCode());
    }
}
