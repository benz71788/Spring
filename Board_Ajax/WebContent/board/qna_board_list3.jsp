<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>MCV 게시판</title>
	<link href ="css/list.css" rel="stylesheet">
</head>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script src="./js/list.js"></script>
<script>
	$(function(){
	   $("#viewcount").val("${limit}").prop("selected", true);
	});	    
</script>
<body>
<%@ include file="/board/header.jsp" %>
<!--게시판 리스트 -->
<table>
<!-- 레코드가 있으면 -->
<!-- core 라이브러리 사용 -->
<c:if test="${listcount >0 }">
	<tr>
		<th colspan="3">MVC 게시판 - list</th>
		<th colspan="2"><font size=2>글 개수 : ${listcount }</font></th>
	</tr>
	<tr>
		<td width="8%" ><div>번호</div></td>
		<td width="50%" ><div>제목</div></td>
		<td width="14%" ><div>작성자</div></td>
		<td width="17%" ><div>날짜</div></td>
		<td width="11%" ><div>조회수</div></td>
	</tr>
	<c:set var="num" value="${listcount-(page-1)*10 }"/>
	<c:forEach var="b" items="${boardlist }">
	<tr>
		<td>
			<c:out value="${num }"/><%-- num 출력 --%>
			<c:set var="num" value="${num-1}"/><%-- num = num-1 --%>
		</td>
		<td>
			<div>
				<%-- 답변글 제목앞에 여백 처리 부분
					BOARD_RE_LEV, BOARD_RE_LEV, BOARD_NUM,
					BOARD_SUBJECT, BOARD_NAME, BOARD_DATE,
					BOARD_READCOUNT : property 이름--%>
				<c:if test="${b.BOARD_RE_LEV !=0}"> <%-- 답글인 경우  --%>
					<c:forEach var="a" begin="0"
					 end="${b.BOARD_RE_LEV*2}" step="1">&nbsp;
					 </c:forEach>
				</c:if>
				<c:if test="${b.BOARD_RE_LEV ==0 }"><%--원글인경우 --%>
					&nbsp; ▶
				</c:if>
				<a href="./BoardDetailAction.bo?num=${b.BOARD_NUM}">
					${b.BOARD_SUBJECT }
				</a>
			</div>
		</td>
		<td><div>${b.BOARD_NAME}</div></td>
		<td><div>${b.BOARD_DATE}</div></td>
		<td><div>${b.BOARD_READCOUNT}</div></td>
	</tr>
	</c:forEach>
</c:if>

<!-- 레코드가 없으면 -->
	<c:if test="${listcount == 0}">
		<tr>
			<td colspan="4">MVC 게시판</td>
			<td style="text-align:right">
				<font size=2>등록된 글이 없습니다.</font>
			</td>
		</tr>
	</c:if>
		
		<tr class="30 lime center btn">
			<td colspan="5">
				<c:if test="${page <=1 }">이전&nbsp;</c:if>
				<c:if test="${page>1 }">
				<a href="./BoardList.bo?page=${page-1}">이전</a>&nbsp;</c:if>
				
				<c:forEach var="a" begin="${startpage }" end="${endpage}">
					<c:if test="${a == page }">
						${a}
					</c:if>
					<c:if test="${a != page }">
						<a href="./BoardList.bo?page=${a}">${a }</a>
					</c:if>
				</c:forEach>
				<c:if test="${page>= maxpage }">
					&nbsp;다음
				</c:if>
				<c:if test="${page <maxpage }">
					<a href="./BoardList.bo?page=${page+1 }">&nbsp; 다음</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" style="text-align:center">
				<a href="./BoardWrite.bo">[글쓰기]</a>
				<select id="viewcount">
					<option value="3">3줄 보기</option>
					<option value="5">5줄 보기</option>
					<option value="7">7줄 보기</option>
					<option value="10" selected>10줄 보기</option>
				</select>
			</td>
		</tr>
</table>
</body>
</html>