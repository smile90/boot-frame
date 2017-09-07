package com.frame.boot.frame.monitor;

/**
 * 资源接口
 * @author duancq
 * 2016年3月11日 下午7:58:11
 */
public interface ResourceInterface {

	/**
	 * 获取组名称
	 * @return
	 */
	String getGroupName();

	/**
	 * 获取资源名称
	 * @return
	 */
	String getResourceName();

	/**
	 * 获取描述
	 * @return
	 */
	String getDescription();

	/**
	 * 是否应该输出状态
	 * @return
	 */
	boolean isShouldPrintStatus();
}