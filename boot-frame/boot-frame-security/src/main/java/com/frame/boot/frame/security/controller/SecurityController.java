package com.frame.boot.frame.security.controller;

import com.frame.boot.frame.security.entity.SystemSecurityUser;
import com.frame.boot.frame.security.service.SystemSecurityUserService;
import com.frame.common.frame.base.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SystemSecurityUserService systemSecurityUserService;

    @RequestMapping(value = "/user/{username}" ,method = RequestMethod.GET)
    public ResponseBean<SystemSecurityUser> find(@PathVariable("username") String username) {
        return ResponseBean.SUCCESS(systemSecurityUserService.find(username));
    }
}
