package com.lefu.boot.frame.hibernate.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lefu.boot.frame.hibernate.exception.ExceptionConstant;
import com.lefu.boot.frame.hibernate.exception.search.SearchException;
import com.lefu.boot.frame.hibernate.search.enums.SearchConjunction;
import org.springframework.data.jpa.domain.Specification;

/**
 * 查询组
 * @author duancq
 * 2016年8月29日 下午2:55:05
 */
public class CriteriaList<T> implements Specification<T> {

	private List<Criteria<T>> criterias = new ArrayList<>();
	private SearchConjunction conjunction;

	private CriteriaList(List<Criteria<T>> criterias, SearchConjunction conjunction) {
		this.criterias = criterias;
		this.conjunction = conjunction;
	}

	@SafeVarargs
	public static <T> CriteriaList<T> and(Criteria<T>... criterias) {
		return new CriteriaList<T>(Arrays.asList(criterias), SearchConjunction.AND);
	}

	public static <T> CriteriaList<T> and(List<Criteria<T>> criterias) {
		return new CriteriaList<T>(criterias, SearchConjunction.AND);
	}

	@SafeVarargs
	public static <T> CriteriaList<T> or(Criteria<T>... criterias) {
		return new CriteriaList<T>(Arrays.asList(criterias), SearchConjunction.OR);
	}

	public static <T> CriteriaList<T> or(List<Criteria<T>> criterias) {
		return new CriteriaList<T>(criterias, SearchConjunction.OR);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		int size = criterias.size();
		Predicate[] predicates = new Predicate[size];
		for (int i = 0; i < size; i++) {
			predicates[i] = criterias.get(i).toPredicate(root, query, cb);
		}
		switch (conjunction) {
			case AND: return cb.and(predicates);
			case OR: return cb.or(predicates);
			default: throw new SearchException(ExceptionConstant.BASE_SYSTEM_SEARCH_ERROR_CODE, String.format("SearchConjunction not found. conjunction:%s", conjunction));
		}
	}

}
