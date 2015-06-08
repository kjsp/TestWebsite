<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var xhr;
	window.onload = showBoardList(1);
	function showBoardList(page) {
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = boardList;
		xhr.open("post", "DispatcherServlet", true);
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr.send("command=board&page=" + page);
	}

	function boardList() {
		if (xhr.readyState == 4) {//정상 응답 받았을때
			if (xhr.status == 200) {//정상 수행
				//alert(xhr.responseText);
				var jsonData = JSON.parse(xhr.responseText);
				if (jsonData.error != null) {
					alert(jsonData.error);
					return;
				}
				var tbody = "";
				for ( var i = 0; i < jsonData.list.length; i++) {
					tbody += "<tr>";
					tbody += "<td>" + jsonData.list[i].article_no + "</td>";
					if (jsonData.list[i].category != null) {
						tbody += "<td>" + jsonData.list[i].category + "</td>";
					} else {
						tbody += "<td>" + "-" + "</td>";
					}
					tbody += "<td><a href='DispatcherServlet?command=article&article_no="
							+ jsonData.list[i].article_no
							+ "&view_count="
							+ jsonData.list[i].view_count
							+ "'>"
							+ jsonData.list[i].title + "</a></td>";
					tbody += "<td>" + jsonData.list[i].writer + "</td>";
					tbody += "<td>" + jsonData.list[i].reg_date + "</td>";
					tbody += "<td>" + jsonData.list[i].view_count + "</td>";
					tbody += "</td>";
				}
				document.getElementById("boardlist").innerHTML = tbody;

				var pb = jsonData.pb;
				var pagination = "<ul>";
				if (pb.previousPageGroup) {
					pagination += "<li><span onclick=showBoardList('"
							+ (pb.startPageOfPageGroup - 1)
							+ "'); onmouseover='linkMouseOver()' onmouseout='linkMouseOut()'>Prev</span></li>";
				}
				for ( var i = pb.startPageOfPageGroup; i <= pb.endPageOfPageGroup; i++) {
					if (pb.nowPage == i) {
						pagination += "<li class='disabled'><span>" + i
								+ "</span></li>";
					} else {
						pagination += "<li><span onclick=showBoardList('"
								+ i
								+ "'); onmouseover='linkMouseOver()' onmouseout='linkMouseOut()'>"
								+ i + "</span></li>";
					}

				}
				if (pb.nextPageGroup) {
					pagination += "<li><span onclick=showBoardList('"
							+ (pb.endPageOfPageGroup + 1)
							+ "'); onmouseover='linkMouseOver()' onmouseout='linkMouseOut()'>Next</span></li>";
				}
				pagination += "</ul>";
				document.getElementById("pagination").innerHTML = pagination;
			}
		}
	}

	function linkMouseOver() {
		event.target.style.backgroundColor = "#f5f5f5";
	}

	function linkMouseOut() {
		event.target.style.backgroundColor = "#ffffff";
	}
	function selectsearch() {

		var selectsearch = document.getElementById("howsearch").value;
		//alert(selectsearch);
		var searachcontent = document.getElementById("searchecontent").value;
		//alert(searachcontent);
		if (selectsearch == "searchbynickname") {
			//alert("rrr");
			startAjax("command=board&searchcontent=" + searachcontent
					+ "&type=searchbynickname", boardList);
			//location.href="DispatcherServlet?command=board&searchcontent="+searachcontent+"&type=searchbynickname"
		} else if (selectsearch == "searchbytitle") {
			startAjax("command=board&searchcontent=" + searachcontent
					+ "&type=searchbytitle", boardList)
			//location.href="DispatcherServlet?command=board&searchcontent="+searachcontent+"&type=searchbytitle"
		}
	}
</script>
<div class="board">
	<div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>No</th>
					<th width="10%">카테고리</th>
					<th width="60%">제목</th>
					<th width="10%">작성자</th>
					<th>등록일</th>
					<th>조회</th>
				</tr>
			</thead>
			<tbody id="boardlist">
			</tbody>
		</table>
	</div>

	<div>
		<c:if test="${sessionScope.login!=null }">
			<span class="pull-right"><a
				href="DispatcherServlet?command=article&type=writeView" class="btn">글쓰기</a></span>
		</c:if>
		<span class="pull-left"> <input type="hidden"
			name="selectsearch" value=""> <select id="howsearch"
			class="input-small">
				<option>-----</option>
				<option value="searchbynickname">작성자</option>
				<option value="searchbytitle">제목</option>
		</select> <input type="text" name="searchecontent" id="searchecontent">
			<input type="button" id="searchBtn" value="검색"
			class="btn btn-primary" onclick="selectsearch()">
		</span>
	</div>
	<div id="pagination" class="pagination pagination-centered"></div>
</div>
