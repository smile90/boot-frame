package com.frame.boot.frame.security.config;

import com.frame.boot.frame.security.filter.ContextFilter;
import com.frame.boot.frame.security.properties.FrameHostProperties;
import com.frame.boot.frame.security.properties.KaptchaProperties;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    @Bean("ctxContextFilter")
    public FilterRegistrationBean ctxContextFilter(){
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        fitler.setFilter(new ContextFilter());
        fitler.addInitParameter(ContextFilter.STATIC_HOST_CODE, frameHostProperties.getStaticHost());
        return fitler;
    }

    @Bean("kaptchaServletRegistrationBean")
    public ServletRegistrationBean kaptchaServletRegistrationBean() {
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
