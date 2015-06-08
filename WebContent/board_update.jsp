<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function cancle() {
		location.href = "main.jsp";
	}

	function updateArticle() {
		var title = document.getElementById("title");
		var content = document.getElementById("content");

		if (title.value == "") {
			alert("제목을 입력해 주세요!");
			return false;
		} else if (content.value == "") {
			alert("글 내용을 입력해 주세요!");
			return false;
		}
	}
</script>

<form method="post" action="DispatcherServlet"
	onsubmit="return updateArticle()">
	<div class="board_update">

	<div>
		<h2>수정하기</h2>
	</div>
	<div>
		<div>
			<div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span> NO. <input type="text" id="article_no"
					name="article_no" readonly="readonly" value=${bvo.article_no } class="input-mini"></span>
					<span>
					<select id="category" name="category" class="span1">
						<option value="">---</option>
						<c:forEach items="${requestScope.categoryList }" var="category">
							<c:choose>
							<c:when test="${bvo.category == category.categoryName}">
							<option value="${category.categoryNo }" selected="selected">${category.categoryName}</option>
							</c:when>
							<c:otherwise>
							<option value="${category.categoryNo }">${category.categoryName}</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</span>
					<span> <input type="text" id="title" name="title"
					value=${bvo.title } class="input-large"></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span> <input type="text" id="writer"
					name="writer" readonly="readonly" value=${bvo.writer } class="input-small"></span>

			</div>
			<div>
				
				
				
			</div>
		</div>
		<div>
				<span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
			<textarea id="content" name="content" rows="10" class="input-xxlarge" cols="200">
			${bvo.content }
			</textarea>
			</span>
			
		</div>

		<div class="control-group">
			<div class="controls" align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn">확인</button>
				<button type="button" class="btn" onclick="cancle()">취소</button>
			</div>
		</div>
	</div>
	<input type="hidden" name="command" value="article"> 
	<input type="hidden" name="type" value="updatepost">
</form>
