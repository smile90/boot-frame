package com.frame.boot.frame.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/role")
public class RoleController {

    @RequestMapping(value = "/page")
    public String page() {
        return "/sys/role/page.jsp";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "/sys/role/add.jsp";
    }
}
