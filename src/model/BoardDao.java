package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDao extends OracleDao{
private static BoardDao instance = new BoardDao();
	
	private BoardDao(){}
	
	public static BoardDao getInstance(){
		return instance;
	}

	public ArrayList<ArticleVO> getArticleList() throws SQLException {
		SQLStrategy st = new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="select b.article_no,b.title,u.nickname ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date,b.view_count from board b,user_info u " +
						"where b.user_no = u.user_no order by article_no desc";
				PreparedStatement ps = c.prepareStatement(sql);
				return ps;
			}
			@SuppressWarnings("unchecked")
			@Override
			ArrayList<ArticleVO> QueryResult(ResultSet rs) throws SQLException {
				ArrayList<ArticleVO> list = new ArrayList<>();
				while(rs.next()){								
					
					list.add(new ArticleVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));			
				}
				return list;
			}
		};
		return executeSelectQuery(st);
	}
	
	public ArticleVO getArticle(final int articleNo) throws SQLException{
		SQLStrategy st = new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="select b.article_no,b.title,u.nickname,b.content ,to_char(b.reg_date,'YYYY.MM.DD') as reg_date,b.view_count,c.category_name "
						+ "from board b,user_info u, category c " +
						"where b.user_no = u.user_no and b.category_no=c.category_no(+) and b.article_no=? ";
				PreparedStatement ps = c.prepareStatement(sql);
				ps=c.prepareStatement(sql);
				ps.setInt(1, articleNo);
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			ArticleVO QueryResult(ResultSet rs) throws SQLException {
				ArticleVO vo = null;
				if(rs.next())
					vo=new ArticleVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
				return vo;
			}
		};
		return executeSelectQuery(st);
	}

	public int updateViewCount(final int viewCount, final int articleNo) throws SQLException {
		SQLStrategy st = new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="update board set view_count=?+1 where article_no=?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps=c.prepareStatement(sql);
				ps.setInt(1, viewCount);
				ps.setInt(2, articleNo);
				return ps;
			}
		};
		return executeUpdateQuery(st);
	}
	
	public int deleteArticle(final int articleNo) throws SQLException{
		SQLStrategy st= new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				c=getConnection();
				String sql="delete from board where article_no=?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setInt(1, articleNo);
				return ps;
			}
		};
		return executeUpdateQuery(st);
	}
	
	public int updateArticle(final ArticleVO vo) throws SQLException{
		SQLStrategy st=new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				c=getConnection();
				String sql="update board set title=?,content=?,category_no=? where article_no=?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getContent());
				ps.setString(3, vo.getCategory());
				ps.setInt(4, vo.getArticle_no());
				//System.out.println("dao"+vo);
				return ps;
			}
		};
		return executeUpdateQuery(st);
	}

	public ArrayList<ArticleVO> getPageList(final int page) throws SQLException{
		SQLStrategy st = new SQLStrategy() {
			
			@Override
			PreparedStatement makePreparedStatement(Connection c)throws SQLException {
				String sql="select b.article_no, b.title,  b.reg_date, b.view_count, u.nickname, b.category_name from("
						+ "select article_no, title, content, reg_date, view_count, user_no, category_name,ceil(rownum/"
						+ CommonConstants.CONTENT_NUMBER_PER_PAGE
						+ ")as page from("
						+ "select b.article_no, b.title, b.content, b.reg_date, b.view_count, b.user_no, c.category_name from board b,category c "
						+ "where b.category_no=c.category_no(+) order by article_no desc"
						+ ")" + ")b, user_info u where page=? and b.user_no=u.user_no";
				PreparedStatement ps= c.prepareStatement(sql);
				ps=c.prepareStatement(sql);
				ps.setInt(1, page);
				return ps;
			}
			@SuppressWarnings("unchecked")
			@Override
			ArrayList<ArticleVO> QueryResult(ResultSet rs) throws SQLException{
				ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
				while(rs.next()){
					list.add(new ArticleVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
				}
					return list;
			}
		};
		return executeSelectQuery(st);
	}

	public int getBoardTotalCount() throws SQLException {
		SQLStrategy st = new SQLStrategy() {

			@Override
			PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				String sql="select count(*) from board";
				PreparedStatement ps=c.prepareStatement(sql);
				ps=c.prepareStatement(sql);
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			Integer QueryResult(ResultSet rs) throws SQLException {
				int count=0;
				if(rs.next())
					count=rs.getInt(1);
				return count;
			}			 
		};
		return executeSelectQuery(st);
	}

	public int writeArticle(final ArticleVO vo) throws SQLException {
		Connection c = getConnection();
		SQLStrategy st = new SQLStrategy() {	
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="insert into board(article_no,title,category_no,content,user_no) values(article_seq.nextval,?,?,?,?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getCategory());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getArticle_no());
				return ps;
			}
		};
		
		if(executeUpdateQuery(c, st, false) == 1){
			st = new SQLStrategy() {
				@Override
				PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					String sql="select article_seq.currval from board";
					PreparedStatement ps = c.prepareStatement(sql);
					return ps;
				}
				@SuppressWarnings("unchecked")
				@Override
				Integer QueryResult(ResultSet rs) throws SQLException {
					int articleNo = -1;
					if(rs.next()){
						articleNo = rs.getInt(1);
					}
					return articleNo;
				}
			};
			
		}
		return executeSelectQuery(c, st, true);
	}
	
	public ArrayList<CategoryVO> getCategoryList() throws SQLException{
		SQLStrategy st = new SQLStrategy() {
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql = "select category_no, category_name from category";
				PreparedStatement ps = c.prepareStatement(sql);
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			ArrayList<CategoryVO> QueryResult(ResultSet rs) throws SQLException {
				ArrayList<CategoryVO> rList = new ArrayList<>();
				while(rs.next()){
					rList.add(new CategoryVO(rs.getInt(1), rs.getString(2)));
				}
				return rList;
			}
		};
		return executeSelectQuery(st);
	}
	public ArrayList<ArticleVO> searchByTitle(final String searchContent) throws SQLException{
		SQLStrategy st=new SQLStrategy() {						
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="select b.article_no, b.title, b.reg_date, b.view_count, u.nickname, c.category_name from user_info u,board b,category c "
						+ "where u.user_no=b.user_no and b.category_no=c.category_no(+) and b.title like ?";
				PreparedStatement ps=c.prepareStatement(sql);
				ps.setString(1, "%"+searchContent+"%");
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			ArrayList<ArticleVO> QueryResult(ResultSet rs) throws SQLException {
				ArrayList<ArticleVO> slist=new ArrayList<ArticleVO>();
				while(rs.next()){
					slist.add(new ArticleVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5), rs.getString(6)));
				}
				
				return slist;
			}
			
		};
		return executeSelectQuery(st);
	}
	public ArrayList<ArticleVO> searchByNickname(final String searchContent) throws SQLException{
		SQLStrategy st=new SQLStrategy() {						
			@Override
			PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				String sql="select b.article_no, b.title, b.reg_date, b.view_count, u.nickname, c.category_name from user_info u,board b,category c "
						+ "where u.user_no=b.user_no and b.category_no=c.category_no(+) and u.nickname like ?";
				PreparedStatement ps=c.prepareStatement(sql);
				ps.setString(1, "%"+searchContent+"%");
				return ps;
			}

			@SuppressWarnings("unchecked")
			@Override
			ArrayList<ArticleVO> QueryResult(ResultSet rs) throws SQLException {
				ArrayList<ArticleVO> slist=new ArrayList<ArticleVO>();
				while(rs.next()){
					slist.add(new ArticleVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
				}
				
				return slist;
			}
			
		};
		return executeSelectQuery(st);
	}
}
