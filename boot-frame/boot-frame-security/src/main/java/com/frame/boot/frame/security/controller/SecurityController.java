package com.frame.boot.frame.security.controller;

import com.frame.boot.frame.security.entity.SystemSecurityUser;
import com.frame.boot.frame.security.service.SystemSecurityUserService;
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
    public SystemSecurityUser find(@PathVariable("username") String username) {
        return systemSecurityUserService.find(username);
    }
}
