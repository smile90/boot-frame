package com.frame.boot.frame.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.auth.CustomSecurityMetadataSource;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.utils.JwtTokenUtil;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.base.constants.CommonConstant;
import com.frame.common.frame.utils.EmptyUtil;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTLoginFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        boolean ok = false;
        String token = jwtTokenUtil.getToken((HttpServletRequest) request);
        // 首先校验token
        if (EmptyUtil.notEmpty(token)) {
            // 校验Token
            if (jwtTokenUtil.validateToken(token)) {
                // 刷新Token
                jwtTokenUtil.refreshTokenExporation(token);

                // 放置权限
                Authentication authentication = jwtTokenUtil.getAuthentication((HttpServletRequest) request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                ok = true;
            }
        // 系统不做权限校验的跳过
        } else if (!customSecurityMetadataSource.needSysValidate((HttpServletRequest) request)) {
            ok = true;
        }
        if (!ok) {
            // 清空权限
            SecurityContextHolder.getContext().setAuthentication(null);
            // 403，重新登录
            ((HttpServletResponse) response).setStatus(HttpStatus.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding(CommonConstant.ENCODING);
            response.getWriter().write(JSONObject.toJSONString(ResponseBean.getInstance(SysConstants.USER_HAS_NO_AUTH_ERROR_CODE,
                    SysConstants.USER_HAS_NO_AUTH_ERROR_MSG, SysConstants.USER_HAS_NO_AUTH_ERROR_SHOW_MSG)));
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
