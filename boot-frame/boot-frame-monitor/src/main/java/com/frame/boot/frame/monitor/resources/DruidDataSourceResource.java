package com.frame.boot.frame.monitor.resources;

import com.alibaba.druid.pool.DruidDataSource;
import com.frame.boot.frame.monitor.ResourceInterface;

public class DruidDataSourceResource implements ResourceInterface {

	private DruidDataSource dataSource;

	public DruidDataSourceResource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String getGroupName() {
		return "DataSource";
	}

	@Override
	public String getResourceName() {
		return dataSource.getName();
	}

	@Override
	public String getDescription() {
		try {
			return String.format("maxActive:%d,connectCount:%d,activeCount:%d,waitThreadsCount:%d",
					// 最大活动数
					dataSource.getMaxActive(),
					// 连接数
					dataSource.getConnectCount(),
					// 活动总数
					dataSource.getActiveCount(),
					// 等待获取连接的线程数量
					dataSource.getWaitThreadCount());
		} catch (Exception e) {
			return "print datasource info error:"+e.toString();
		}
	}

	@Override
	public boolean isShouldPrintStatus() {
		try {
			return dataSource.getActiveCount() > 0;
		} catch (Exception e) {
			return true;
		}
	}
}
