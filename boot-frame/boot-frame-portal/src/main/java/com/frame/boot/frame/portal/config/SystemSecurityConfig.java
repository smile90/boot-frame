package com.frame.boot.frame.portal.config;

import com.frame.boot.frame.portal.properties.SystemSecurityProperties;
import com.frame.boot.frame.portal.rpc.service.SecurityService;
import com.frame.boot.frame.security.filter.SecurityFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    private SecurityService securityService;

    @Bean
    public FilterRegistrationBean securityFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SecurityFilter(securityService));
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.info("{}", systemSecurityProperties);
		if (systemSecurityProperties.isEnable()) {
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
		else {
			http
				.authorizeRequests()
				.antMatchers("/**").permitAll();
		}
    }
}
