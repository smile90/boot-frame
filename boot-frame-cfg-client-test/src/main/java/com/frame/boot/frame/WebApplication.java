package com.frame.boot.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@RefreshScope
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.**.mapper.**")
public class WebApplication
{

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
