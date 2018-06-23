<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE>
<html>
<head>
<title>Insert title here</title>
<link rel= "stylesheet" type="text/css" href="../css/login.css">
<script src="/JspProject/js/jquery-3.3.1.js"></script>
<script>
	$(function() {
		$('.join').click(function() {
			location.href = "join.net";
		});
	})
</script>
</head>
<body>
	<form action="loginpro.net" method="post">
	<h1>로그인 </h1>
		<hr>
		<b>아이디</b>
			<input type="text" name="id" placeholder="Enter id" required> 
		<b>Password</b> 
		<input type="password" name="pass" placeholder="Enter password" required>
		<div class="clearfix">
			<button type="submit" class="submitbtn">로그인</button>
			<button type="button" class="join">회원가입</button>
		</div>
	</form>
</body>
</html>