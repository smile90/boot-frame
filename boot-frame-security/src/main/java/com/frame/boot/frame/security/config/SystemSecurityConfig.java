package com.frame.boot.frame.security.config;

import com.frame.boot.frame.security.authentication.LoginSuccessHandler;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource(name = "securityAuthenticationProvider")
    private AuthenticationProvider securityAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(securityAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("{}", systemSecurityProperties);
        SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
        http
                .headers().frameOptions().sameOrigin()
                .and().authorizeRequests()
                .antMatchers(url.getPermitPaths()).permitAll()
                .antMatchers(url.getAuthenticatePaths()).authenticated()
                .and().formLogin().loginPage(url.getLoginUrl()).permitAll()
                .successHandler(loginSuccessHandler()).defaultSuccessUrl(url.getIndexUrl()).failureUrl(url.getLoginUrl() + "?error")
                .and().logout().logoutUrl(url.getLogoutUrl()).logoutSuccessUrl(url.getLoginUrl()).permitAll();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
