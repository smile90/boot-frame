package com.frame.boot.frame.search;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.frame.boot.frame.security.exception.SearchException;
import com.frame.common.frame.utils.EmptyUtil;

public class SearchBuilder<T> {

    private Wrapper<T> entityWrapper = new EntityWrapper();

    public SearchBuilder() {}

    public SearchBuilder<T> build(SearchData searchData) {
        if (searchData == null || searchData.getType() == null
                || EmptyUtil.isEmpty(searchData.getName()) || EmptyUtil.isEmpty(searchData.getValue())) {
            return this;
        }
        switch (searchData.getType()) {
            case EQ: {
                entityWrapper.eq(searchData.getName(), searchData.getRealValue());
                break;
            }
            case NE: {
                entityWrapper.ne(searchData.getName(), searchData.getRealValue());
                break;
            }
            case LT: {
                entityWrapper.lt(searchData.getName(), searchData.getRealValue());
                break;
            }
            case LE: {
                entityWrapper.le(searchData.getName(), searchData.getRealValue());
                break;
            }
            case GT: {
                entityWrapper.gt(searchData.getName(), searchData.getRealValue());
                break;
            }
            case GE: {
                entityWrapper.ge(searchData.getName(), searchData.getRealValue());
                break;
            }
            case IN: {
                entityWrapper.in(searchData.getName(), searchData.getValue());
                break;
            }
            case LIKE: {
                entityWrapper.like(searchData.getName(), searchData.getValue());
                break;
            }
            default:
                throw new SearchException(SearchException.BUILD_SEARCH_ERROR_CODE,
                        String.format("build search error. searchData:%s", searchData),
                        SearchException.BUILD_SEARCH_ERROR_MSG);
        }
        return this;
    }

    public Wrapper<T> build() {
        return entityWrapper;
    }
}
