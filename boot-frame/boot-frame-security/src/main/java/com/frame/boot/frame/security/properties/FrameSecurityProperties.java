package com.frame.boot.frame.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.security")
public class FrameSecurityProperties {

    private String menuTypeCode;

    public String getMenuTypeCode() {
        return menuTypeCode;
    }

    public void setMenuTypeCode(String menuTypeCode) {
        this.menuTypeCode = menuTypeCode;
    }
}
