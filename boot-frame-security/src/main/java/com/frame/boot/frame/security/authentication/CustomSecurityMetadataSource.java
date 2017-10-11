package com.frame.boot.frame.security.authentication;

import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysFunction;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.mapper.SysFunctionMapper;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysFunctionService;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("customSecurityMetadataSource")
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    private SysModuleService sysModuleService;
    @Autowired
    private SysFunctionService sysFunctionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        FilterInvocation filterInvocation = (FilterInvocation) object;
        String paramUrl = filterInvocation.getRequestUrl();
        String requestUrl = filterInvocation.getRequest().getServletPath();
        String httpMethod = filterInvocation.getRequest().getMethod();

        // 不做权限验证的请求跳过
        SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
        if (url != null && EmptyUtil.notEmpty(url.getPermitPaths())) {
            for (String urlPath : url.getPermitPaths()) {
                if (antPathMatcher.match(urlPath, requestUrl)) {
                    return null;
                }
            }
        }

        // 获取URL对应的权限
        List<ConfigAttribute> authoritys = new ArrayList<>();

        List<SysFunction> sysFunctions = sysFunctionService.findAll();
        List<SysModule> sysModules = sysModuleService.findAll();
        if (EmptyUtil.notEmpty(sysFunctions)) {
            for (SysFunction sysFunction : sysFunctions) {
                if (EmptyUtil.notEmpty(sysFunction.getUrl())
                        && antPathMatcher.match(sysFunction.getUrl(), requestUrl)
                        && EmptyUtil.notEmpty(sysFunction.getModuleCode())) {
                    SysModule sysModule = sysModuleService.findByCode(sysFunction.getModuleCode());
                    if (sysModule != null) {
                        authoritys.add(sysModule);
                    }
                }
            }
        }
        if (EmptyUtil.notEmpty(sysModules)) {
            for (SysModule sysModule : sysModules) {
                if (EmptyUtil.notEmpty(sysModule.getUrl())
                        && antPathMatcher.match(sysModule.getUrl(), requestUrl)) {
                    authoritys.add(sysModule);
                }
            }
        }
        logger.info("url:{}-{}({});authoritys:{}", httpMethod, requestUrl, paramUrl, authoritys);
        return authoritys;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<ConfigAttribute> allCfgAttrs = new ArrayList<>();
        List<SysModule> sysModules = sysModuleService.findAll();
        if (EmptyUtil.notEmpty(sysModules)) {
            for (SysModule sysModule : sysModules) {
                allCfgAttrs.add(sysModule);
            }
        }
        return allCfgAttrs;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
