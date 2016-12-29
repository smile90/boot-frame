package com.frame;

@SpringBootApplication
@EnableEurekaServer
@EnableConfigurationProperties
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
