package com.frame.boot.frame.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysController {

    @RequestMapping("/index")
    public String index() {
        return "sys/hello";
    }

    @RequestMapping("/login")
    public String login() {
        return "sys/login";
    }
}
