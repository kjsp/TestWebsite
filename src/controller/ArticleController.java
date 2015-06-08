package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.BoardService;

public class ArticleController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = (request.getParameter("type") == null) ? "view":request.getParameter("type");
		BoardService service = BoardService.getInstance();
		ModelAndView mv = null;
		int articleNo = 0;
		System.out.println("type"+type);
		switch (type) {
		case "writeView":
			mv = service.getCategory(request,response);
			break;
		case "delete":
			mv = service.deleteArticle(request,response);
			break;
		case "wrtie":
			articleNo = service.writeArticle(request,response);
			mv = service.viewArticle(request,response, articleNo);
			break;
		case "update":
			mv = service.updateArticle(request,response);
			//mv = service.viewArticle(request,response, articleNo);
			break;
		case "updatepost":
			mv= service.updatePosting(request, response);	
		default:
			mv = service.viewArticle(request,response, articleNo);
			break;
		}
		return mv;
	}

}
