package com.frame.boot.frame.security.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frame.boot.frame.mybatis.search.bean.SearchBuilder;
import com.frame.boot.frame.mybatis.search.bean.SearchData;
import com.frame.boot.frame.mybatis.search.bean.SearchType;
import com.frame.boot.frame.mybatis.search.bean.ValueType;
import com.frame.boot.frame.security.constants.ModuleConstants;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.boot.frame.security.utils.AuthUtil;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/sys/module")
public class SysModuleController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysModuleService sysModuleService;

    @GetMapping("/ROOT")
    public Object list() {
        return ResponseBean.successContent(sysModuleService.selectList(new EntityWrapper<SysModule>().eq("code", SysConstants.MODULE_CODE_ROOT)));
    }

    @GetMapping("/list/{parentCode}")
    public Object list(@PathVariable("parentCode") String parentCode, @RequestParam Map<String,String> map) {
        SearchBuilder<SysModule> builder = new SearchBuilder<SysModule>();
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("name"))) {
            builder.build(new SearchData("name", SearchType.LIKE, ValueType.STRING, map.get("name")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("code"))) {
            builder.build(new SearchData("code", SearchType.EQ, ValueType.STRING, map.get("code")));
        }
        return ResponseBean.successContent(sysModuleService.selectList(builder.build().eq("parent_code", parentCode)));
    }

    @GetMapping("/list")
    public Object list(@RequestParam Map<String,String> map) {
        SearchBuilder<SysModule> builder = new SearchBuilder<SysModule>();
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("name"))) {
            builder.build(new SearchData("name", SearchType.LIKE, ValueType.STRING, map.get("name")));
        }
        if (EmptyUtil.notEmpty(map) && EmptyUtil.notEmpty(map.get("code"))) {
            builder.build(new SearchData("code", SearchType.EQ, ValueType.STRING, map.get("code")));
        }
        return ResponseBean.successContent(sysModuleService.selectList(builder.build()));
    }

    @GetMapping("/get/{code}")
    public Object one(@PathVariable("code") String code) {
        return ResponseBean.successContent(sysModuleService.selectOne(new EntityWrapper<SysModule>().eq("code", code)));
    }

    @GetMapping("/exist/{code}/{selfCode}")
    public Object exist(@PathVariable("code") String code, @PathVariable("selfCode") String selfCode) {
        if (!sysModuleService.exist(code, selfCode)) {
            return ResponseBean.success();
        } else {
            return ResponseBean.getInstance(ModuleConstants.MODULE_EXIST_ERROR_CODE, "code is exist:" + code, SysConstants.CODE_EXIST_ERROR_SHOW_MSG);
        }
    }

    @PostMapping("/save")
    public Object save(SysModule module) {
        try {
            SysModule parent = sysModuleService.selectOne(new EntityWrapper<SysModule>().eq("code", module.getParentCode()));
            module.setParentPath(parent.getParentPath() + "-" + parent.getCode());
            module.setCreateTime(new Date());
            module.setCreateUser(AuthUtil.getUsername());
            sysModuleService.insert(module);
        } catch (Exception e) {
            logger.error("save SysModule error. module:{}", module, e);
            return ResponseBean.getInstance(ModuleConstants.MODULE_ERROR_CODE, ModuleConstants.MODULE_ERROR_MSG, ModuleConstants.MODULE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @PostMapping("/update")
    public Object update(SysModule module) {
        try {
            SysModule dbModule = sysModuleService.selectById(module.getId());
            // 系统数据不允许变更
            if (SysConstants.MODULE_CODE_ROOT.equals(module.getCode())) {
                return ResponseBean.getInstance(SysConstants.SYS_DATA_MODIFY_ERROR_CODE, SysConstants.SYS_DATA_MODIFY_ERROR_MSG, SysConstants.SYS_DATA_MODIFY_ERROR_SHOW_MSG);
            }
            sysModuleService.update(module, dbModule);
        } catch (Exception e) {
            logger.error("update SysModule error. module:{}", module, e);
            return ResponseBean.getInstance(ModuleConstants.MODULE_ERROR_CODE, ModuleConstants.MODULE_ERROR_MSG, ModuleConstants.MODULE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Long id) {
        try {
            SysModule module = sysModuleService.selectById(id);
            if (module != null) {
                // 系统数据不允许变更
                if (SysConstants.MODULE_CODE_ROOT.equals(module.getCode())) {
                    return ResponseBean.getInstance(SysConstants.SYS_DATA_MODIFY_ERROR_CODE, SysConstants.SYS_DATA_MODIFY_ERROR_MSG, SysConstants.SYS_DATA_MODIFY_ERROR_SHOW_MSG);
                }
                sysModuleService.delete(module);
            }
        } catch (Exception e) {
            logger.error("delete SysModule error. id:{}", id, e);
            return ResponseBean.getInstance(ModuleConstants.MODULE_ERROR_CODE, ModuleConstants.MODULE_ERROR_MSG, ModuleConstants.MODULE_ERROR_SHOW_MSG);
        }
        return ResponseBean.success();
    }
}
