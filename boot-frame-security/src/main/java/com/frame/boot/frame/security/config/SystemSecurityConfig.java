package com.frame.boot.frame.security.config;

import com.frame.boot.frame.security.authentication.LoginSuccessHandler;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("sysUserService")
    private UserDetailsService sysUserService;

    @Autowired
    @Qualifier("customAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("customSecurityMetadataSource")
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @PostConstruct
    public void init() {
        logger.info("{}", systemSecurityProperties);
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList();
        decisionVoters.add(new AuthenticatedVoter());
        RoleVoter AuthVoter = new RoleVoter();
        AuthVoter.setRolePrefix(null);// 特殊权限投票器,修改前缀为AUTH_
        decisionVoters.add(AuthVoter);
        return new AffirmativeBased(decisionVoters);
    }

    private FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor customFilterSecurityInterceptor = new FilterSecurityInterceptor();
        customFilterSecurityInterceptor.setAuthenticationManager(super.authenticationManagerBean());
        customFilterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        customFilterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
        return customFilterSecurityInterceptor;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
        http
                .headers().frameOptions().sameOrigin()
            .and().addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .authorizeRequests()
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
                .authenticationProvider(authenticationProvider)
                .userDetailsService(sysUserService);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}
