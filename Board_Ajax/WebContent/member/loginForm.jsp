<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인 페이지</title>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script src = "./member/login.js"></script>
<link href ="css/login.css" rel="stylesheet">
<script>
	$(function(){
		$(".join").click(function(){
			location.href="join.net";
		})
	})
</script>
<body>
<h1>로그인</h1>
<hr>
	<form name ="loginform.jsp" action="loginProcess.net" method="post">
		아이디 : <input type="text" name="id" id="id" placeholder="Enter id..." >
		<input type="checkbox" name="check">ID 기억하기<br>
		비밀번호 : <input type="password" name="pass" id="pass" ><br>
	<hr>
		<div class="clearfix">
			<button type="submit" class="submitbtn">로그인</button>
			<input type="button" class="join" value="회원가입">
		</div>
	</form>
</body>
</html>