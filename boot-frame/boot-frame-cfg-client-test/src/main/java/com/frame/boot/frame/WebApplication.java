package com.frame.boot.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@RefreshScope
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class WebApplication
{

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
