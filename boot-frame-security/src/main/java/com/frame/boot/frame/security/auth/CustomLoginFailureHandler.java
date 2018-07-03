package com.frame.boot.frame.security.auth;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.exception.SystemSecurityException;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.base.constants.CommonConstant;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customLoginFailureHandler")
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = EmptyUtil.isEmpty(request.getParameter("username")) ? " " : request.getParameter("username");
        logger.debug(null, exception);
        // 登录日志记录 TODO

        ResponseBean result = null;
        if (exception.getCause() instanceof SystemSecurityException) {
            SystemSecurityException securityException = ((SystemSecurityException) exception.getCause());
            result = ResponseBean.getInstance(securityException.getErrorCode(), securityException.getMessage(), securityException.getShowMsg());
        } else {
            result = ResponseBean.getInstance(SysConstants.AUTH_ERROR_CODE, exception.getMessage(), SysConstants.AUTH_ERROR_SHOW_MSG);
        }
        logger.info("login error:{}. {}", username, result);

        // 响应
        response.setContentType("application/json");
        response.setCharacterEncoding(CommonConstant.ENCODING);
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
