package com.frame.boot.frame.security.auth;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.exception.FrameSecurityException;
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
        logger.info("login error:{}. {}", username, exception.getMessage());
        logger.debug(null, exception);
        // 登录日志记录 TODO
        JSONObject result = new JSONObject();
        result.put("msg", exception.getMessage());

        if (exception instanceof FrameSecurityException) {
            result.put("code", ((FrameSecurityException) exception).getErrorCode());
            result.put("showMsg", ((FrameSecurityException) exception).getShowMsg());
        } else {
            result.put("code", SysConstants.AUTH_ERROR_CODE);
            result.put("showMsg", SysConstants.AUTH_ERROR_MSG);
        }

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(result.toJSONString());
    }
}
