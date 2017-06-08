package com.frame.boot.frame.portal.controller.sys;

import com.frame.boot.frame.portal.entity.sys.SysParam;
import com.frame.boot.frame.portal.service.sys.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys")
public class SysParamController {

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping("/create")
    @ResponseBody
    public Object create(SysParam sysParam) {
        sysParam = new SysParam();
        sysParam.setId(1);
        sysParam.setKey("test1");
        sysParam.setDescription("描述");
        return sysParamService.create(sysParam);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestParam("id") Integer id) {
        SysParam sysParam = sysParamService.find(id);
        sysParam.setKey("test2");
        sysParamService.update(sysParam);
        return sysParam;
    }
}
