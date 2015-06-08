package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.ReplyService;

public class ReplyController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = (request.getParameter("type") != null) ? 
				request.getParameter("type") : "view";
		ReplyService service = ReplyService.getInstance();
		switch (type) {
		case "add":
			service.addReply(request, response);
			break;
		case "update":
			service.updateReply(request, response);
			break;
		case "delete":
			service.deleteReply(request, response);
			break;
		default:
			break;
		}
		service.getReplyList(request, response);
		return null;
	}

}
