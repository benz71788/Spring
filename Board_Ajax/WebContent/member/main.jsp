<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<title>메인 페이지</title>
</head>

<body>
	<%@ include file="/board/header.jsp" %>
	<c:if test="${id=='admin' }">
	<a href="member_list.net">관리자 모드 접속(회원 목록 보기)</a>
	</c:if>
</body>
</html>