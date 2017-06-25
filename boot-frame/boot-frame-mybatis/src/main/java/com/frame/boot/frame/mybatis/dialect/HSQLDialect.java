package com.frame.boot.frame.mybatis.dialect;

import com.frame.boot.frame.mybatis.bean.PageBounds;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Dialect for HSQLDB
 * @author badqiu
 * @author miemiedev
 */
public class HSQLDialect extends Dialect{

    public HSQLDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        super(mappedStatement, parameterObject, pageBounds);
    }

    protected String getLimitString(String sql, String offsetName,int offset, String limitName, int limit) {
		boolean hasOffset = offset>0;
		return new StringBuffer( sql.length() + 10 )
		.append( sql )
		.insert( sql.toLowerCase().indexOf( "select" ) + 6, hasOffset ? " limit "+String.valueOf(offset)+" "+String.valueOf(limit) : " top "+String.valueOf(limit) )
		.toString();
	}
    
}
