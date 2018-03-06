package com.frame.boot.frame.security.config;

import com.frame.boot.frame.security.dataSource.DataSourceContextHolder;
import com.frame.boot.frame.security.dataSource.DataSourceTypeEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAopConfig {
    private static Logger logger = LoggerFactory.getLogger(DataSourceAopConfig.class);

    @Before("execution(* com.frame..mapper*..*.find*(..)) "
            + " || execution(* com.frame..mapper*..*.get*(..)) "
            + " || execution(* com.frame..mapper*..*.select*(..)) "
            + " || execution(* com.frame..mapper*..*.query*(..))")
    public void setReadDataSourceType(JoinPoint joinPoint) {
        DataSourceContextHolder.setDataSourceType(DataSourceTypeEnum.READ);

        Signature signature = joinPoint.getSignature();
        logger.debug("dataSource:{}.{}:{}", signature.getDeclaringTypeName(), signature.getName(), DataSourceTypeEnum.READ.name());
    }

    @Before("execution(* com.frame..mapper*..*.insert*(..)) "
            + " || execution(* com.frame..mapper*..*.update*(..))"
            + " || execution(* com.frame..mapper*..*.remove*(..))")
    public void setWriteDataSourceType(JoinPoint joinPoint) {
        DataSourceContextHolder.setDataSourceType(DataSourceTypeEnum.PRIMARY);

        Signature signature = joinPoint.getSignature();
        logger.debug("dataSource:{}.{}:{}", signature.getDeclaringTypeName(), signature.getName(), DataSourceTypeEnum.PRIMARY.name());
    }


/*    @Before("execution(* com.fei.springboot.dao..*.*(..)) "
            + " and @annotation(com.fei.springboot.annotation.ReadDataSource) ")
    public void setReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
            DataSourceContextHolder.setRead();
        }

    }

    @Before("execution(* com.fei.springboot.dao..*.*(..)) "
            + " and @annotation(com.fei.springboot.annotation.WriteDataSource) ")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }*/
}
