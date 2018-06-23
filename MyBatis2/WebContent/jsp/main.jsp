<%-- server.xml을 아래 처럼 변경하세요
     <Connector URIEncoding="UTF-8" connectionTimeout="20000" 
      port="8088" protocol="HTTP/1.1" redirectPort="8443"/>
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
  <title>main.jsp</title> 
  
</head>
<body>
<c:if test='${empty sessionScope.id }'>
	<script>
		location.href="loginForm.net";
	</script>
</c:if>

<h2>로그인 되었습니다.</h2> <a href="logout.net">로그아웃</a>
<hr>
<c:if test="${sessionScope.id=='admin' }">
	<c:out value="관리자모드!"/><br>
	<a href="list.net">회원명단</a><br>
</c:if>
</body>
</html>



