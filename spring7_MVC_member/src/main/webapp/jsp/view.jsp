<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
	<script>
		alert("./resources/upload${join_file}");
	</script>
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<script>
			alert("다시 로그인 해주세요!");
			location="../member_login.nhn";
		</script>
	</c:if>
	<c:if test="${state == 'edit'}">
		<script>
			alert("수정완료되었습니다.!")
		</script>
	</c:if>
	<div id="main_wrap">
	<h2 class="main_title">사용자 메인화면</h2>
		<form method="post" action="member_logout.nhn">
			<table id="main_t">
				<tr>
					<th colspan="2">
					<input type="button" value="정보수정" class="input_button"
						onclick="location='./member_edit.nhn'">
					<input type="button" value="회원탈퇴" class="input_button"
						onclick="location='./member_del.nhn'">
					<input type="submit" value="로그아웃" class="input_button">
					</th>
				</tr>
				<tr>
					<td>회원이름</td>
					<td>${join_name}님 로그인을 환영합니다.</td>
				</tr>
				<tr>
					<td>프로필사진</td>
					<td>
						<c:if test="${empty join_file}">
							&nbsp;
						</c:if>
						<c:if test="${!empty join_file}">
							<img src="./resources/upload${join_file}" height="100" width="100">
						</c:if>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>