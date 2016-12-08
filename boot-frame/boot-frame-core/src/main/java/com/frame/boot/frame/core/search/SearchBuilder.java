package com.frame.boot.frame.core.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.frame.boot.frame.common.search.beans.SearchData;
import com.frame.boot.frame.common.search.beans.SortData;
import com.frame.boot.frame.common.search.params.PageParam;
import com.frame.boot.frame.common.search.params.SearchParam;
import com.frame.boot.frame.common.search.params.SortParam;
import com.frame.boot.frame.utils.EmptyUtil;

/**
 * 查询构建器
 * @author duancq
 * 2016年8月29日 上午10:37:18
 */
public class SearchBuilder {

	/**
	 * 排序
	 * @param sortParam
	 * @return
	 */
	public static Sort sort(SortData sortData) {
		if (sortData != null) {
			return new Sort(new Order(Direction.valueOf(sortData.getSortType().name()), sortData.getSortField()));
		} else {
			return null;
		}
	}

	/**
	 * 排序
	 * @param sortParam
	 * @return
	 */
	public static Sort sort(SortData... sortDatas) {
		if (EmptyUtil.notEmpty(sortDatas)) {
			int len = sortDatas.length;
			Order[] orders = new Order[len];
			for (int i = 0; i < len; i++) {
				orders[i] = new Order(Direction.valueOf(sortDatas[i].getSortType().name()), sortDatas[i].getSortField());
			}
			return new Sort(orders);
		} else {
			return null;
		}
	}

	/**
	 * 排序
	 * @param sortParam
	 * @return
	 */
	public static Sort sort(SortParam sortParam) {
		List<Order> orders = new ArrayList<>();
		if (sortParam != null && EmptyUtil.notEmpty(sortParam.getSorts())) {
			List<SortData> sortDatas = sortParam.getSorts();
			for (SortData sortData : sortDatas) {
				orders.add(new Order(Direction.valueOf(sortData.getSortType().name()), sortData.getSortField()));
			}
			return new Sort(orders);
		} else {
			return null;
		}
	}

	/**
	 * 构建分页请求
	 * @param pageParam
	 * @return
	 */
	public static PageRequest pageRequest(PageParam pageParam) {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		return new PageRequest(pageParam.getJpaPage(), pageParam.getRows());
	}

	/**
	 * 构建分页请求
	 * @param pageParam
	 * @return
	 */
	public static PageRequest pageRequest(PageParam pageParam, SortParam sortParam) {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		Sort sort = sort(sortParam);
		if (sort == null) {
			return new PageRequest(pageParam.getJpaPage(), pageParam.getRows());
		} else {
			return new PageRequest(pageParam.getJpaPage(), pageParam.getRows(), sort);
		}
	}

	/**
	 * 构建分页请求
	 * @param pageParam
	 * @return
	 */
	public static PageRequest pageRequest(PageParam pageParam, Sort sort) {
		if (pageParam == null) {
			pageParam = new PageParam();
		}
		if (sort == null) {
			return new PageRequest(pageParam.getJpaPage(), pageParam.getRows());
		} else {
			return new PageRequest(pageParam.getJpaPage(), pageParam.getRows(), sort);
		}
	}

	/**
	 * 查询
	 * @param searchParam
	 * @param clazz
	 * @return
	 */
	public static <T> Specification<T> specification(SearchParam searchParam, Class<T> clazz) {
		Specification<T> specification = null;
		if (searchParam != null) {
			List<SearchData> searchDatas = searchParam.getSearch();
			if (EmptyUtil.notEmpty(searchDatas)) {
				List<Criteria<T>> criterias = new ArrayList<>();
				for (SearchData searchData: searchDatas) {
					Criteria<T> criteria = new Criteria<T>(searchData);
					criterias.add(criteria);
				}
				specification = CriteriaList.and(criterias);
			}
		}
		return specification;
	}
}
