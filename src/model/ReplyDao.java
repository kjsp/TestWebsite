package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReplyDao extends OracleDao {
	private static ReplyDao instance = new ReplyDao();

	private ReplyDao() {
		// TODO Auto-generated constructor stub
	}

	public static ReplyDao getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public ArrayList<ReplyVO> getReply(final int articleNo) throws SQLException {
		SQLStrategy st = new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				String sql = "select r.reply_no, r.content,r.reg_date,u.nickname,level "
						+ "from reply r,user_info u "
						+ "where r.user_no = u.user_no and r.article_no=?"
						+ "start with r.child_reply is null "
						+ "CONNECT BY PRIOR r.reply_no=r.child_reply "
						+ "order SIBLINGS by r.reg_date asc";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, articleNo);
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			ArrayList<ReplyVO> QueryResult(ResultSet rs) throws SQLException {
				ArrayList<ReplyVO> rList = new ArrayList<>();
				while (rs.next()) {
					rList.add(new ReplyVO(rs.getInt(1), rs.getString(2), rs
							.getString(3), rs.getString(4), rs.getInt(5)));
				}
				return rList;
			}
		};
		return executeSelectQuery(st);
	}

	public int addReply(final ReplyVO vo, final int userNo) throws SQLException {
		SQLStrategy st = new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				String sql = "insert into reply(reply_no,content,reg_date,child_reply,article_no,user_no) "
						+ "values (reply_seq.nextval,?,sysdate,?,?,?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, vo.getContent());
				if (vo.getChild_reply() != 0)
					ps.setInt(2, vo.getChild_reply());
				else
					ps.setString(2, null);
				ps.setInt(3, vo.getArticle_no());
				ps.setInt(4, userNo);
				return ps;
			}
		};
		return executeUpdateQuery(st);
	}

	public int deleteReply(final int replyNo) throws SQLException {
		SQLStrategy st = new SQLStrategy() {

			@Override
			PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				String sql = "delete from reply where reply_no=?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, replyNo);
				return ps;
			}
		};

		return executeUpdateQuery(st);
	}

	public int updateReply(final String content, final int replyNo) throws SQLException {
		SQLStrategy st = new SQLStrategy() {

			@Override
			PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				String sql = "update reply set content=? where reply_no=?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, content);
				ps.setInt(2, replyNo);
				return ps;
			}
		};

		return executeUpdateQuery(st);
	}

}
