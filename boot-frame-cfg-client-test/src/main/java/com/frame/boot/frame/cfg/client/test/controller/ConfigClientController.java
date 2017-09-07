package com.frame.boot.frame.cfg.client.test.controller;

import com.frame.boot.frame.cfg.client.test.properties.TestProperties;
import com.frame.boot.frame.cfg.client.test.service.remote.TestRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cfg/client")
public class ConfigClientController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestProperties testProperties;

    @Autowired
    private TestRemoteService testRemoteService;

    @RequestMapping("/remote/{test}")
    public String remoteTest(@PathVariable("test") String test) {
        String result = testRemoteService.test(test);
        logger.info(result);
        return result;
    }

    @RequestMapping("/service/{test}")
    public String helloTest(@PathVariable("test") String test) {
        logger.info(test);
        return test;
    }

    @RequestMapping("/config/hello")
    public String helloString() {
        logger.info(testProperties.toString());
        return testProperties.toString();
    }

    @RequestMapping("/config/hello.json")
    public Object helloJson() {
        logger.info("{}", testProperties);
        return testProperties;
    }
}