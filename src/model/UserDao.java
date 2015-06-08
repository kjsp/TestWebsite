package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends OracleDao{
	private static UserDao instance = new UserDao();
	
	private UserDao(){}
	
	public static UserDao getInstance(){
		return instance;
	}
	
	public UserVO login(final String userId, final String password) throws SQLException{
		SQLStrategy st = new SQLStrategy() {	
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="select user_no,user_id, nickname from user_info where user_id=? and password=? ";
				PreparedStatement ps = c.prepareStatement(sql);
				ps=c.prepareStatement(sql);
				ps.setString(1, userId);
				ps.setString(2, password);
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			UserVO QueryResult(ResultSet rs) throws SQLException {
				UserVO vo = null;
				if(rs.next()){
					vo = new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3));
				}
				return vo;
			}
		};
		return executeSelectQuery(st);
	}

	public int existId(final String userId) throws SQLException {
		SQLStrategy st = new SQLStrategy() {
			
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="select count(*) from user_info where user_id=?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, userId);
				return ps;
			}
			
			@SuppressWarnings("unchecked")
			@Override
			Integer QueryResult(ResultSet rs) throws SQLException {
				int result = -1;
				if(rs.next()){
					result=rs.getInt(1);
				}
				return result;
			}
		};
		return executeSelectQuery(st);
	}
	
	public int join(final UserVO vo) throws SQLException{
		SQLStrategy st = new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="insert into USER_INFO(user_no,user_id,nickname,password,email) values (user_seq.nextval,?,?,?,?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, vo.getUser_id());
				ps.setString(2, vo.getNickname());
				ps.setString(3, vo.getPassword());
				ps.setString(4, vo.getEmail());
				return ps;
			}
		};
		
		return executeUpdateQuery(st);
	}
	public UserVO viewUserInfo(final int userNo) throws SQLException{
		SQLStrategy st =new SQLStrategy() {
			
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="select user_id, nickname,email from USER_INFO where user_no=?";
				PreparedStatement ps=c.prepareStatement(sql);
				ps.setInt(1, userNo);
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			UserVO QueryResult(ResultSet rs) throws SQLException {
				UserVO vo = null;
				if(rs.next()){
					vo=new UserVO(userNo,rs.getString(1),rs.getString(2), rs.getString(3));
				}
				return vo;
			}
			
		};
		return executeSelectQuery(st);
	}
	public int withDrawal(final int userNo,final String password) throws SQLException{
		SQLStrategy st=new SQLStrategy() {
			
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="update USER_INFO set user_id=null, password=null, email=null where user_no=? and password=?";
				PreparedStatement ps=c.prepareStatement(sql);
				ps.setInt(1, userNo);
				ps.setString(2, password);
				return ps;
			}
		};
		return executeUpdateQuery(st);
	}
	public int update(final int userNo, final UserVO vo) throws SQLException{
		SQLStrategy st=new SQLStrategy() {
			
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="update USER_INFO set password=?,nickname=?,email=? where user_no=?";
				PreparedStatement ps=c.prepareStatement(sql);
				ps.setString(1, vo.getPassword());
				ps.setString(2, vo.getNickname());
				ps.setString(3, vo.getEmail());
				ps.setInt(4, userNo);
				return ps;
			}
		};
		return executeUpdateQuery(st);
	}
	
	
}
