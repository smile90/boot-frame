package com.frame.boot.frame.portal.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
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
        OptimisticLockingInterceptor interceptor = new OptimisticLockingInterceptor();
        Properties properties = new Properties();
        properties.put("mapper", "se.spagettikod.optimist.impl.MySqlMapper");

        interceptor.setProperties(properties);
        sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
    }
}