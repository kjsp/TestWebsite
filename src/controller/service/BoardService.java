package controller.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ArticleVO;
import model.BoardDao;
import model.CategoryVO;
import model.UserVO;
import controller.ModelAndView;
import exception.FailCommandException;
import exception.NotExistArticleException;
import exception.NotLoginException;
import exception.NullValueException;

public class BoardService {
	private static BoardService instance = new BoardService();

	private BoardService() {
		// TODO Auto-generated constructor stub
	}

	public static BoardService getInstance() {
		return instance;
	}

	private int getArticleNo(HttpServletRequest request) throws NullValueException{
		String articleNo = request.getParameter("article_no");
		if(articleNo == null){
			throw new NullValueException("잘못된 접근입니다.");
		}
		return Integer.parseInt(articleNo);	
	}
	
	public ModelAndView updateArticle(HttpServletRequest request,
			HttpServletResponse response) throws NullValueException, NotExistArticleException, SQLException{
		ArticleVO vo = BoardDao.getInstance().getArticle(getArticleNo(request));
		ArrayList<CategoryVO> categoryList = BoardDao.getInstance().getCategoryList();
		request.setAttribute("bvo", vo);
		request.setAttribute("categoryList", categoryList);
		return  new ModelAndView("main.jsp?menu=board_update");	
	}
	
	public ModelAndView updatePosting(HttpServletRequest request,
			HttpServletResponse response) throws NullValueException, SQLException, FailCommandException {
		ArticleVO vo = getArticleContent(request);
		vo.setArticle_no(getArticleNo(request));
		int result = BoardDao.getInstance().updateArticle(vo);
		if(result != 1){
			throw new FailCommandException("수정 실패");
		}
		request.setAttribute("path", "main.jsp?menu=board_update");
		
		return  new ModelAndView("main.jsp?menu=board");	
	}
	
	private int getViewCount(HttpServletRequest request){
		String viewCount = request.getParameter("view_count");
		return (viewCount == null) ? 0 : Integer.parseInt(viewCount);
	}
	
	private int getUserNo(HttpServletRequest request) throws NotLoginException{
		HttpSession session = request.getSession(false);
		if(session == null && session.getAttribute("login") == null){
			throw new NotLoginException("로그인을 해주세요!");
		}
		return ((UserVO) session.getAttribute("login")).getUser_no();
	}
	
	private ArticleVO getArticleContent(HttpServletRequest request) throws NullValueException{
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		if(title.isEmpty() && content.isEmpty()){
			throw new NullValueException();
		}
		return new ArticleVO(category,title, content);	
	}
	
	public int writeArticle(HttpServletRequest request,
			HttpServletResponse response) throws NullValueException, NotLoginException, SQLException {
		ArticleVO vo= getArticleContent(request);
		vo.setArticle_no(getUserNo(request));
		System.out.println("write:"+vo);
		int articleNo = BoardDao.getInstance().writeArticle(vo);
		System.out.println("no:"+articleNo);
		return articleNo;
	}

	public ModelAndView deleteArticle(HttpServletRequest request,
			HttpServletResponse response) throws NullValueException, SQLException, FailCommandException {
		int articleNo=getArticleNo(request);
		int result = BoardDao.getInstance().deleteArticle(articleNo);
		if(result != 1){
			throw new FailCommandException("삭제 실패");
		}
		request.setAttribute("msg", "글이 삭제 되었습니다");
		request.setAttribute("path", "main.jsp?menu=board");
		return  new ModelAndView("main.jsp?menu=printMsg");

	}

	public ModelAndView viewArticle(HttpServletRequest request,
			HttpServletResponse response, int articleNo) throws NotExistArticleException, SQLException, NullValueException {
		if(articleNo <= 0){
			articleNo = getArticleNo(request);
		}
		int viewCount = getViewCount(request);
		
		ArticleVO bvo = BoardDao.getInstance().getArticle(articleNo);
		if(bvo == null){
			throw new NotExistArticleException("존재하지 않는 게시글입니다.");
		}
		System.out.println(bvo);
		BoardDao.getInstance().updateViewCount(viewCount,articleNo);
		request.setAttribute("bvo", bvo);
		return new ModelAndView("main.jsp?menu=board_content", false);
	}

	public ModelAndView getCategory(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		ArrayList<CategoryVO> rList = BoardDao.getInstance().getCategoryList();
		request.setAttribute("categoryList", rList);
		return new ModelAndView("main.jsp?menu=article_write");
	}
	
	

	

}
