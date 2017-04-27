package com.frame..boot.frame.hibernate.entity;

import java.io.Serializable;

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
	@Column(name = "optimistic")
    public Long optimistic = 0L;

	public Long getOptimistic() {
		return optimistic;
	}

	public void setOptimistic(Long optimistic) {
		this.optimistic = optimistic;
	}

}
