package com.frame.boot.frame.utils.pool;

/**
 * 公共池接口
 * @author duancq
 * 2016年1月30日 下午6:06:34
 */
public interface PoolInterface {

	/**
	 * 获取活动线程数
	 * @return
	 */
	int getActiveCount();
}
