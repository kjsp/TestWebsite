package controller.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ReplyDao;
import model.ReplyVO;
import model.UserVO;

import org.json.JSONArray;

import controller.SendAjax;
import exception.FailCommandException;
import exception.NullValueException;

public class ReplyService {
	private static ReplyService instance = new ReplyService();
	private ReplyService() {}
	
	public static ReplyService getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	
	private int getArticleNo(HttpServletRequest request){
		return (request.getParameter("article_no") != null) ? 
				Integer.parseInt(request.getParameter("article_no")) : 0;
	}
	
	private int getUserNo(HttpServletRequest request){
		UserVO vo = null;
		HttpSession session = request.getSession(false);
		if(session != null){
			vo = (UserVO) session.getAttribute("login");
		}
		return vo.getUser_no();
	}
	
	private int getReplyNo(HttpServletRequest request) throws NullValueException{
		int replyNo = (request.getParameter("reply_no") != null) ? 
				Integer.parseInt(request.getParameter("reply_no")) : 0;
		if(replyNo == 0){
			throw new NullValueException("잘못된 접근입니다.");
		}
		return replyNo;
	}
	
	private String getReplyContent(HttpServletRequest request) throws NullValueException{
		String content=request.getParameter("reply_content");
		if(content == null){
			throw new NullValueException("댓글 내용을 적어주세요");
		}
		return content;
	}
	
	private ReplyVO getReplyInfo(HttpServletRequest request){
		String content=request.getParameter("reply_content");
		int child_reply = (request.getParameter("child_reply") != "") ? 
				Integer.parseInt(request.getParameter("child_reply")) : 0;
				
		return new ReplyVO(content, child_reply, getArticleNo(request));
	}

	public void addReply(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, FailCommandException {
		
		int result = ReplyDao.getInstance().addReply(getReplyInfo(request),getUserNo(request));
		if(result != 1){
			throw new FailCommandException("댓글등록 실패");
		}
	}

	public void getReplyList(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		ArrayList<ReplyVO> rList = ReplyDao.getInstance().getReply(getArticleNo(request));
		System.out.println(rList);
		JSONArray json = new JSONArray(rList);
		System.out.println(json);
		SendAjax.printAjax(request, response, json);
	}

	public void updateReply(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, NullValueException {
		ReplyDao.getInstance().updateReply(getReplyContent(request), getReplyNo(request));
	}

	public void deleteReply(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, NullValueException {
		ReplyDao.getInstance().deleteReply(getReplyNo(request));
		
	}
	
	

}
