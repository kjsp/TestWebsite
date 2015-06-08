package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SQLStrategy {
	abstract PreparedStatement makePreparedStatement(Connection c) throws SQLException;
	<T> T QueryResult(ResultSet rs) throws SQLException{
		return null;
	}
}
