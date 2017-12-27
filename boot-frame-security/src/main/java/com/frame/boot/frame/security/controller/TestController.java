package com.frame.boot.frame.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/{test}")
    public void test(String test) {
        logger.info(test);
    }
}
