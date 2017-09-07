package com.frame.boot.frame.hibernate.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.frame.boot.frame.hibernate.exception.ExceptionConstant;
import com.frame.boot.frame.hibernate.search.enums.SearchConjunction;
import org.springframework.data.jpa.domain.Specification;

import com.frame.boot.frame.hibernate.exception.search.SearchException;

/**
 * 查询组
 * @author duancq
 * 2016年8月29日 下午2:55:05
 */
public class CriteriaGroup<T> implements Specification<T> {

	private Criteria<T> criteria1;
	private SearchConjunction conjunction;
	private Criteria<T> criteria2;

	private CriteriaGroup(Criteria<T> criteria1, SearchConjunction conjunction, Criteria<T> criteria2) {
		super();
		this.criteria1 = criteria1;
		this.conjunction = conjunction;
		this.criteria2 = criteria2;
	}

	public static <T> CriteriaGroup<T> and(Criteria<T> criteria1, Criteria<T> criteria2) {
		return new CriteriaGroup<T>(criteria1, SearchConjunction.AND, criteria2);
	}

	public static <T> CriteriaGroup<T> or(Criteria<T> criteria1, Criteria<T> criteria2) {
		return new CriteriaGroup<T>(criteria1, SearchConjunction.OR, criteria2);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		switch (conjunction) {
			case AND: return cb.and(criteria1.toPredicate(root, query, cb), criteria2.toPredicate(root, query, cb));
			case OR: return cb.or(criteria1.toPredicate(root, query, cb), criteria2.toPredicate(root, query, cb));
			default: throw new SearchException(ExceptionConstant.BASE_SYSTEM_SEARCH_ERROR_CODE, String.format("SearchConjunction not found. conjunction:%s", conjunction));
		}
	}

}
