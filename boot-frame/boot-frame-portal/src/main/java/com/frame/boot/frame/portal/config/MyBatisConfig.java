package com.frame.boot.frame.portal.config;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import se.spagettikod.optimist.impl.OptimisticLockingInterceptor;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisConfig {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void addInterceptor() {
        // optimistic
        {
            OptimisticLockingInterceptor optimistic = new OptimisticLockingInterceptor();
            Properties properties = new Properties();
            properties.put("mapper", "se.spagettikod.optimist.impl.MySqlMapper");
            optimistic.setProperties(properties);
            sqlSessionFactory.getConfiguration().addInterceptor(optimistic);
        }
        // page
        {
            OffsetLimitInterceptor offsetLimit = new OffsetLimitInterceptor();
            Properties properties = new Properties();
            properties.put("dialectClass", "com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
            offsetLimit.setProperties(properties);
            sqlSessionFactory.getConfiguration().addInterceptor(offsetLimit);
        }
    }
}