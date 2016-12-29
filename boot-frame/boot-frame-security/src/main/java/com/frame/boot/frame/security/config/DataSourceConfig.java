package com.frame.boot.frame.security.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.frame.boot.frame.security.properties.DataSourceProperties;
import com.frame.boot.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @PostConstruct
    public void init() {
        logger.info("dataSourceProperties:{}", dataSourceProperties);
    }

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(dataSourceProperties.getUrl());
        datasource.setUsername(dataSourceProperties.getUsername());
        datasource.setPassword(dataSourceProperties.getPassword());
        datasource.setDriverClassName(dataSourceProperties.getDriverClassName());

        //configuration
        if (dataSourceProperties.getInitialSize() != null) {
            datasource.setInitialSize(dataSourceProperties.getInitialSize());
        }
        if (dataSourceProperties.getMinIdle() != null) {
            datasource.setMinIdle(dataSourceProperties.getMinIdle());
        }
        if (dataSourceProperties.getMaxActive() != null) {
            datasource.setMaxActive(dataSourceProperties.getMaxActive());
        }
        if (dataSourceProperties.getMaxWait() != null) {
            datasource.setMaxWait(dataSourceProperties.getMaxWait());
        }
        if (dataSourceProperties.getTimeBetweenEvictionRunsMillis() != null) {
            datasource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        }
        if (dataSourceProperties.getMinEvictableIdleTimeMillis() != null) {
            datasource.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        }
        if (EmptyUtil.notEmpty(dataSourceProperties.getValidationQuery())) {
            datasource.setValidationQuery(dataSourceProperties.getValidationQuery());
        }
        if (dataSourceProperties.getTestWhileIdle() != null) {
            datasource.setTestWhileIdle(dataSourceProperties.getTestWhileIdle());
        }
        if (dataSourceProperties.getTestOnBorrow() != null) {
            datasource.setTestOnBorrow(dataSourceProperties.getTestOnBorrow());
        }
        if (dataSourceProperties.getTestOnReturn() != null) {
            datasource.setTestOnReturn(dataSourceProperties.getTestOnReturn());
        }
        if (dataSourceProperties.getPoolPreparedStatements() != null) {
            datasource.setPoolPreparedStatements(dataSourceProperties.getPoolPreparedStatements());
        }
        if (dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize() != null) {
            datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        try {
            if (EmptyUtil.notEmpty(dataSourceProperties.getFilters())) {
                datasource.setFilters(dataSourceProperties.getFilters());
            }
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        if (EmptyUtil.notEmpty(dataSourceProperties.getConnectionProperties())) {
            datasource.setConnectionProperties(dataSourceProperties.getConnectionProperties());
        }

        return datasource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings(dataSourceProperties.getServletUrlMappings());
        for (Map.Entry<String, String> entry : dataSourceProperties.getServletInitParameter().entrySet()) {
            reg.addInitParameter(entry.getKey(), entry.getValue());
        }
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(dataSourceProperties.getFilterUrlPatterns());
        for (Map.Entry<String, String> entry : dataSourceProperties.getFilterInitParameter().entrySet()) {
            filterRegistrationBean.addInitParameter(entry.getKey(), entry.getValue());
        }
        return filterRegistrationBean;
    }
}
