<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:choose>
		<c:when test="${empty id}">
		<script>
			location.href = 'login.net';
		</script>
		</c:when>
		<c:when test="${id =='admin'}">
			<span>관리자 ${id}님 환영합니다.</span>
			(<a href="main.net">메인페이지로 이동</a>)
		</c:when>
		<c:otherwise>
			<span>일반 회원 ${id}님 환영합니다.</span>
		</c:otherwise>
	</c:choose>

	<span>
	
	(<a href="member_update.net?id=${id}">정보수정</a>)
	(<a href="logout.net">로그아웃</a>)</span>
<hr>