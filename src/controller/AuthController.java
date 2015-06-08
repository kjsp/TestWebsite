package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.AuthService;

public class AuthController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		AuthService service = AuthService.getInstance();
		String type = request.getParameter("type");

		switch (type) {
		case "login":
			service.login(request, response);
			break;
		case "checkPassword":
			service.confirmPassword(request, response);
			break;
		case "checkId":
			service.checkDuplicateUserId(request, response);
			break;
	
		default:
			break;
		}

		return mv;
	}
}
