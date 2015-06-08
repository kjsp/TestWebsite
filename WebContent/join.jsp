<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

		if (id.value == "") {
			alert("아이디를 입력해주세요");
			id.focus();
		} else if (id.value.length < 4) {
			alert("아이디는 4자 이상 입력해주세요");
			id.focus();
		} else if (pass.value == "") {
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
		} else if (name.value == "") {
			alert("이름을 입력해주세요");
			name.focus();
		} else if (email.value == "") {
			alert("E-mail를 입력해주세요");
			email.focus();
		} else if (flag.value == "duplicateid") {
			alert("아이디가 중복되어 등록할수 없습니다");
			id.value = "";
			id.focus();
		} else {
			return true;
		}

		return false;
	}
	function userCheckID() {
		var id = document.getElementById("inputID");
		var param = "command=auth&type=checkId&userid=" + id.value;
		if (id.value.length < 4) {
			document.getElementById("userIdCheck").innerHTML = "4자이상 입력해주세요";

		} else if (id.value.length >= 4) {
			document.getElementById("userIdCheck").innerHTML = "";
			startAjax(param, callback);
		}

	}
	function callback() {
		if (xhr.readyState == 4) {//응답완료
			if (xhr.status == 200) {//정상수행
				//alert(xhr.responseText);
				var json = JSON.parse(xhr.responseText);
				document.getElementsByName("duplicate").value = "duplicateid";
				if (json.flag == 1) {

					document.getElementById("userIdCheck").innerHTML = json.msg;

				} else if (json.flag == 0) {
					document.getElementById("userIdCheck").innerHTML = json.msg;
					var flag = document.getElementsByName("duplicate");
					flag.value = "notduplicate";
				}
			}
		}

	}
	function cancel() {
		location.href = "main.jsp";

	}
</script>

<form class="form-horizontal" action="DispatcherServlet" method="post"
	onsubmit="return user_info_check()">
	<H2 class="header">회원가입</H2>
	<div class="control-group">
		<label class="control-label" for="inputID">ID</label>
		<div class="controls">
			<input type="text" id="inputID" name="userId" placeholder="ID"
				onkeyup="userCheckID()"> <span id="userIdCheck"></span>
		</div>
	</div>


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
			<input type="text" id="inputName" name="nickname" placeholder="NAME">
		</div>
	</div>



	<div class="control-group">
		<label class="control-label" for="inputEmail">E-Mail</label>
		<div class="controls">
			<input type="text" id="inputEmail" name="email" placeholder="E-Mail">
		</div>
	</div>

	<!-- <div class="control-group">
		<label class="control-label" for="inputIcon">Email address</label>
		<div class="controls">
			<div class="input-prepend">
				<span class="add-on"><i class="icon-envelope"></i></span> <input
					class="input-large" id="inputIcon" type="text">
			</div>
		</div>
	</div> -->


	<input type="hidden" name=duplicate value="duplicateid"> <input
		type="hidden" name="command" value="user"> <input
		type="hidden" name="type" value="join"> <input type="hidden"
		name="select" value="userInsert">
	<div class="control-group">
		<div class="controls">
			<button type="submit" class="btn">회원가입</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn" onclick="cancel()">취소</button>
		</div>
	</div>
</form>