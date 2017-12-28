package com.frame.boot.frame.security.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.mapper.SysModuleMapper;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysModuleService extends ServiceImpl<SysModuleMapper, SysModule> {

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    public SysModule findByCode(String code) {
        return baseMapper.selectByCode(code);
    }

    public List<SysModule> findByParentCode(String parentCode) {
        return baseMapper.selectByParentCode(parentCode);
    }

    public List<SysModule> findMenuByUsername(String username) {
        return baseMapper.selectByUser(username, systemSecurityProperties.getMenuTypeCode(),
                new EntityWrapper<SysModule>().orderBy("level").orderBy("orders"));
    }

    public JSONArray findMenuJSONByUsername(String username) {
        List<SysModule> listMenu = findMenuByUsername(username);
        return getModulesJSON(SysConstants.MODULE_CODE_ROOT, listMenu);
    }

    public JSONArray getModulesJSON(String parentCode, Collection<SysModule> modules) {
        if (EmptyUtil.isEmpty(modules) || EmptyUtil.isEmpty(parentCode)) {
            return null;
        }
        // 拷贝一份，防止删除的时候会删除节点
        List<SysModule> list = new ArrayList<>(modules);
        // 排序
        sort(list);

        // 剩余节点，用于递归
        List<SysModule> surplusModules = new ArrayList<>(modules);
        // 遍历添加
        JSONArray childrenModulesJSON = new JSONArray();
        for (SysModule module : list) {
            // 父节点
            if (parentCode.equals(module.getParentCode())) {
                // 添加
                JSONObject moduleJSON = new JSONObject();
                moduleJSON.put("id", module.getId());
                moduleJSON.put("optimistic", module.getOptimistic());
                moduleJSON.put("parentCode", parentCode);
                moduleJSON.put("validate", module.getValidate());
                moduleJSON.put("useable", module.getUseable());
                moduleJSON.put("code", module.getCode());
                moduleJSON.put("name", module.getName());
                moduleJSON.put("typeCode", module.getTypeCode());
                moduleJSON.put("icon", module.getIcon());
                moduleJSON.put("url", module.getUrl());
                moduleJSON.put("order", module.getOrders());
                moduleJSON.put("description", module.getDescription());
                // 删除掉已经添加的节点
                surplusModules.remove(module);
                // 添加子节点
                moduleJSON.put("children", getModulesJSON(module.getCode(), surplusModules));

                boolean exit = false;
                for (int i = 0; i < childrenModulesJSON.size(); i++) {
                    JSONObject childModuleJSON = childrenModulesJSON.getJSONObject(i);
                    String childCode = (childModuleJSON == null ? null : childModuleJSON.getString("code"));
                    if (EmptyUtil.notEmpty(childCode) && childCode.equals(module.getCode())) {
                        exit = true;
                        break;
                    }
                }
                if (!exit) {
                    childrenModulesJSON.add(moduleJSON);
                }
            }
        }
        return childrenModulesJSON;
    }

    /**
     * 排序
     *
     * @param modules
     */
    public void sort(List<SysModule> modules) {
        Collections.sort(modules, new Comparator<SysModule>() {
            @Override
            public int compare(SysModule object1, SysModule object2) {
                if (null == object1 || null == object2 || null == object1.getOrders() || null == object2.getOrders()) {
                    return 0;
                } else {
                    return object1.getOrders() - object2.getOrders();
                }
            }
        });
    }
}
