package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path="main.jsp?menu=printMsg";
		HttpSession session=request.getSession(false);
		if(session.getAttribute("login")!=null){
			session.invalidate();
		}
		request.setAttribute("msg", "로그아웃되었습니다.");
		request.setAttribute("path", "main.jsp");
		return new ModelAndView(path);
	}

}
