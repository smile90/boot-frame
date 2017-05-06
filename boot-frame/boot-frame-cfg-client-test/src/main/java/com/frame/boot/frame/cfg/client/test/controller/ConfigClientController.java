package com.frame.boot.frame.cfg.client.test.controller;

import com.frame.boot.frame.cfg.client.test.properties.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cfg/client")
public class ConfigClientController {

    @Autowired
    private TestProperties testProperties;

    @RequestMapping("/hello")
    public String helloString() {
        return testProperties.toString();
    }

    @RequestMapping("/hello.json")

    public Object helloJson() {
        return testProperties;
    }
}