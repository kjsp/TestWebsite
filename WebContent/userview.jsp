<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	<script>
    	function confirmPass(user_no, select){
    		
    		//alert(user_no+"유저넘버"+select);
    		
    	location.href="main.jsp?menu=confirm_password&userNO="+user_no+"&select="+select;
    		
    		
    		
    	}
    	function update(user_no){
    		
    		
    		location.href="DispatcherServlet?command=user&type=updateview&userNo="+user_no;
    		
    	}
    	
    	
    	
    	</script>
    	
    	
    	<div class="alert alert-info">
    	<H2 class="header">회원정보</H2>
    	
    	<div>
    		ID : ${requestScope.uvo.user_id };
    	</div>
    	
    	<div>
    		NAME : ${requestScope.uvo.nickname };
    	</div>
    	
    	<div>
    		E-Mail: ${requestScope.uvo.email };
    	</div>
    	<br>
    	<br>
    	<div class="control-group">
		<div class="controls">
			<button class="btn btn-danger" onclick="confirmPass('${requestScope.uvo.user_no}', 'user_withdrawal')">회원탈퇴</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn" onclick="update('${requestScope.uvo.user_no}')">정보수정</button>
			
		</div>
		</div>
	</div>
