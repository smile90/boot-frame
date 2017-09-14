package com.frame.boot.frame.security.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.frame.boot.frame.security.filter.ContextFilter;
import com.frame.boot.frame.security.properties.DruidProperties;
import com.frame.boot.frame.security.properties.FrameHostProperties;
import com.frame.boot.frame.security.properties.KaptchaProperties;
import com.frame.common.frame.utils.EmptyUtil;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebConfig {

    @Autowired
    private FrameHostProperties frameHostProperties;

    @Autowired
    private KaptchaProperties kaptchaProperties;

    /**
     * @see com.frame.boot.frame.security.filter.ContextFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean contextFilter(){
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        fitler.setFilter(new ContextFilter());
        fitler.addInitParameter(ContextFilter.STATIC_HOST_CODE, frameHostProperties.getStaticHost());
        return fitler;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), kaptchaProperties.getUrl());
        servlet.addInitParameter("kaptcha.border", kaptchaProperties.getBorder());
        servlet.addInitParameter("kaptcha.session.key", kaptchaProperties.getSessionKey());
        servlet.addInitParameter("kaptcha.textproducer.font.color", kaptchaProperties.getFont().getColor());
        servlet.addInitParameter("kaptcha.textproducer.font.size", String.valueOf(kaptchaProperties.getFont().getSize()));
        servlet.addInitParameter("kaptcha.obscurificator.impl", kaptchaProperties.getObscurificator());
        servlet.addInitParameter("kaptcha.noise.impl", kaptchaProperties.getNoise());
        servlet.addInitParameter("kaptcha.image.width", String.valueOf(kaptchaProperties.getImage().getWidth()));
        servlet.addInitParameter("kaptcha.image.height", String.valueOf(kaptchaProperties.getImage().getHeight()));
        servlet.addInitParameter("kaptcha.textproducer.char.length", String.valueOf(kaptchaProperties.getChars().getLength()));
        servlet.addInitParameter("kaptcha.textproducer.char.space", String.valueOf(kaptchaProperties.getChars().getSpace()));
        servlet.addInitParameter("kaptcha.background.clear.from", kaptchaProperties.getBackground().getFrom());
        servlet.addInitParameter("kaptcha.background.clear.to", kaptchaProperties.getBackground().getTo());
        return servlet;
    }
}
