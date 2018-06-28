<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<% request.setCharacterEncoding("euc-kr"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 목록</title>
<link href ="css/list.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/board/header.jsp"></jsp:include>
	<table>
		<thead>
			<tr>
				<th colspan="3">회원 목록</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="m" items="${totallist }">
			<tr>
				<td><a href="member_info.net?id=${m.id}">
				${m.id}</a></td>
				<td>${m.name}</td>
				<td><a href="member_delete.net?id=${m.id}">
				삭제</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
</html>







