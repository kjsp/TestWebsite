<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function check(userNO, select) {

		var password = document.getElementById("inputPass").value;
		if (password == "") {
			alert("비밀번호를 입력해주세요");
			return;
		} else if (select == "user_withdrawal") {
			document.getElementById("select").value = "withdrawal";
		} else if (select == "user_update") {
			document.getElementById("select").value = "update";
		}

	}
</script>

<H2 class="header">패스워드 확인</H2>

<form method="post" action="DispatcherServlet"
	onsubmit="return check('${param.userNO}','${param.select }')">
	<input type="hidden" name="command" value="user"> <input
		type="hidden" name="type" value="" id="select">

	<div class="control-group">
		<label class="control-label" for="inputPass">Password</label>
		<div class="controls">
			<input type="password" id="inputPass" name="password"
				placeholder="PASSWORD">
		</div>
	</div>


	<div class="control-group">
		<div class="controls">
			<button type="submit" class="btn btn-danger">확인</button>


		</div>
	</div>
	
	</form>