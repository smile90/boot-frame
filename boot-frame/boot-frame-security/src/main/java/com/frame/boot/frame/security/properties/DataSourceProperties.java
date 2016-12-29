package com.frame.boot.frame.security.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/config/security.properties"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "dataSource")
public class DataSourceProperties {

}
