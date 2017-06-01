package com.frame.boot.frame.portal.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.frame.boot.frame.portal.properties.DruidProperties;
import com.frame.common.frame.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Autowired
    private DruidProperties druidProperties;

    /**
     * ServletRegistrationBean,
     * @see com.alibaba.druid.support.http.ResourceServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean druid = new ServletRegistrationBean();
        druid.setServlet(new StatViewServlet());
        druid.setUrlMappings(Arrays.asList("/druid/*"));

        Map<String,String> params = new HashMap<>();
        if (druidProperties.isLoginEnable()) {
            params.put("loginUsername", druidProperties.getLoginUsername());
            params.put("loginPassword", druidProperties.getLoginPassword());
        }
        druid.setInitParameters(params);
        return druid;
    }

    /**
     * @see com.alibaba.druid.support.http.WebStatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        fitler.setFilter(new WebStatFilter());
        fitler.setUrlPatterns(Arrays.asList("/*"));
        if (EmptyUtil.notEmpty(druidProperties.getExclusions())) {
            fitler.addInitParameter("exclusions", druidProperties.getExclusions());
        }
        return fitler;
    }
}
