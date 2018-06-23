
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>updateForm.jsp</title>
</head>
<body>
   <form action="updatePro.net">
   	<h1>정보 수정</h1>
   	<input type="hidden" name="id" value="${mem.id}">
   	<hr>
   		<b>아이디</b>
   			<input type="text" name="id" value="${mem.id}" disabled="disabled">
   		<b>Password</b>
   			<input type="text" name="password" value="${mem.password}">
   		<div class="clearfix">
   			<input type="submit" value="변경">
   			<input type="button" onclick="history.go(-1)" value="취소">
   		</div>
   	</form>
</body>
</html>