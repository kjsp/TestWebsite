package controller.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDao;
import model.UserVO;
import controller.ModelAndView;
import exception.FailJoinException;
import exception.FailWithdrawalException;
import exception.NotFoundUserInfoException;
import exception.NullValueException;
import exception.failUpdateException;

public class UserService {
	private static UserService instance = new UserService();
	
	private UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserService getInstance(){
		return instance;
	}

	public ModelAndView join(HttpServletRequest request, HttpServletResponse response) throws NullValueException, SQLException, FailJoinException {
		UserVO vo = getUserInfo(request);
		int result = UserDao.getInstance().join(vo);
		if(result != 1)
			throw new FailJoinException("회원가입실패");
		
		request.setAttribute("msg", "회원가입을 환영합니다.");
		request.setAttribute("path", "main.jsp");
		return new ModelAndView("main.jsp?menu=printMsg");
	}

	public ModelAndView updateUser(HttpServletRequest request,
			HttpServletResponse response) throws NullValueException, SQLException, failUpdateException {
		int userNo=getUserNo(request);
		UserVO vo=getUserInfo(request);
		
		int result=UserDao.getInstance().update(userNo, vo);
		if(result!=1){
			throw new failUpdateException("수정실패하였습니다.");
		}
		request.setAttribute("msg", "수정되었습니다");
		request.setAttribute("path", "main.jsp");
		return new ModelAndView("main.jsp?menu=printMsg");
		
	}

	public ModelAndView deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws FailWithdrawalException, SQLException, NullValueException {
		int userNo=getUserNo(request);
		String password=getPassword(request);
		int result=UserDao.getInstance().withDrawal(userNo, password);
		if(result != 1){
			throw new FailWithdrawalException("비밀번호가 다릅니다");
		}
		
		HttpSession session=request.getSession();
		session.invalidate();
		request.setAttribute("msg", "회원탈퇴가 완료 되었습니다.");
		request.setAttribute("path", "main.jsp");
		return new ModelAndView("main.jsp?menu=printMsg");
		
		
			
		// TODO Auto-generated method stub
		
	}

	public ModelAndView viewUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, NotFoundUserInfoException {
		int userNo=getUserNo(request);
		UserVO vo=UserDao.getInstance().viewUserInfo(userNo);
		if(vo ==null)
			throw new NotFoundUserInfoException("해당하는 회원정보를 불러올수 없습니다");
		
		request.setAttribute("uvo", vo);
		return new ModelAndView("main.jsp?menu=userview");
		// TODO Auto-generated method stub
	}
	
	private int getUserNo(HttpServletRequest request){
		UserVO vo = null;
		HttpSession session = request.getSession(false);
		if(session != null){
			vo = (UserVO) session.getAttribute("login");
		}
		return vo.getUser_no();
	}
	
	private UserVO getUserInfo(HttpServletRequest request) throws NullValueException{
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		if(userId==null || password == null || nickname== null || email == null){
			throw new NullValueException("필요한 값이 입력되지 않았습니다");
		}
		return new UserVO(userId, password, nickname, email);
	}
	
	private String getPassword(HttpServletRequest request) throws NullValueException{
		String password = request.getParameter("password");
		if(password == null)
			throw new NullValueException("패스워드 값이 없습니다.");
		
		return password;
	}

	public ModelAndView updateView(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		int userNo=getUserNo(request);
		UserVO vo=UserDao.getInstance().viewUserInfo(userNo);
		request.setAttribute("uvo", vo);
		
		return new ModelAndView("main.jsp?menu=userupdate");
	}
}
