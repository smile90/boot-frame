package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/{test}")
    @ResponseBody
    public Object test(@PathVariable String test) {
        try {
            template.boundValueOps("test").set(test);
        } catch (Exception e) {
            logger.info("{}", e);
        }
        return JSONObject.toJSON(test);
    }

    @ResponseBody
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return map;
    }

    @ResponseBody
    @RequestMapping(value="/request")
    public Object request (HttpServletRequest request) {
        return String.format("request.getContextPath():%s, request.getServletPath():%s, request.getRemoteUser():%s", request.getContextPath(), request.getServletPath(), request.getRemoteUser());
    }

    @ResponseBody
    @RequestMapping(value="/session")
    public Object session (HttpServletRequest request) {
        return JSONObject.toJSON(request.getSession());
    }
}
