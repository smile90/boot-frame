package com.frame.security.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.frame.security.properties.SystemSecurityProperties;

@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SystemSecurityProperties systemSecurityProperties;

	@Resource(name = "securityAuthenticationProvider")
	private AuthenticationProvider securityAuthenticationProvider;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(securityAuthenticationProvider);
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.info("{}", systemSecurityProperties);

    	if (!systemSecurityProperties.isEnableFrameOptions()) {
    		http.headers().frameOptions().disable();
    	}
    	if (!systemSecurityProperties.isEnableCsrf()) {
    		http.csrf().disable();
    	}
    	SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
		http
			.authorizeRequests()
			.antMatchers(url.getPermitPaths()).permitAll()
			.antMatchers(url.getAuthenticatePaths()).authenticated()
			.and().formLogin().loginPage(url.getLoginUrl()).defaultSuccessUrl(url.getIndexUrl()).failureUrl(url.getLoginUrl() + "?error").permitAll()
			.and().logout().logoutUrl(url.getLogoutUrl()).logoutSuccessUrl(url.getLoginUrl()).permitAll();
    }
}
