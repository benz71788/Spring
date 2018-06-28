<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("euc-kr"); %>
<jsp:include page="/board/header.jsp"></jsp:include>
<%--<c:set var="m" value="${memberinfo}"/> --%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보</title>
</head>
<body>
	<form>
		<table>
			<tr>
				<td colspan="2">회원 정보</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>${memberinfo.id}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>${memberinfo.password}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${memberinfo.name}</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>${memberinfo.age}</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>${memberinfo.gender}</td>
			</tr>
			<tr>
				<td>Email 주소</td>
				<td>${memberinfo.email}</td>
			</tr>
			<tr>
				<td colspan="2"><a href="member_list.net">리스트로 돌아가기</a></td>			
			</tr>
		</table>
	</form>
</body>
</html>