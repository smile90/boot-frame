package com.frame.boot.frame.portal.rpc.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("boot-frame-security")
public interface SecurityService {

    @RequestMapping(value = "/security/user/{username}" ,method = RequestMethod.GET)
    JSONObject find(@PathVariable("username") String username);
}
