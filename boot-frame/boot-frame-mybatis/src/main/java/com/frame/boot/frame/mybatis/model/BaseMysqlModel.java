package com.frame.boot.frame.mybatis.model;

import com.baomidou.mybatisplus.annotations.Version;

import java.io.Serializable;


/**
 * 基础模型属性
 * @author:changqing.duan
 * @date:2017-03-02 下午8:50
 */
public class BaseMysqlModel implements Serializable {

	private String id;

	@Version
	private Integer optimistic = 0;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getOptimistic() {
		return optimistic;
	}
	
	public void setOptimistic(Integer optimistic) {
		this.optimistic = optimistic;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer('{');
		sb.append("id=").append(id);
		sb.append(", optimistic=").append(optimistic);
		sb.append('}');
		return sb.toString();
	}
}
