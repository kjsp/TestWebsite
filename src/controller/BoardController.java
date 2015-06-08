package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticleVO;
import model.BoardDao;
import model.PagingBean;

import org.json.JSONObject;

import exception.searchFailException;

public class BoardController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = getPage(request);
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<ArticleVO> list = null;
		PagingBean pb = null;
		String type=request.getParameter("type");
		String searchContent=request.getParameter("searchcontent");
		
		if(type== null){
			list =BoardDao.getInstance().getPageList(page);
		 	pb=new PagingBean(page,BoardDao.getInstance().getBoardTotalCount());
		}else if(type.equals("searchbytitle")){
			list = BoardDao.getInstance().searchByTitle(searchContent);
			if(list.isEmpty()){
				throw new searchFailException("요청한 내용을 검색할수없습니다");
			}
		}
		else if(type.equals("searchbynickname")){
			list=BoardDao.getInstance().searchByNickname(searchContent);
			if(list.isEmpty()){
				throw new searchFailException("요청한 내용을 검색할수없습니다");
			}
		}
		System.out.println(list);
		map.put("list", list);
		map.put("pb", pb);
		map.put("paging", null);
		JSONObject json=new JSONObject(map);
		SendAjax.printAjax(request, response, json);
		return null;
		
	}
	
	private int getPage(HttpServletRequest request){
		return (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
	}
}
