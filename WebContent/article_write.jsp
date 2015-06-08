<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	function writeArticle() {
		var title = document.getElementById("title");
		var content = document.getElementById("content");
		var category = document.getElementById("category");
		alert(category.value);
		if(category.value==""){
			alert("카테고리를 선택해 주세요!");
			return false;
		}
		if(title.value==""){
			alert("제목을 입력해 주세요!");
			return false;
		} else if(content.value==""){
			alert("글 내용을 입력해 주세요!");
			return false;
		}
	}
	function cancle(){
		location.href = "main.jsp?menu=board";
	}
	

</script>
<form method="get" action="DispatcherServlet" name="writeForm" onsubmit="return writeArticle()">
<div>
	<div>
		<h2>글쓰기</h2>
	</div>
	<div>
		<span >

		
			<select id="category" name="category" class="span1" >
				<option value="">---</option>
				<c:forEach items="${requestScope.categoryList }" var="category">
					<option value="${category.categoryNo }">${category.categoryName}</option>
				</c:forEach>
			</select>
		</span>
		<input type="text" id="title" name="title" placeholder="제목" class="input-xxlarge">
	</div>
	<div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
		<textarea id="content" name="content" rows="10" class="input-xxlarge"></textarea>
	</div>
	
	<div align="center">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="작성" class="btn" >
		<input type="button" value="취소"class="btn" onclick="cancle()">
	</div>
</div>
<input type="hidden" name="command" value="article">
<input type="hidden" name="type" value="wrtie">
</form>