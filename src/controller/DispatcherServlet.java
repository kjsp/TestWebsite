package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import exception.UserErrorAjaxException;
import exception.UserException;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "error.jsp";
		String command = request.getParameter("command");
		Controller controller = null;
		try{
			if(command != null){
				controller = HandlerMapping.getInstance().create(command);
				ModelAndView mv = controller.execute(request, response);
				// ajax일 경우 페이지 정보가 아니므로 ModelAndView 대신 null를 반환한다.
				if(mv==null){
					return;	//수행 중단(ajax 방식은 페이지 이동이 아니므로)
				}
				if(mv.isRedirect()){
					response.sendRedirect(mv.getPath());
				}else{
					request.getRequestDispatcher(mv.getPath()).forward(request, response);
				}
			}
		}catch(UserErrorAjaxException e){
			JSONObject json = new JSONObject();
			json.put("error", e.getMessage());
			System.out.println(e.getMessage());
			SendAjax.printAjax(request, response, json);
		}catch(UserException e){
			System.out.println(e.getMessage());
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("path", "main.jsp");
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect(path);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		this.doGet(request, response);
	}

}
