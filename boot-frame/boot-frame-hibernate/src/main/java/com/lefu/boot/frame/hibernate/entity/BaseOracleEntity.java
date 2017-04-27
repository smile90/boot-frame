package com.frame..boot.frame.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseOracleEntity<PK extends Serializable> extends AbstractEntity<PK> {

	private static final long serialVersionUID = 6397194043500936420L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_STORE")
	@Column(name = "id")
    private PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }
}
