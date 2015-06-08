package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.UserService;

public class UserController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		UserService service = UserService.getInstance();
		String type = request.getParameter("type");
		switch (type) {

		case "withdrawal":
			mv = service.deleteUser(request, response);
			break;
		case "userinfoview":
			mv=service.viewUserInfo(request, response);
		case "join":
			mv = service.join(request, response);
			break;
		case "updateview":
			mv=service.updateView(request,response);
			break;
		case "update":
			mv = service.updateUser(request, response);
			break;
		default:
			mv = service.viewUserInfo(request, response);
			break;
		}
		System.out.println(mv);
		return mv;
	}

}
