package com.frame.common.frame.base.bean;


/**
 * 基础用户
 * @author duancq
 * 2014-3-6 下午2:11:31
 */
public interface IBaseUser {

	/**
	 * 获取主键
	 * @return
	 */
	public abstract String getId();

	/**
	 * 获取用户名
	 * @return
	 */
	public abstract String getUsername();

	/**
	 * 获取用户密码
	 * @return
	 */
	public abstract String getPassword();

	/**
	 * 获取真实姓名
	 * @return
	 */
	public abstract String getRealname();

	/**
	 * 获取用户状态
	 * @return
	 */
	public abstract String getStatus();
}
