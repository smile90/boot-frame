package com.frame.boot.frame.security.auth;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.base.constants.CommonConstant;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        logger.error("{}.{}", request.getServletPath(),accessDeniedException.getMessage());

        response.setContentType("application/json");
        response.setStatus(HttpStatus.SC_OK);
        response.setCharacterEncoding(CommonConstant.ENCODING);
        response.getWriter().write(JSONObject.toJSONString(ResponseBean.getInstance(SysConstants.USER_HAS_NO_AUTH_ERROR_CODE,
                SysConstants.USER_HAS_NO_AUTH_ERROR_MSG, SysConstants.USER_HAS_NO_AUTH_ERROR_SHOW_MSG)));
    }
}
