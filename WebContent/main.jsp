<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>5조 홈페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="include/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="include/css/default.css" rel="stylesheet" media="screen">
</head>
<body background="include/img/bg/1.jpg">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<jsp:include page="user_info.jsp"></jsp:include>
				<jsp:include page="menu.jsp"></jsp:include>
			</div>
			<div class="span10">
				<div class="container">
				<c:choose>
					<c:when test="${param.menu != null}">
						<jsp:include page="${param.menu }.jsp"></jsp:include>
					</c:when>
					<c:when test="${sessionScope.login==null }">
						<jsp:include page="login.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
						${sessionScope.login.nickname}님 환영합니다.
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="include/js/bootstrap.min.js"></script>
	<script src="include/js/ajax.js"></script>
	
	<script type="text/javascript">
	<%-- 1분마다 배경 바꾸기--%>
	
	window.onload = changeBackGround();
	function changeBackGround(){
		var now=new Date();
		var mins=now.getMinutes();
		document.body.background="include/img/bg/"+(mins%6+1)+".jpg";
		setTimeout("changeBackGround()",60000);
	}
	</script>
</head>
</html>