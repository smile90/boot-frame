package com.frame.boot.frame.cfg.client.test.service.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "boot-frame-cfg-client-test")
public interface TestRemoteService {
    @RequestMapping(value = "/cfg/client/service/{test}", method = RequestMethod.GET)
    String test(@PathVariable("test") String test);
}
