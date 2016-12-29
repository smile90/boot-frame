package com.frame.boot.frame.security.properties;

import com.frame.boot.frame.utils.EmptyUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:/config/dataSource.properties"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties extends org.springframework.boot.autoconfigure.jdbc.DataSourceProperties {

    private static final String SERVLET_URL_MAPPINGS = "/druid/*";

    private static final String FILTER_URL_PATTERNS = "/*";
    private static final String EXCLUSIONS_KEY = "exclusions";
    private static final String EXCLUSIONS_VALUE = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";

    private Integer initialSize;

    private Integer minIdle;

    private Integer maxActive;

    private Integer maxWait;

    private Integer timeBetweenEvictionRunsMillis;

    private Integer minEvictableIdleTimeMillis;

    private String validationQuery;

    private Boolean testWhileIdle;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    private Boolean poolPreparedStatements;

    private Integer maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;

    private String servletUrlMappings = SERVLET_URL_MAPPINGS;
    private Map<String, String> servletInitParameter = new HashMap<>();

    private String filterUrlPatterns = FILTER_URL_PATTERNS;
    private Map<String, String> filterInitParameter = new HashMap<>();

    @PostConstruct
    public void init() {
        if (EmptyUtil.notEmpty(filterInitParameter) && !filterInitParameter.containsKey(EXCLUSIONS_KEY)) {
            filterInitParameter.put(EXCLUSIONS_KEY, EXCLUSIONS_VALUE);
        }
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Integer getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getServletUrlMappings() {
        return servletUrlMappings;
    }

    public void setServletUrlMappings(String servletUrlMappings) {
        this.servletUrlMappings = servletUrlMappings;
    }

    public Map<String, String> getServletInitParameter() {
        return servletInitParameter;
    }

    public void setServletInitParameter(Map<String, String> servletInitParameter) {
        this.servletInitParameter = servletInitParameter;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public String getFilterUrlPatterns() {
        return filterUrlPatterns;
    }

    public void setFilterUrlPatterns(String filterUrlPatterns) {
        this.filterUrlPatterns = filterUrlPatterns;
    }

    public Map<String, String> getFilterInitParameter() {
        return filterInitParameter;
    }

    public void setFilterInitParameter(Map<String, String> filterInitParameter) {
        this.filterInitParameter = filterInitParameter;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DataSourceProperties{");
        sb.append("url=").append(getUrl());
        sb.append(", username=").append(getUsername());
        sb.append(", password=").append("******");
        sb.append(", driverClassName=").append(getDriverClassName());
        sb.append(", initialSize=").append(initialSize);
        sb.append(", minIdle=").append(minIdle);
        sb.append(", maxActive=").append(maxActive);
        sb.append(", maxWait=").append(maxWait);
        sb.append(", timeBetweenEvictionRunsMillis=").append(timeBetweenEvictionRunsMillis);
        sb.append(", minEvictableIdleTimeMillis=").append(minEvictableIdleTimeMillis);
        sb.append(", validationQuery='").append(validationQuery).append('\'');
        sb.append(", testWhileIdle=").append(testWhileIdle);
        sb.append(", testOnBorrow=").append(testOnBorrow);
        sb.append(", testOnReturn=").append(testOnReturn);
        sb.append(", poolPreparedStatements=").append(poolPreparedStatements);
        sb.append(", maxPoolPreparedStatementPerConnectionSize=").append(maxPoolPreparedStatementPerConnectionSize);
        sb.append(", filters='").append(filters).append('\'');
        sb.append(", connectionProperties='").append(connectionProperties).append('\'');
        sb.append(", servletUrlMappings='").append(servletUrlMappings).append('\'');
        sb.append(", servletInitParameter=").append(servletInitParameter);
        sb.append(", filterUrlPatterns='").append(filterUrlPatterns).append('\'');
        sb.append(", filterInitParameter=").append(filterInitParameter);
        sb.append('}');
        return sb.toString();
    }
}
