package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class OracleDao {
	protected DataSource dataSource;
	
	public OracleDao() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public void close(PreparedStatement ps)
			throws SQLException {
		if (ps != null) {
			ps.close();
		}
		
	}
	
	public void close(ResultSet rs) throws SQLException{
		if (rs != null) {
			rs.close();
		}
	}
	
	protected void disconnect(Connection c) throws SQLException{
		if(c != null)
			c.close();
	}

	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	protected <T> T executeSelectQuery(SQLStrategy st) throws SQLException{
		return executeSelectQuery(getConnection(), st, true);
	}
	
	protected <T> T executeSelectQuery(Connection c, SQLStrategy st, boolean disconnect) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		T result = null;
		try {
			ps = st.makePreparedStatement(c);
			rs = ps.executeQuery();
			result = st.QueryResult(rs);
		}finally{
			close(rs);
			close(ps);
			if(disconnect) disconnect(c);
		}
		return result;
	}
	
	protected int executeUpdateQuery(SQLStrategy st) throws SQLException{
		
		return executeUpdateQuery(getConnection(), st, true);
	}
	
	protected int executeUpdateQuery(Connection c, SQLStrategy st, boolean disconnect) throws SQLException{
		PreparedStatement ps = null;
		int result = -1;
		try {
			ps = st.makePreparedStatement(c);
			result = ps.executeUpdate();
		}finally{
			close(ps);
			if(disconnect) disconnect(c);
		}
		
		return result;
	}
}
