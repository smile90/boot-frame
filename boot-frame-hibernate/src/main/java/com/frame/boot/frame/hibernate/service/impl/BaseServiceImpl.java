package com.frame.boot.frame.hibernate.service.impl;

import java.io.Serializable;
import java.util.List;

import com.frame.boot.frame.hibernate.entity.AbstractEntity;
import com.frame.boot.frame.hibernate.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.frame.boot.frame.hibernate.repository.BaseRepository;

@Transactional
public abstract class BaseServiceImpl<E extends AbstractEntity<ID>, ID extends Serializable> implements BaseService<E, ID> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    public BaseRepository<E, ID> repository;

    public E create(E entity) {
//    	entity.setCreateTime(new Date());
        return repository.save(entity);
    }

    public E createAndFlush(E entity) {
//    	entity.setCreateTime(new Date());
        return repository.saveAndFlush(entity);
    }

    public E update(E entity) {
//    	entity.setUpdateTime(new Date());
        return repository.save(entity);
    }

    public void delete(ID id) {
        repository.delete(id);
    }

    public void delete(E entity) {
        repository.delete(entity);
    }

	public void delete(Iterable<E> entities) {
		repository.delete(entities);
	}

	public void deleteInBatch(Iterable<E> entities) {
		repository.deleteInBatch(entities);
	}

    public E findOne(ID id) {
        return repository.findOne(id);
    }

    public E findOne(Specification<E> spec) {
    	return repository.findOne(spec);
    }

    public boolean exists(ID id) {
        return repository.exists(id);
    }

    public boolean exists(Specification<E> spec) {
    	return count(spec) > 0 ? true : false;
    }

    public long count() {
        return repository.count();
    }

    public long count(Specification<E> spec) {
    	return repository.count(spec);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public List<E> findAll(Iterable<ID> ids) {
    	return repository.findAll(ids);
    }

    public List<E> findAll(Specification<E> spec) {
    	return repository.findAll(spec);
    }

    public List<E> findAll(Specification<E> spec, Sort sort) {
    	return repository.findAll(spec, sort);
    }

    public List<E> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<E> findAll(Specification<E> spec, Pageable pageable) {
    	return repository.findAll(spec, pageable);
    }

}
