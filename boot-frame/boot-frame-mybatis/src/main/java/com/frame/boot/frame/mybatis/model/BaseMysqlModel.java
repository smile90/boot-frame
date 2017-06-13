package com.frame.boot.frame.mybatis.model;

import se.spagettikod.optimist.Identity;
import se.spagettikod.optimist.Version;

import java.io.Serializable;
import java.util.Date;


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

	private Integer orders;

	private String status;

	private String description;

	private String createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

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

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
