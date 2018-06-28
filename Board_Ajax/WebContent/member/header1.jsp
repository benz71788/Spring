<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header">
	<c:if test="${empty id }">
		<script>
			location.href="login.net";
		</script>
	</c:if>
	<c:if test="${!empty id }">
		<c:if test="${id=='admin' }">
			<span>관리자 ${id} 님 환영합니다.</span>
		</c:if>
		<c:if test="${id!='admin'}">
			<span>일반회원 ${id } 님 환영합니다. </span>
		</c:if>
	</c:if>
</div>
<span>(<a href="member_update.net">정보수정</a>)
(<a href="logout.net">로그아웃</a>)</span>
<hr>