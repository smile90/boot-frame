package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONArray;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.exception.SecurityException;
import com.frame.boot.frame.security.exception.SystemException;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.common.frame.base.bean.InfoBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys")
public class SecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysModuleService sysModuleService;


    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "/loginPage")
    public String login() {
        return "/sys/login.jsp";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "/sys/index.jsp";
    }

    /**
     * 欢迎页
     * @return
     */
    @RequestMapping(value = "/welcome")
    public String welcome() {
        return  "/sys/welcome.jsp";
    }

    @RequestMapping(value = "/menu")
    @ResponseBody
    public Object menu() {
        // 获取用户
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return null;
        }
        SysUser user = (SysUser) subject.getPrincipal();
        if (user == null) {
            return null;
        }
        // 获取用户菜单
        return sysModuleService.findMenuByUserId(user.getId());
    }

    /**
     * ajax登录请求
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object submitLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //  登录
            subject.login(token);
            return InfoBean.SUCCESS(subject.getPrincipal());
        } catch (SecurityException e) {
            logger.info("{}", e.toString());
            return InfoBean.FAIL(e.getShowMsg(), e.getErrorCode(), null);
        } catch (AuthenticationException e) {
            if (e.getCause() instanceof SecurityException) {
                logger.info("{}", e.toString());
                return InfoBean.FAIL(((SecurityException) e.getCause()).getShowMsg(), ((SecurityException) e.getCause()).getErrorCode(), null);
            } else {
                logger.error(null, e);
                throw new SystemException(e);
            }
        } catch (Exception e) {
            logger.error(null, e);
            throw new SystemException(e);
        }
    }
}
