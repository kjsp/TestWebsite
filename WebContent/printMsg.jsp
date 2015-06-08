<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("${requestScope.msg}");
	window.location="${requestScope.path}";
</script>