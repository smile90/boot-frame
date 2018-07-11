package com.frame.boot.frame.security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

public class Test {

    @org.junit.Test
    public void requestMatch() {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/sys/pwd/*", "/sys/pwd/test"));
        System.out.println(antPathMatcher.match("/sys/pwd/*", "/sys/pwd/test/test"));
        System.out.println(antPathMatcher.match("/sys/pwd/**", "/sys/pwd/test"));
        System.out.println(antPathMatcher.match("/sys/pwd/**", "/sys/pwd/test/test"));
    }

    @org.junit.Test
    public void pwdEncode() {
        String pwd = "$2a$10$1B1qFlepHm/zN1P3hTs4IuJ57WE/nnpub3AMI7E4PSMa4/Fd7J5.C";
        System.out.println(new BCryptPasswordEncoder().matches("123456", pwd));
        pwd = "$2a$10$SIOpU6lCfq0cj8olILxWEu7ELCDgAu5C5ntPo2zp49opuNIWtcQuS";
        System.out.println(new BCryptPasswordEncoder().matches("123456", pwd));
    }
}
