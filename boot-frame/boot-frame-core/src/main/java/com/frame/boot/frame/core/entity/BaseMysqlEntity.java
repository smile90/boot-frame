package com.frame.boot.frame.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseMysqlEntity<ID extends Serializable> extends AbstractEntity<ID> {

	private static final long serialVersionUID = 6086217779965466797L;

	@Id
    @GeneratedValue(generator = "IDGenerator")
    @GenericGenerator(name = "IDGenerator", strategy = "com.frame.boot.frame.core.entity.IDGenerator")
    private ID id;

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

}
