package com.frame.boot.frame.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.frame"})
@ServletComponentScan(basePackages = {"com.frame"})
@EnableJpaRepositories(basePackages = {"com.frame"})
@EntityScan(basePackages = {"com.frame"})
@EnableConfigurationProperties
@PropertySource(
    ignoreResourceNotFound = true,
    value = {
        "classpath:/application.properties",
        "classpath:/config/dataSource.properties",
        "classpath:/config/monitor.properties",
        "classpath:/config/security.properties"
    }
)
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
