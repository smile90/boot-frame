package com.frame.boot.frame.hibernate.search;

import com.frame.boot.frame.hibernate.exception.ExceptionConstant;
import com.frame.boot.frame.hibernate.exception.search.SearchException;
import com.frame.boot.frame.hibernate.search.enums.SearchType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 查询器
 * @author duancq
 * 2016年8月29日 下午2:55:05
 */
public class Criteria<T> implements Specification<T> {

	private SearchData searchData;

	/** 查询的字段名称 */
	private String name;

	/** 查询条件的类别 */
	private SearchType type;

	/** 查询的字段值 */
	private Object value;

	public Criteria(SearchData searchData) {
		this.searchData = searchData;
	}

	public Criteria(String name, SearchType type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String realName = (searchData != null ? searchData.getName() : name);
		Object realValue = (searchData != null ? searchData.getValue() : value);
		SearchType realType = (searchData != null ? searchData.getType() : type);

		Path expression = root.get(realName);
		switch (realType) {
			case EQ:
				return cb.equal(expression, (Comparable) realValue);
			case NE:
				return cb.notEqual(expression, (Comparable) realValue);
			case LIKE:
				return cb.like((Expression<String>) expression, "%" + realValue + "%");
			case LT:
				return cb.lessThan(expression, (Comparable) realValue);
			case LE:
				return cb.lessThanOrEqualTo(expression, (Comparable) realValue);
			case GT:
				return cb.greaterThan(expression, (Comparable) realValue);
			case GE:
				return cb.greaterThanOrEqualTo(expression, (Comparable) realValue);
			case IN:
				return expression.in(realValue);
			default:
				throw new SearchException(ExceptionConstant.BASE_SYSTEM_SEARCH_ERROR_CODE, String.format("searchType not found. realName:%s,realValue:%s,searchData:%s", realName, realValue, searchData));
		}
	}

}
