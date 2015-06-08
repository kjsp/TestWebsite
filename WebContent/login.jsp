<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	var xhr;
	function login() {
		var id = document.getElementById("inputID");
		var password = document.getElementById("inputPassword");
		var param = "command=auth&type=login&userid=" + id.value + "&password="
				+ password.value;
		if (id.value == "") {
			alert("아이디를 입력해 주세요!");
		} else if (password.value == "") {
			alert("비밀번호를 입력해 주세요!");
		} else {
			startAjax(param, callback);
		}

	}
	function callback() {
		if (xhr.readyState == 4) { //응답완료
			if (xhr.status == 200) { // 정상수행
				//alert("return: "+xhr.responseText);
				var jsonData = JSON.parse(xhr.responseText);
				//alert(jsonData);
				var error = jsonData.error;
				if (error != null) {
					alert(error);
				} else {
					alert(jsonData.msg);
					window.location = "main.jsp";
				}
			}
		}
	}

	function join() {
		window.location = "main.jsp?menu=join";
	}
</script>
<div class="content">
<form class="form-horizontal">
	<H2 class="header">로그인</H2>
	<div class="control-group">
		<label class="control-label" for="inputID">ID</label>
		<div class="controls">
			<input type="text" id="inputID" placeholder="ID">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputPassword">Password</label>
		<div class="controls">
			<input type="password" id="inputPassword" placeholder="Password">
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button type="button" class="btn" onclick="login()">로그인</button>
			<button type="button" class="btn" onclick="join()">회원가입</button>
		</div>
	</div>
</form>
</div>