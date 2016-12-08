package com.frame.boot.frame.monitor.resources;

import com.frame.boot.frame.monitor.ResourceInterface;
import com.frame.boot.frame.utils.thread.ThreadExecutor;

/**
 * JDK线程池资源
 * @author duancq
 * 2016年3月11日 下午11:56:59
 */
public class ThreadExecutorResource implements ResourceInterface {

	private String resourceName;
	private ThreadExecutor execuror;

	public ThreadExecutorResource(String resourceName, ThreadExecutor execuror) {
		this.resourceName = resourceName;
		this.execuror = execuror;
	}

	@Override
	public String getGroupName() {
		return "ThreadExecutor";
	}

	@Override
	public String getResourceName() {
		return resourceName;
	}

	@Override
	public String getDescription() {
		return String.format("base:%d,active:%d,queue:%d,completed:%d",
				execuror.getCorePoolSize(),
				execuror.getActiveCount(),
				execuror.getQueueSize(),
				execuror.getCompletedTaskCount());
	}

	@Override
	public boolean isShouldPrintStatus() {
		return execuror.getActiveCount() > 0;
	}

}