package com.frame.boot.frame.security.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.mybatis.bean.Order;
import com.frame.boot.frame.mybatis.bean.PageBounds;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.mapper.SysModuleMapper;
import com.frame.boot.frame.security.properties.FrameSecurityProperties;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysModuleService {

    @Autowired
    private FrameSecurityProperties frameSecurityProperties;
    @Autowired
    private SysModuleMapper sysModuleMapper;

    public List<SysModule> findMenuByUserId(Long userId) {
        return sysModuleMapper.selectByUser(userId, frameSecurityProperties.getMenuTypeCode(), new PageBounds(Order.asc("level"), Order.asc("orders")));
    }

    public JSONArray findMenuJSONByUserId(Long userId) {
        Map<SysModule, List<SysModule>> mapMenus = new HashMap<>();
        List<SysModule> listMenu = findMenuByUserId(userId);
        if (EmptyUtil.notEmpty(listMenu)) {
            Map<SysModule, List<SysModule>> menus;
            List<SysModule> children;
            for (SysModule sysModule : listMenu) {
                if (EmptyUtil.isEmpty(sysModule.getParentCode())) {
                    menus = new HashMap<>();
                    menus.put(sysModule, new ArrayList<SysModule>());
                } else {
                    children = mapMenus.get(sysModule);
                    children.add(sysModule);
                }
            }
        }
        return JSON.parseArray(JSON.toJSONString(mapMenus.values()));
    }

}
