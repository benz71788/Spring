<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>joinForm.jsp</title>
<link rel= "stylesheet" type="text/css" href="./css/login.css">

<script src="./js/jquery-3.3.1.js"></script>
</head>
<body>
	<form action="joinPro.net" method="post">
		<h1>회원가입</h1>
		<hr>
		<b>아이디</b> 
			<input type="text" name="id" placeholder="Enter id" required><br> 
		<b>password</b>
			<input type="password" name="password" placeholder="Enter password" required><br> 
		<div class="clearfix">
			<button type="submit" class="submitbtn">회원가입</button>
		</div>
	</form>
</body>
</html>