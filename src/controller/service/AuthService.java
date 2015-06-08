package controller.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDao;
import model.UserVO;

import org.json.JSONObject;

import controller.SendAjax;
import exception.FailAuthException;
import exception.NullValueException;

public class AuthService {
	private static AuthService instance = new AuthService();

	private AuthService() {
		// TODO Auto-generated constructor stub
	}

	public static AuthService getInstance() {
		return instance;
	}

	public void login(HttpServletRequest request, HttpServletResponse response) 
					throws IOException, SQLException, FailAuthException, NullValueException {
		final UserVO vo = UserDao.getInstance().login(getUserId(request), getPassword(request));
		//System.out.println(vo);
		if (vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("login", vo);
			JSONObject json = new JSONObject();
			json.put("msg", vo.getNickname() + "님 환영합니다.");
			SendAjax.printAjax(request, response, json);
		} else {
			System.out.println("login 실패");
			throw new FailAuthException("로그인 실패");
		}
	}

	public void checkDuplicateUserId(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NullValueException {
		String userId = getUserId(request);
		String msg="";
		int result=UserDao.getInstance().existId(userId);
		if(result==1){
			msg="중복되는 아이디 입니다";
		}else if(result==0){
			msg=userId+"는 사용가능한 아이디 입니다";
		}
		
		HashMap<String,Object> map= new HashMap<>();
		map.put("msg", msg);
		map.put("flag", result);
		JSONObject obj=new JSONObject(map);
		System.out.println(obj);
		SendAjax.printAjax(request, response, obj);
		
	}

	public void confirmPassword(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
	}
	
	private String getUserId(HttpServletRequest request) throws NullValueException{
		String userId = request.getParameter("userid");
		if(userId==null)
			throw new NullValueException("유저 아이디를 입력해주세요!");
		return userId;
	}
	
	private String getPassword(HttpServletRequest request) throws NullValueException{
		String password = request.getParameter("password");
		if(password.isEmpty())
			throw new NullValueException("패스워드를 입력해주세요!");
		return password;
	}

}
