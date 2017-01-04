package com.frame.boot.frame.portal.rpc.service;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.portal.rpc.service.impl.SecurityServiceHystrixImpl;
import com.frame.common.frame.base.bean.ResponseBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "boot-frame-security", fallback = SecurityServiceHystrixImpl.class)
public interface SecurityService {

    @RequestMapping(value = "/security/user/{username}" ,method = RequestMethod.GET)
    ResponseBean<JSONObject> find(@PathVariable("username") String username);
}
