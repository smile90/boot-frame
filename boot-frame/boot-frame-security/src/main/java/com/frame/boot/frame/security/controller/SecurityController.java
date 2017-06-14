package com.frame.boot.frame.security.controller;

import com.frame.boot.frame.security.constants.SysConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.exception.SecurityException;
import com.frame.boot.frame.security.exception.SystemException;
import com.frame.common.frame.base.bean.InfoBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sys")
public class SecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 跳转到登录表单页面
    @RequestMapping(value="loginPage")
    public String login() {
        return "login";
    }

    /**
     * ajax登录请求
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="login",method= RequestMethod.POST)
    @ResponseBody
    public Object submitLogin(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //  登录
            subject.login(token);
            return InfoBean.SUCCESS(subject.getPrincipal());
        } catch(SecurityException e) {
            logger.error(null, e);
            return InfoBean.FAIL(e.getShowMsg(), e.getErrorCode(), null);
        } catch(Exception e) {
            logger.error(null, e);
            throw new SystemException(e);
        }
    }
}
