package com.frame.boot.frame.security.config;

import com.frame.boot.frame.security.auth.CustomAdminRoleVoter;
import com.frame.boot.frame.security.auth.CustomLoginSuccessHandler;
import com.frame.boot.frame.security.auth.CustomWebAuthenticationDetailsSource;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.properties.KaptchaProperties;
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
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

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
    private KaptchaProperties kaptchaProperties;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("customAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("customSecurityMetadataSource")
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @PostConstruct
    public void init() {
        logger.info("{}{}", systemSecurityProperties, kaptchaProperties);
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList();
        // 登录状态控制
        decisionVoters.add(new AuthenticatedVoter());
        // 超级管理员投票器，超级管理员全部通过
        CustomAdminRoleVoter superAdminVoter = new CustomAdminRoleVoter();
        superAdminVoter.setRoleCode(SysConstants.ROLE_CODE_SUPER_ADMIN);
        decisionVoters.add(superAdminVoter);
        // 角色权限投票器，不要前缀，拥有角色则通过
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("");
        decisionVoters.add(roleVoter);
        // 有一个通过，则通过
        return new AffirmativeBased(decisionVoters);
    }

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor customFilterSecurityInterceptor = new FilterSecurityInterceptor();
        customFilterSecurityInterceptor.setAuthenticationManager(super.authenticationManagerBean());
        customFilterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        customFilterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
        return customFilterSecurityInterceptor;
    }

    @Bean
    public WebAuthenticationDetailsSource webAuthenticationDetailsSource() {
        CustomWebAuthenticationDetailsSource source = new CustomWebAuthenticationDetailsSource();
        source.setValidCodeName(kaptchaProperties.getFormName());
        return source;
    }

    @Bean
    public CustomLoginSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
        if (systemSecurityProperties.isEnableCsrf()) {
            http.csrf().ignoringAntMatchers(url.getCsrfIgnoringPaths());
        } else {
            http.csrf().disable();
        }
        http.headers().frameOptions().sameOrigin()
            .and()
            .authorizeRequests()
                .antMatchers(url.getPermitPaths()).permitAll()
                .antMatchers(url.getAuthenticatePaths())
                .authenticated()
            .and().formLogin()
                .authenticationDetailsSource(webAuthenticationDetailsSource())
                .loginPage(url.getLoginUrl()).permitAll()
                .successHandler(loginSuccessHandler()).defaultSuccessUrl(url.getIndexUrl())
                .failureUrl(url.getLoginUrl() + "?error")
            .and().rememberMe()
            .and().logout()
                .logoutUrl(url.getLogoutUrl()).logoutSuccessUrl(url.getLoginUrl() + "?logout").permitAll()
// 无权限            .and().exceptionHandling().accessDeniedPage("/403")
            .and()
                .addFilterAfter(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService);
    }

}
