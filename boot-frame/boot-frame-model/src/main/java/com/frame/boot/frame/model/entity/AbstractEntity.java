package com.frame.boot.frame.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable>  implements Persistable<ID>  {

	private static final long serialVersionUID = -1544766588814690602L;

	public abstract ID getId();

    public abstract void setId(final ID id);

    @Override
    public boolean isNew() {
        return null == getId();
    }

	/** 乐观锁 */
	@Version
	@Column(name = "`optimistic`")
    public Long optimistic = 0L;

	/** 状态 */
	@Column(name = "`status`")
    public String status;

	/** 排序 */
	@Column(name = "`orders`")
    public Integer orders;

	/** 创建人 */
	@Column(name = "`create_user`")
    public String createUser;

	/** 创建时间 */
	@Column(name = "`create_time`")
    public Date createTime;

	/** 修改人 */
	@Column(name = "`update_user`")
    public String updateUser;

	/** 修改时间 */
	@Column(name = "`update_time`")
    public Date updateTime;

	public Long getOptimistic() {
		return optimistic;
	}

	public void setOptimistic(Long optimistic) {
		this.optimistic = optimistic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
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

}
