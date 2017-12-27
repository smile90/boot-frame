package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/{test}")
    @ResponseBody
    public Object test(String test) {
        return JSONObject.toJSON(test);
    }
}
