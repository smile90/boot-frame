package com.frame.boot.frame.common.search.params;

import java.util.ArrayList;
import java.util.List;

import com.frame.boot.frame.common.search.beans.SortData;
import com.frame.boot.frame.common.search.enums.SortType;
import com.frame.boot.frame.utils.EmptyUtil;

/**
 * 查询参数
 * @author duancq
 * 2016年8月26日 下午5:58:35
 */
public class SortParam {

	/** 默认排序 */
	private String sord = "asc";

	/** 排序字段 */
	private String sidx;

	/** 排序 */
	private List<SortData> sorts;

	public SortParam() {}

	public SortParam(List<SortData> sortDatas) {
		this.sorts = sortDatas;
	}

	public SortParam(SortData... sortDatas) {
		if (EmptyUtil.isEmpty(sortDatas)) {
			sorts = new ArrayList<>();
			for (SortData sortData : sortDatas) {
				if (sortData != null) {
					sorts.add(sortData);
				}
			}
		}
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public List<SortData> getSorts() {
		if (EmptyUtil.notEmpty(sidx)) {
			sorts = new ArrayList<>();
			String[] fieldSorts = sidx.split(",");
			for (String filedSort : fieldSorts) {
				String[] temp = filedSort.trim().split(" ");
				if (EmptyUtil.notEmpty(temp)) {
					SortData data = new SortData();
					data.setSortField(temp[0]);
					if (temp.length == 1) {
						data.setSortType(SortType.valueOf(sord.toUpperCase()));
					} else {
						data.setSortType(SortType.valueOf(temp[1].toUpperCase()));
					}
					sorts.add(data);
				}
			}
		}
		return sorts;
	}

	@Override
	public String toString() {
		return String.format("SortParam [sord=%s, sidx=%s]", sord, sidx);
	}

}
