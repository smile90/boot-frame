package com.frame.boot.frame.security.config;

import com.frame.boot.frame.monitor.factory.DruidDataSourceResourceFactory;
import com.frame.boot.frame.monitor.factory.ThreadExecutorResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SystemMonitorConfig {

    @Autowired
    private ThreadExecutorResourceFactory threadExecutorResourceFactory;

    @Autowired
    private DruidDataSourceResourceFactory druidDataSourceResourceFactory;

    @PostConstruct
    public void init() {
        threadExecutorResourceFactory.startup();
        druidDataSourceResourceFactory.startup();
    }
}
