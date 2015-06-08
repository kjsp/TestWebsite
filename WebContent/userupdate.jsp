<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
	function user_info_check() {
		var id = document.getElementById("inputID");
		var pass = document.getElementById("inputPass");
		var passCheck = document.getElementById("inputPassCheak");
		var name = document.getElementById("inputName");
		var address = document.getElementById("inputAddr");
		var email = document.getElementById("inputEmail");
		var flag = document.getElementsByName("duplicate");

		//alert(id+pass+passCheck+name+address+email);

		 if (pass.value == "") {
			alert("비밀번호를 입력해주세요");
			pass.focus();
		} else if (passCheck.value == "") {
			alert("비밀번호를 다시한번 입력해 주세요");
			passCheck.focus();
		} else if (pass.value != passCheck.value) {
			alert("비밀번호를 다시한번 확인해주세요");
			pass.value = "";
			passCheck.value = "";
			pass.focus();
		} else if (name == "") {
			alert("이름을 입력해주세요");
			name.focus();
		} else if (address == "") {
			alert("주소를 입력해주세요");
			address.focus();
		} else if (email == "") {
			alert("E-mail를 입력해주세요");
			email.focus();
		} else if (flag.value == "duplicateid") {
			alert("아이디가 중복되어 등록할수 없습니다");
			id.value = "";
			id.focus();
		}else{
			return true;
		}

		return false;
	}
	
	function cancel(){
		location.href="main.jsp";
		
	}
	
	</script>

<H2 class="header">회원정보수정</H2>
<form class="form-horizontal" action="DispatcherServlet"  method="post" onsubmit="return user_info_check()">
<input type="hidden" name="type" value="update">
<input type="hidden" name="command" value="user">
<input type="hidden" name="userNO" value="${requestScope.uvo.user_no }">
<input type="hidden" name="userId" value="${requestScope.uvo.user_id }">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
ID &nbsp;&nbsp;&nbsp;
${requestScope.uvo.user_id }
<div class="control-group">
	<label class="control-label" for="inputPass">Password</label>
	<div class="controls">
		<input type="password" id="inputPass" name="password"
			placeholder="PASSWORD">
	</div>
</div>

<div class="control-group">
		<label class="control-label" for="inputPassCheak">Password
			Check</label>
		<div class="controls">
			<input type="password" id="inputPassCheak"
				placeholder="Password Check">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="inputName">NAME</label>
		<div class="controls">
			<input type="text" id="inputName"  value="${requestScope.uvo.nickname }" name="nickname" placeholder="NAME">
		</div>
	</div>

	

	<div class="control-group">
		<label class="control-label" for="inputEmail">E-Mail</label>
		<div class="controls">
			<input type="text" id="inputEmail"  value="${requestScope.uvo.email }" name="email" placeholder="E-Mail">
		</div>
	</div>


<div class="control-group">
		<div class="controls">
			<button type="submit" class="btn">확인</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn" onclick="cancel()">취소</button>
		</div>
	</div>
</form>