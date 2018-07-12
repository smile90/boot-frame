package com.frame.boot.frame.security.config;

import com.frame.boot.frame.security.auth.CustomAdminRoleVoter;
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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;
    @Autowired
    private KaptchaProperties kaptchaProperties;

    @Autowired
    @Qualifier("customLoginSuccessHandler")
    private AuthenticationSuccessHandler loginSuccessHandler;
    @Autowired
    @Qualifier("customLoginFailureHandler")
    private AuthenticationFailureHandler loginFailureHandler;
    @Autowired
    @Qualifier("customLogoutSuccessHandler")
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    @Qualifier("customAccessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;
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
    public FilterSecurityInterceptor filterSecurityInterceptor() {
        FilterSecurityInterceptor customFilterSecurityInterceptor = new FilterSecurityInterceptor();
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
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
        // CSRF
        if (systemSecurityProperties.isEnableCsrf()) {
            http.csrf().ignoringAntMatchers(url.getCsrfIgnoringPaths());
        } else {
            http.csrf().disable();
        }
        // Remember Me
        if (systemSecurityProperties.isEnableRememberMe()) {
            http.rememberMe();
        }
        http.headers().frameOptions().sameOrigin()
            .and()
            .authorizeRequests()
                .antMatchers(url.getPermitPaths()).permitAll()
                .antMatchers(url.getAuthenticatePaths()).authenticated()
            .and().formLogin()
                .authenticationDetailsSource(webAuthenticationDetailsSource())
                .loginPage(url.getLoginPageUrl()).loginProcessingUrl(url.getLoginProcessUrl()).permitAll()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
            .and().logout()
                .logoutUrl(url.getLogoutUrl())
                .logoutSuccessHandler(logoutSuccessHandler)
            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and()
                .addFilterAfter(filterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

}
