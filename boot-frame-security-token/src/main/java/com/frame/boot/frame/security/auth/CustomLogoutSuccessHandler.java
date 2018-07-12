package com.frame.boot.frame.security.auth;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.boot.frame.security.utils.JwtTokenUtil;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.base.constants.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            // 清除Token
            jwtTokenUtil.deleteToken(authentication.getName());

//            SysUser userDetails = (SysUser) authentication.getPrincipal();
//            if (userDetails != null) {

//                // 重新查询最新用户信息
//                SysUser sysUser = sysUserService.findByUsername(userDetails.getUsername());
//                logger.debug("{}", sysUser);
//                // 登出日志记录 TODO
//            }
            logger.info("logout success:{}", authentication.getName());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding(CommonConstant.ENCODING);
        response.getWriter().write(JSONObject.toJSONString(ResponseBean.success()));
    }
}
