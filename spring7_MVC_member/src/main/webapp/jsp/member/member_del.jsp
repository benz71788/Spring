<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/admin.css" rel="stylesheet">
<link href="./resources/css/member.css" rel="stylesheet">
<script src="./resources/js/jquery-3.3.1.js"></script>
</head>
<body>
	<div id="pwd_wrap">
		<h2 class="pwd_title">회원탈퇴</h2>
			<form method="post" action="member_del_ok.nhn"
				onsubmit="return check()">
				<table id="pwd_t">
					<tr>
						<th>아이디</th>
						<td><input type="text" name="join_id" id="join_id" size="14" 
							class="input_box" value="${delm.join_id}"></td>
					</tr>
					<tr>
						<th>회원이름</th>
						<td><input type="text" name="join_name" id="join_name" size="14" 
							class="input_box" value="${delm.join_name}"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="join_pwd" id="join_pwd" size="17" 
							class="input_box"></td>
					</tr>
					<tr>
						<th>탈퇴사유</th>
						<td><textarea name="join_delcont" id="join_delcont" cols="20" rows="5"></textarea></td>
					</tr>
				</table>
				<div id="pwd_menu">
					<input type="submit" value="탈퇴" class="input_button">
					<input type="reset" value="취소" class="input_button"
						onclick="$('#id').focus();">
				</div>
				<div id="pwd_close">
					<input type="button" value="닫기" class="input_button"
						onclick="self.close();">
					<!-- close()메서드로 팝업창을 닫습니다. -->
				</div>
		</form>
	</div>
</body>
</html>