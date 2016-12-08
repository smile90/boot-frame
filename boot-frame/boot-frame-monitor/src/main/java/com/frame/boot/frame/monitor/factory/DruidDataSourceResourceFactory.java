package com.frame.boot.frame.monitor.factory;

import java.util.Map;
import java.util.Map.Entry;

import com.frame.boot.frame.monitor.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.frame.boot.frame.monitor.resources.DruidDataSourceResource;
import com.frame.boot.frame.utils.EmptyUtil;

/**
 * 资源监控工厂
 * @author duancq
 * 2016年3月11日 下午11:53:54
 */
@Component("druidDataSourceResourceFactory")
public class DruidDataSourceResourceFactory extends ResourceFactory implements ApplicationContextAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void initResourceInstances() {
		Map<String, DruidDataSource> dataSources = applicationContext.getBeansOfType(DruidDataSource.class);
		if (EmptyUtil.notEmpty(dataSources)) {
			for (Entry<String, DruidDataSource> entry : dataSources.entrySet()) {
				resourceInstances.add(new DruidDataSourceResource(entry.getValue()));
			}
		} else {
			logger.info("not found beans of type ThreadExecutor.");
		}
	}

}
