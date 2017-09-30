package com.frame.boot.frame.security.config;

import com.frame.boot.frame.security.authentication.LoginSuccessHandler;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    @Qualifier("sysUserService")
    private UserDetailsService sysUserService;

    @Autowired
    @Qualifier("securityAuthenticationProvider")
    private AuthenticationProvider securityAuthenticationProvider;

//    @Autowired
//    @Qualifier("securityMetadataSource")
//    private FilterInvocationSecurityMetadataSource securityMetadataSource;
//
//    @Autowired
//    @Qualifier("securityAccessDecisionManager")
//    private AccessDecisionManager securityAccessDecisionManager;

//    @Bean
//    public FilterSecurityInterceptor filterSecurityInterceptor() {
//        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
//        filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
//        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
//        filterSecurityInterceptor.setAccessDecisionManager(securityAccessDecisionManager);
//        return filterSecurityInterceptor;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("{}", systemSecurityProperties);
        SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
        http
//                .addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .headers().frameOptions().sameOrigin()
            .and().authorizeRequests()
                .antMatchers(url.getPermitPaths()).permitAll()
                .antMatchers(url.getAuthenticatePaths()).authenticated()
            .and().formLogin()
                .loginPage(url.getLoginUrl()).permitAll()
                .successHandler(loginSuccessHandler()).defaultSuccessUrl(url.getIndexUrl())
                .failureUrl(url.getLoginUrl() + "?error")
                .and().rememberMe()
            .and().logout()
                .logoutUrl(url.getLogoutUrl()).logoutSuccessUrl(url.getLoginUrl() + "?logout").permitAll()
            .and()
                .authenticationProvider(securityAuthenticationProvider)
                .userDetailsService(sysUserService);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
