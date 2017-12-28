package com.frame.boot.frame.cfg.client.test.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;

import java.io.Serializable;
import java.util.Date;


/**
 * 基础模型属性
 * @author:changqing.duan
 * @date:2017-03-02 下午8:50
 */
public class BaseModel<T extends Model> extends Model<T> implements Serializable {

	@TableId
	protected Long id;

	@Version
	protected Long optimistic = 0L;

	protected Integer orders;

	protected String status;

	protected String description;

	protected String createUser;

	protected Date createTime;

	protected String updateUser;

	protected Date updateTime;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOptimistic() {
		return optimistic;
	}
	
	public void setOptimistic(Long optimistic) {
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
	protected Serializable pkVal() {
		return this.id;
	}
}
