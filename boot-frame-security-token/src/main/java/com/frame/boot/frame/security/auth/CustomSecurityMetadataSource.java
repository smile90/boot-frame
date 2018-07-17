package com.frame.boot.frame.security.auth;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysModule;
import com.frame.boot.frame.security.entity.SysRole;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.boot.frame.security.service.SysRoleService;
import com.frame.common.frame.base.enums.DataStatus;
import com.frame.common.frame.base.enums.YesNo;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String paramUrl = filterInvocation.getRequestUrl();
        String requestUrl = filterInvocation.getRequest().getServletPath();
        String httpMethod = filterInvocation.getRequest().getMethod();

        // 系统不做校验的请求跳过
        if (!needSysValidate(filterInvocation.getHttpRequest())) {
            return null;
        }
        // 权限不做校验的请求跳过
        if (!needAuthValidate(requestUrl)) {
            return null;
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
        List<SysRole> sysRoles = sysRoleService.selectList(new EntityWrapper<SysRole>().eq("status", DataStatus.NORMAL.name()));
        if (EmptyUtil.notEmpty(sysRoles)) {
            for (SysRole sysRole : sysRoles) {
                allCfgAttrs.add(new SecurityConfig(sysRole.getCode()));
            }
        }
        logger.debug("allCfgAttrs:{}", allCfgAttrs);
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
        Set<String> moduleCodes = new HashSet<>();
        List<SysModule> sysModules = sysModuleService.findEnableListAll();
        if (EmptyUtil.notEmpty(sysModules)) {
            for (SysModule sysModule : sysModules) {
                if (EmptyUtil.notEmpty(sysModule.getUrl())
                        && antPathMatcher.match(sysModule.getUrl(), requestUrl)) {
                    moduleCodes.add(sysModule.getCode());
                }
            }
        }

        // 角色
        List<ConfigAttribute> authoritys = new ArrayList<>();
        List<SysRole> sysRoles = sysRoleService.findByModuleCode(moduleCodes);
        if (EmptyUtil.notEmpty(sysRoles)) {
            for (SysRole sysRole : sysRoles) {
                authoritys.add(new SecurityConfig(sysRole.getCode()));
            }
        }
        return authoritys;
    }

    public boolean needSysValidate(HttpServletRequest request) {
        // 跨域检查请求不做校验
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return false;
        }

        // 系统不做权限验证的请求跳过
        SystemSecurityProperties.Url url = systemSecurityProperties.getUrl();
        if (url != null && EmptyUtil.notEmpty(url.getPermitPaths())) {
            for (String urlPath : url.getPermitPaths()) {
                if (antPathMatcher.match(urlPath, request.getServletPath())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean needAuthValidate(String requestUrl) {
        // 启用的，不做校验的模块
        List<SysModule> sysModules = sysModuleService.selectList(new EntityWrapper<SysModule>().eq("status", DataStatus.NORMAL.name())
                .eq("useable", YesNo.Y.name()).eq("validate", YesNo.N.name()));
        if (EmptyUtil.notEmpty(sysModules)) {
            for (SysModule sysModule : sysModules) {
                if (EmptyUtil.notEmpty(sysModule.getUrl()) && antPathMatcher.match(sysModule.getUrl(), requestUrl)) {
                    return false;
                }
            }
        }
        return true;
    }
}
