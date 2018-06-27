package com.frame.boot.frame.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.properties.KaptchaProperties;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysModuleService;
import com.frame.common.frame.base.bean.ResponseBean;
import com.frame.common.frame.utils.EmptyUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/sys")
public class SysController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysModuleService sysModuleService;

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;

    @Autowired
    private KaptchaProperties kaptchaProperties;
    @Autowired
    private DefaultKaptcha kaptchaProducer;

    @RequestMapping("/login")
    public String login(Model model) {
        JSONObject loginModel = new JSONObject();
        loginModel.put("enableValidCode", systemSecurityProperties.isEnableValidCode());
        model.addAttribute("loginModel", loginModel);
        return "sys/login";
    }

    @RequestMapping("/login/validCode")
    public void loginValidCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Set to expire far in the past.
        resp.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        resp.setHeader("Pragma", "no-cache");
        // return a jpeg
        resp.setContentType("image/jpeg");

        String capText = kaptchaProducer.createText();
        BufferedImage bi = kaptchaProducer.createImage(capText);

        // 设置session
        req.getSession().setAttribute(kaptchaProducer.getConfig().getSessionKey(), capText);
        req.getSession().setAttribute(kaptchaProducer.getConfig().getSessionDate(), new Date());

        ServletOutputStream out = null;
        try {
            out = resp.getOutputStream();
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            logger.error("produce valid code error.", e);
            // TODO 返回异常信息
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("produce valid code error.", e);
                }
            }
        }
    }


    @RequestMapping("/index")
    public String index() {
        return "sys/index";
    }
    @RequestMapping("/welcome")
    public String welcome() {
        return "sys/welcome";
    }
    @RequestMapping("/menu")
    @ResponseBody
    public Object menu() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && EmptyUtil.notEmpty(auth.getName())) {
            return ResponseBean.successContent(sysModuleService.findMenuJSONByUsername(auth.getName()));
        } else {
            return null;
        }
    }

    @RequestMapping("/user")
    @ResponseBody
    public Object user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        } else {
            return null;
        }
    }
}
