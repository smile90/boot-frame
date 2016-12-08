package com.frame.boot.frame.core.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.frame.boot.frame.common.search.beans.SearchData;
import com.frame.boot.frame.common.search.enums.SearchType;
import com.frame.boot.frame.core.exception.SearchException;

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
		if (searchData != null) {
			Path expression = root.get(searchData.getName());
			switch (searchData.getType()) {
				case EQ:
					return cb.equal(expression, (Comparable) SearchUtil.getObjectValue(searchData.getValue(), searchData.getValueType()));
				case NE:
					return cb.notEqual(expression, (Comparable) SearchUtil.getObjectValue(searchData.getValue(), searchData.getValueType()));
				case LIKE:
					return cb.like((Expression<String>) expression, "%" + searchData.getValue() + "%");
				case LT:
					return cb.lessThan(expression, (Comparable) SearchUtil.getObjectValue(searchData.getValue(), searchData.getValueType()));
				case LE:
					return cb.lessThanOrEqualTo(expression, (Comparable) SearchUtil.getObjectValue(searchData.getValue(), searchData.getValueType()));
				case GT:
					return cb.greaterThan(expression, (Comparable) SearchUtil.getObjectValue(searchData.getValue(), searchData.getValueType()));
				case GE:
					return cb.greaterThanOrEqualTo(expression, (Comparable) SearchUtil.getObjectValue(searchData.getValue(), searchData.getValueType()));
				default: throw new SearchException(SearchException.ERROR_CODE_CRITERIA_ERROR,
						String.format("searchType not found. searchData:%s", searchData),
						SearchException.SHOW_MSG_CRITERIA_ERROR);
			}
		} else {
			Path expression = root.get(name);
			switch (type) {
				case EQ:
					return cb.equal(expression, (Comparable) value);
				case NE:
					return cb.notEqual(expression, (Comparable) value);
				case LIKE:
					return cb.like((Expression<String>) expression, "%" + value + "%");
				case LT:
					return cb.lessThan(expression, (Comparable) value);
				case LE:
					return cb.lessThanOrEqualTo(expression, (Comparable) value);
				case GT:
					return cb.greaterThan(expression, (Comparable) value);
				case GE:
					return cb.greaterThanOrEqualTo(expression, (Comparable) value);
				default: throw new SearchException(SearchException.ERROR_CODE_CRITERIA_ERROR,
						String.format("searchType not found. searchData:%s", searchData),
						SearchException.SHOW_MSG_CRITERIA_ERROR);
			}
		}

	}

}
