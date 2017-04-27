package com.frame.boot.frame.mybatis.mapper;

import se.spagettikod.optimist.LockedByAnotherUserException;
import se.spagettikod.optimist.impl.EntityWrapper;
import se.spagettikod.optimist.impl.mapper.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlOptimisticMapper implements Mapper {
	
	@Override
	public Object getCurrentEntityVersionInDatabase(Connection connection, EntityWrapper entityWrapper) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement("SELECT "
							+ entityWrapper.getVersionColumnName() + " FROM "
							+ entityWrapper.getTableName()
							+ " WHERE id = ? ");
			stmt.setObject(1, entityWrapper.getIdentity());
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.first()) {
				Object databaseVersion = rs.getObject(1);
				rs.close();
				return databaseVersion;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new LockedByAnotherUserException();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	@Override
	public boolean isCompatible(Connection connection) {
		return false;
	}
}
