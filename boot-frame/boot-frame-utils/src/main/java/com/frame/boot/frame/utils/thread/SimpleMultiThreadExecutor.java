package com.frame.boot.frame.utils.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 简单多线程执行者
 * @author duancq
 * 2015年4月10日下午1:59:51
 */
public class SimpleMultiThreadExecutor implements ThreadExecutor {

	private ThreadPoolExecutor threadPoolExecutor;

	/**
	 * 线程名称
	 */
	private String threadName;

	/**
	 * 初始化
	 *
	 * @param threadName 线程名称
	 * @param corePoolCount 核心线程数
	 * @param maxThreadCount 最大线程数
	 * @param maxQueueSize 最大队列数
	 */
	public SimpleMultiThreadExecutor(String threadName, int corePoolCount, int maxThreadCount, int maxQueueSize) {
		this.threadName = threadName;
		threadPoolExecutor = new ThreadPoolExecutor(corePoolCount, maxThreadCount, 60L,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(maxQueueSize),
				new NamedThreadFactory(threadName), new CallerRunsPolicy());
	}

	@Override
	public Future<?> submit(Runnable task) {
		return threadPoolExecutor.submit(task);
	}

	@Override
	public String getName() {
		return threadName;
	}

	@Override
	public int getPoolSize() {
		return threadPoolExecutor.getPoolSize();
	}

	@Override
	public int getCorePoolSize() {
		return threadPoolExecutor.getCorePoolSize();
	}

	@Override
	public int getActiveCount() {
		return threadPoolExecutor.getActiveCount();
	}

	@Override
	public long getCompletedTaskCount() {
		return threadPoolExecutor.getCompletedTaskCount();
	}

	@Override
	public long getTaskCount() {
		return threadPoolExecutor.getTaskCount();
	}

	@Override
	public int getQueueSize() {
		return threadPoolExecutor.getQueue().size();
	}

	@Override
	public boolean isShutdown() {
		return threadPoolExecutor.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return threadPoolExecutor.isTerminated();
	}
}
