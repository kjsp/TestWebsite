<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<c:set var="bvo" value="${requestScope.bvo}"></c:set>
	<div>
		<div>
			<span class="badge badge-important">No</span><span class="value">${bvo.article_no }</span> 
			<span class="badge">작성자</span><span class="value"> ${bvo.writer }</span> 
			<span class="badge">등록일</span><span class="value"> ${bvo.reg_date }</span> 
			<span class="badge">조회수</span><span class="value"> ${bvo.view_count }</span>
		</div>
		<div>
			<span class="badge badge-info">카테고리</span>
			<span class="value">  
				<c:choose>
					<c:when test="${bvo.category!= null }">${bvo.category }</c:when>
					<c:otherwise>-</c:otherwise>
					</c:choose> 
			</span> 
			<span class="badge">제목 </span> <span class="value">${bvo.title }</span>
			
		</div>
	</div>
	<div class="article_content">${bvo.content }</div>
	<hr>
	<div id="reply"></div>
	<div>
		<a href="main.jsp?menu=board" class="btn">목록보기</a>
		<c:if test="${sessionScope.login.nickname==bvo.writer}">
		<a href="DispatcherServlet?command=article&type=delete&article_no=${bvo.article_no }"class="btn">삭제하기</a> 
		<a href="DispatcherServlet?command=article&type=update&article_no=${bvo.article_no }" class="btn">수정하기</a>
		</c:if>
	</div>
</div>
<script type="text/javascript">
	window.onload = loadReply();

	var reply_flag = false;
	function loadReply() {
		var param = "command=reply&article_no=${bvo.article_no}";
		startAjax(param, printReply);
	}

	function printReply() {
		if (xhr.readyState == 4) { //응답완료
			if (xhr.status == 200) { // 정상수행
				//alert("return: " + xhr.responseText);
				var jsonData = JSON.parse(xhr.responseText);
				var login = "${sessionScope.login != null}";
				var writer = "${sessionScope.login.nickname}";
				var reply = "";
				for ( var i = 0; i < jsonData.length; i++) {
					reply += "<div class='alert alert-info' id=reply_"+jsonData[i].reply_no+">";
					reply += "<span class='value badge badge-important'>" + jsonData[i].writer
							+ "</span>&nbsp;&nbsp;&nbsp;";
					reply += "<span id='reply_content' class='value'>" + jsonData[i].content
							+ "</span>";
					reply += "<span class='value'>" + jsonData[i].reg_date + "</span>";
					if(login == "true"){
						if (jsonData[i].depth < 3) {
							reply += "<button class='btn btn-mini' onclick=addReplyForm('"
									+ jsonData[i].reply_no
									+ "');><i class='icon-plus'></i></button> ";
						}
						if(writer == jsonData[i].writer){
						reply += "<button class='btn btn-mini' onclick=updateReplyForm('"
								+ jsonData[i].reply_no
								+ "');><i class='icon-wrench'></i></button> ";
						reply += "<button class='btn btn-mini' onclick=delReply('"
								+ jsonData[i].reply_no
								+ "');><i class='icon-minus'></i></button>";
						}
					}
						if ((i != jsonData.length - 1)
								&& (jsonData[i].depth <= jsonData[i+1].depth)) {
							if(jsonData[i].depth == jsonData[i+1].depth)
								reply += "</div>";
							else
								reply += "<br><br>";
							continue;
						}
						for ( var j = 0; j <jsonData[i].depth; j++) {
							reply += "</div>";
						}
				}
				reply += "<div id='reply_form'>" + "<span class='badge'>댓글 내용</span> "
						+ "<input type='text' id='reply_content_form' class='input-xxlarge'> "
						+ "<button onclick=addReply(''); class='btn'>등록</button>"
						+ "</div>";
				document.getElementById("reply").innerHTML = reply;
			}
		}
	}

	function check_reply_form() {
		document.getElementById('reply_form').remove();
	}

	function addReplyForm(reply_no) {
		check_reply_form();
		reply_flag = true;
		var reply_input = document.createElement("div");
		reply_input.innerHTML = "<div id='reply_form'>" + "<span class='badge'>댓글 내용</span> "
		+ "<input type='text' id='reply_content_form' class='input-xxlarge'> "
		+ "<button onclick=addReply('" + reply_no + "'); class='btn'>등록</button>"
		+ "</div>";
		var e = (event.target.parentNode.id) ? event.target.parentNode
				: event.target.parentNode.parentNode;
		//alert(e.id);
		e.appendChild(reply_input);
	}

	function updateReplyForm(reply_no) {
		check_reply_form();
		reply_flag = true;
		var reply_input = document.createElement("div");
		reply_input.innerHTML = "<div id='reply_form'>" 
		+ "<span class='badge'>댓글 내용</span> "
		+ "<input type='text' id='reply_content_form' class='input-xxlarge'> "
		+ "<button onclick=updateReply('" + reply_no + "'); class='btn'>수정</button>"
		+ "</div>";
		var e = (event.target.parentNode.id) ? event.target.parentNode
				: event.target.parentNode.parentNode;
		//alert(e.id);
		e.appendChild(reply_input);
	}

	function addReply(reply_no) {
		//alert("ajax로 " + reply_no + "리플 추가");
		var reply_content = document.getElementById('reply_content_form').value;
		//alert(reply_content);
		//등록 ajax로
		var param = "command=reply&type=add&article_no=${bvo.article_no}&writer=${bvo.writer}&child_reply="
				+ reply_no + "&reply_content=" + reply_content;
		startAjax(param, printReply);
	}

	function updateReply(reply_no) {
		//alert("ajax로 " + reply_no + "리플 등록");
		var reply_content = document.getElementById('reply_content_form').value;
		alert(reply_content);
		//수정 ajax로
		var param = "command=reply&type=update&article_no=${bvo.article_no}&reply_no="
				+ reply_no + "&reply_content=" + reply_content;
		startAjax(param, printReply);
	}

	function delReply(reply_no) {
		//alert(reply_no);
		if (confirm("정말로 삭제하시겠습니까?")) {
			var param = "command=reply&type=delete&article_no=${bvo.article_no}&reply_no="
					+ reply_no;
			startAjax(param, printReply);
		}
	}

	function startAjax(param, callback) {
		xhr = new XMLHttpRequest();
		// callback 함수 지정
		xhr.onreadystatechange = callback;
		xhr.open("post", "DispatcherServlet", true);
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr.send(param);
	}
</script>