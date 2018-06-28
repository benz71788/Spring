<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<title>페이지 뷰</title>
</head>
<link href ="css/form.css" rel="stylesheet">
<body>
<%@ include file="/board/header.jsp" %>
	<form name="boardform">
		<table>
			<tr class="center">
				<th colspan="2">MVC 게시판-view 페이지</th>
			</tr>
			<tr>
				<td><div>글쓴이</div></td>
				<td><div>${boarddata.BOARD_NAME}</div></td>
			</tr>
			<tr>
				<td><div>제  목</div></td>
				<td><div>${boarddata.BOARD_SUBJECT}</div></td>
			</tr>
			<tr>
				<td><div>내  용</div></td>
				<td><div>${boarddata.BOARD_CONTENT}</div></td>
			</tr>
			<tr>
				<td><div>파일 첨부</div></td>
				<c:if test="${!empty boarddata.BOARD_FILE }">
				<td>
					<img src="image/down.png" width="10px">
					<a href="./BoardFileDown.bo?filename=${boarddata.BOARD_FILE }">${boarddata.BOARD_FILE }</a>
				</td>
				</c:if>
			</tr>
			<tr class="center">
				<td colspan="2" class="center">
					<a href="./BoardReplyView.bo?num=${boarddata.BOARD_NUM}">
					답변</a>&nbsp;&nbsp;
					
					<c:if test="${boarddata.BOARD_NAME == id || id == 'admin'}">
					<a href="./BoardModifyView.bo?num=${boarddata.BOARD_NUM}">
					수정</a>&nbsp;&nbsp;
					<a href="./BoardDelete.bo?num=${boarddata.BOARD_NUM}">
					삭제</a>&nbsp;&nbsp;
					</c:if>
					
					<a href="./BoardList.bo">
					목록</a>
					
				</td>
			</tr>
		</table>		
	</form>
</body>
</html>