<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="user_info">
	<c:choose>
		<c:when test="${sessionScope.login==null }">
			<div>로그인해 주세요</div>
			<div><a href="main.jsp?menu=login" class="btn btn-danger btn-small">로그인</a></div>
		</c:when>
		<c:otherwise>
			<div>${sessionScope.login.nickname }님</div>
			<div>
				<span><a href="DispatcherServlet?command=user&userNO=${sessionScope.login.user_no }&type=userinfo" class="btn btn-info btn-small">정보보기</a></span> 
				<span><a href="DispatcherServlet?command=logout" class="btn btn-danger btn-small">로그아웃</a></span>
			</div>
			
		</c:otherwise>
		</c:choose>
</div>