package com.frame.boot.frame.utils.thread;

import java.util.concurrent.Future;

import com.frame.boot.frame.utils.pool.PoolInterface;

/**
 * 线程执行者
 * @author duancq
 * 2015年4月10日上午11:41:38
 */
public interface ThreadExecutor extends PoolInterface {

	/**
	 * 提交用于执行的任务
	 *
	 * @param task
	 * @return
	 */
	Future<?> submit(Runnable task);

	/**
	 * 获取线程池名称
	 *
	 * @return
	 */
	String getName();

	/**
	 * 获取线程总数
	 *
	 * @return
	 */
	int getPoolSize();

	/**
	 * 获取核心线程数
	 *
	 * @return
	 */
	int getCorePoolSize();

	/**
	 * 获取活动线程数
	 *
	 * @return
	 */
	int getActiveCount();

	/**
	 * 获取完成任务总数
	 *
	 * @return
	 */
	long getCompletedTaskCount();

	/**
	 * 获取任务总数
	 *
	 * @return
	 */
	long getTaskCount();

	/**
	 * 获取队列总数
	 *
	 * @return
	 */
	int getQueueSize();

	/**
	 * 是否关闭
	 *
	 * @return
	 */
	boolean isShutdown();

	/**
	 * 是否关闭后任务执行完成
	 *
	 * @return
	 */
	boolean isTerminated();
}
