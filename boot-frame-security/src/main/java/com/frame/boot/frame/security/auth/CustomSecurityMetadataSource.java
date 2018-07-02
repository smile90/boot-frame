package com.frame.boot.frame.security.auth;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysFunction;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.entity.SysRoleModule;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysFunctionService;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.boot.frame.security.service.SysRoleModuleService;
import com.frame.boot.frame.security.service.SysRoleService;
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

import java.util.*;

@Service("customSecurityMetadataSource")
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysModuleService sysModuleService;
    @Autowired
    private SysFunctionService sysFunctionService;
    @Autowired
    private SysRoleModuleService sysRoleModuleService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
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
        List<ConfigAttribute> authoritys = getAttributesByUrl(requestUrl);
        // 未获取到，则只能超级管理员访问
        if (EmptyUtil.isEmpty(authoritys)) {
            authoritys.add(new SecurityConfig(SysConstants.ROLE_CODE_SUPER_ADMIN));
        }
        logger.debug("url:{}-{}({});authoritys:{}", httpMethod, requestUrl, paramUrl, authoritys);
        return authoritys;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<ConfigAttribute> allCfgAttrs = new ArrayList<>();
        List<SysModule> sysModules = sysModuleService.selectList(null);
        if (EmptyUtil.notEmpty(sysModules)) {
            Set<String> moduleCodes = new HashSet<>();
            for (SysModule sysModule : sysModules) {
                moduleCodes.add(sysModule.getCode());
            }
            List<SysRoleModule> sysRoleModules = sysRoleModuleService.selectList(new EntityWrapper<SysRoleModule>().in("module_code", moduleCodes));
            if (EmptyUtil.notEmpty(sysRoleModules)) {
                for (SysRoleModule SysRoleModule : sysRoleModules) {
                    allCfgAttrs.add(new SecurityConfig(SysRoleModule.getRoleCode()));
                }
            }
        }
        return allCfgAttrs;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 获取URL对应的权限 TODO:需要优化性能
     * @param requestUrl
     * @return
     */
    private List<ConfigAttribute> getAttributesByUrl(String requestUrl) {
        List<ConfigAttribute> authoritys = new ArrayList<>();
        Set<String> moduleCodes = new HashSet<>();

        List<SysFunction> sysFunctions = sysFunctionService.selectList(null);
        if (EmptyUtil.notEmpty(sysFunctions)) {
            for (SysFunction sysFunction : sysFunctions) {
                if (EmptyUtil.notEmpty(sysFunction.getUrl())
                        && antPathMatcher.match(sysFunction.getUrl(), requestUrl)
                        && EmptyUtil.notEmpty(sysFunction.getModuleCode())) {
                    moduleCodes.add(sysFunction.getModuleCode());
                }
            }
        }

        List<SysModule> sysModules = sysModuleService.selectList(null);
        if (EmptyUtil.notEmpty(sysModules)) {
            for (SysModule sysModule : sysModules) {
                if (EmptyUtil.notEmpty(sysModule.getUrl())
                        && antPathMatcher.match(sysModule.getUrl(), requestUrl)) {
                    moduleCodes.add(sysModule.getCode());
                }
            }
        }

        if (EmptyUtil.notEmpty(moduleCodes)) {
            List<SysRoleModule> sysRoleModules = sysRoleModuleService.selectList(new EntityWrapper<SysRoleModule>().in("module_code", moduleCodes));
            if (EmptyUtil.notEmpty(sysRoleModules)) {
                for (SysRoleModule SysRoleModule : sysRoleModules) {
                    authoritys.add(new SecurityConfig(SysRoleModule.getRoleCode()));
                }
            }
        }
        return authoritys;
    }
}
