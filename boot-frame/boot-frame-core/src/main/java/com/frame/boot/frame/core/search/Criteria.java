package com.frame.boot.frame.core.search;

import com.frame.boot.frame.core.exceptions.SearchException;
import com.frame.common.frame.base.enums.SearchType;
import com.frame.common.frame.base.params.SearchParam;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.*;

/**
 * 查询器
 * @author duancq
 * 2016年8月29日 下午2:55:05
 */
public class Criteria<T> implements Specification<T> {

	private SearchParam searchParam;

	/** 查询的字段名称 */
	private String name;

	/** 查询条件的类别 */
	private SearchType type;

	/** 查询的字段值 */
	private Object value;

	public Criteria(SearchParam searchParam) {
		this.searchParam = searchParam;
	}

	public Criteria(String name, SearchType type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String realName = (searchParam != null ? searchParam.getName() : name);
		Object realValue = (searchParam != null ? searchParam.getValue() : value);
		SearchType realType = (searchParam != null ? searchParam.getType() : type);

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
			default: throw new SearchException(SearchException.ERROR_CODE_CRITERIA_ERROR,
					String.format("searchType not found. searchParam:%s", searchParam),
					SearchException.SHOW_MSG_CRITERIA_ERROR);
		}
	}

}
