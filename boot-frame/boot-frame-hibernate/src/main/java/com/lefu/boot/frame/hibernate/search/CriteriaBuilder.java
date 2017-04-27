package com.lefu.boot.frame.hibernate.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lefu.boot.frame.hibernate.exception.ExceptionConstant;
import com.lefu.boot.frame.hibernate.exception.search.SearchException;
import com.lefu.boot.frame.hibernate.search.enums.SearchConjunction;
import org.springframework.data.jpa.domain.Specification;

/**
 * 查询构建器
 * @author duancq
 * 2016年8月29日 下午2:55:05
 */
public class CriteriaBuilder<T> implements Specification<T> {

	private List<Criteria<T>> criterias = new ArrayList<>();
	private List<SearchConjunction> conjunctions = new ArrayList<>();

	public CriteriaBuilder(Criteria<T> criteria) {
		criterias.add(criteria);
	}

	public CriteriaBuilder(List<Criteria<T>> criterias, List<SearchConjunction> conjunctions) {
		this.criterias = criterias;
		this.conjunctions = conjunctions;
	}

	public CriteriaBuilder<T> and(Criteria<T> criteria) {
		criterias.add(criteria);
		conjunctions.add(SearchConjunction.AND);
		return this;
	}

	public CriteriaBuilder<T> or(Criteria<T> criteria) {
		criterias.add(criteria);
		conjunctions.add(SearchConjunction.OR);
		return this;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, javax.persistence.criteria.CriteriaBuilder cb) {
		// 第一个
		Predicate tempPredicate = criterias.get(0).toPredicate(root, query, cb);

		// 多个的时候，进行处理
		int size = criterias.size();
		if (size >= 1) {
			for (int i = 1; i < size; i++) {
				Criteria<T> criteria = criterias.get(i);
				SearchConjunction conjunction = conjunctions.get(i - 1);
				switch (conjunction) {
					case AND: tempPredicate = cb.and(tempPredicate, criteria.toPredicate(root, query, cb)); break;
					case OR: tempPredicate = cb.or(tempPredicate, criteria.toPredicate(root, query, cb)); break;
					default: throw new SearchException(ExceptionConstant.BASE_SYSTEM_SEARCH_ERROR_CODE, String.format("SearchConjunction not found. conjunction:%s", conjunction));
				}
			}
		}
		return tempPredicate;
	}

}
