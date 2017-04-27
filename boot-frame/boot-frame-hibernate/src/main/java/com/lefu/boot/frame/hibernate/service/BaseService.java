package com.frame..boot.frame.hibernate.service;

import java.io.Serializable;
import java.util.List;

import com.frame..boot.frame.hibernate.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface BaseService<E extends AbstractEntity<ID>, ID extends Serializable> {

    public E create(E entity);

    public E createAndFlush(E entity);

    public E update(E entity);

    public void delete(ID id);

    public void delete(E entity);

	public void delete(Iterable<E> entities);

	public void deleteInBatch(Iterable<E> entities);

    public E findOne(ID id);

    public E findOne(Specification<E> spec);

    public boolean exists(ID id);

    public boolean exists(Specification<E> spec);

    public long count();

    public long count(Specification<E> spec);

    public List<E> findAll();

    public List<E> findAll(Iterable<ID> ids);

    public List<E> findAll(Specification<E> spec);

    public List<E> findAll(Specification<E> spec, Sort sort);

    public List<E> findAll(Sort sort);

    public Page<E> findAll(Pageable pageable);

    public Page<E> findAll(Specification<E> spec, Pageable pageable);

}
