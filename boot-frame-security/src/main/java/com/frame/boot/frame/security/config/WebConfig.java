package com.frame.boot.frame.security.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.frame.boot.frame.security.filter.ContextFilter;
import com.frame.boot.frame.security.properties.DruidProperties;
import com.frame.boot.frame.security.properties.FrameHostProperties;
import com.frame.common.frame.utils.EmptyUtil;
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
}
