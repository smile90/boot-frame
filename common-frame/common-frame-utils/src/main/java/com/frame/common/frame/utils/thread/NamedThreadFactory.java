package com.frame.common.frame.utils.thread;

import java.util.concurrent.ThreadFactory;

/**
 * 名称线程工厂
 * @author duancq
 * 2015年4月10日上午11:36:21
 */
public class NamedThreadFactory implements ThreadFactory {
	
	private String prefix;
	
	private int seq = 0;

	public NamedThreadFactory(String threadNamePrefix) {
		prefix = threadNamePrefix;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, prefix + "-" + (seq++));
	}
}