package com.frame.boot.frame.portal.controller.sys;

import com.frame.boot.frame.mybatis.util.PageUtil;
import com.frame.boot.frame.portal.entity.sys.SysParam;
import com.frame.boot.frame.portal.service.sys.SysParamService;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
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

    @RequestMapping("/page")
    public String page() {
        return "/sys/page.html";
    }

    @RequestMapping("/create")
    @ResponseBody
    public Object create(SysParam sysParam) {
        sysParam = new SysParam();
        sysParam.setId(1L);
        sysParam.setKey("test1");
        sysParam.setDescription("描述");
        return sysParamService.create(sysParam);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestParam("id") Long id) {
        SysParam sysParam = sysParamService.find(id);
        sysParam.setKey("test2");
        sysParamService.update(sysParam);
        return sysParam;
    }

    @RequestMapping("/find")
    @ResponseBody
    public Object findAll() {
        return PageUtil.page(sysParamService.findAll(new PageBounds(1, 2, Order.formString("id.desc"))));
    }
}
