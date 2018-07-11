package com.frame.boot.frame.security.auth;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class CustomWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

    private String validCodeName;

    public String getValidCodeName() {
        return validCodeName;
    }

    public void setValidCodeName(String validCodeName) {
        this.validCodeName = validCodeName;
    }

    @Override
    public CustomWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        super.buildDetails(context);
        return new CustomWebAuthenticationDetails(context, validCodeName);
    }
}
