package com.frame.boot.frame.mybatis.model;

import se.spagettikod.optimist.Identity;
import se.spagettikod.optimist.Version;

import java.io.Serializable;

/**
 * 基础模型属性
 * @author:changqing.duan
 * @date:2017-03-02 下午8:50
 */
public class BaseMysqlModel implements Serializable {
	
	@Identity("id")
	private Integer id;
	
	@Version("optimistic")
	private Integer optimistic = 0;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
