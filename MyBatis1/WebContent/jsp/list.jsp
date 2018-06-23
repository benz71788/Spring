﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
 <title>회원 리스트-list.jsp</title>
 <body>
 	<fieldset>
 		<h2><legend> 회원 리스트</h2></legend>
 		<table>
 			<tr>
 				<th>아이디</th>
 				<th>비밀번호</th>
 				<th>수정</th>
 				<th>삭제</th>
 			</tr>
 				
 			<c:forEach var="l" items="${list}">
 			<tr>
 				<td>${l.id}</td>
 				<td>${l.password}</td>
 				<td><input type="button" value="수정"
 					onclick='location.href="updateForm.net?id=${l.id}"'>
 				</td>
 				<td><input type="button" value="삭제"
 					onclick='location.href="delete.net?id=${l.id}"'>
 				</td>
 			</tr>
 			</c:forEach>
 		</table>
 		<div><a href="main.net">메인으로</a></div>
 	</fieldset>
 </body>
</html>




