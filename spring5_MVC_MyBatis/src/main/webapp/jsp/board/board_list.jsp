<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/list.css" rel="stylesheet">
</head>
<body>
<!-- 게시판 리스트 -->
	<table>
		<tr>
			<th colspan="3">MVC 게시판 - list</th>
			<th colspan="2">
				<font size=2>글 개수 : ${listcount}</font>
			</th>
		</tr>
		<tr>
			<th width="8%"><div>번호</div></th>
			<th width="50%"><div>제목</div></th>
			<th width="14%"><div>작성자</div></th>
			<th width="17%"><div>날짜</div></th>
			<th width="11%"><div>조회수</div></th>
		</tr>
		<c:set var="num" value="${listcount-(page-1) * 10}"/>
		<c:forEach var="b" items="${boardlist}">
		<tr>
			<td>
				<c:out value="${num}"/>	<%-- num 출력 --%>
				<c:set var="num" value="${num-1}"/>	<%-- num = num - 1 --%>
			</td>
			<td>
				<div>
					<%-- 답변글 제목앞에 여백 처리 부분
						BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_NUM,
						BOARD_SUBJECT, BOARD_NAME, BOARD_DATE,
						BOARD_READCOUNT : property 이름 --%>
					<c:if test="${b.board_re_lev != 0}">
						<c:forEach var="a" begin="0"
									end="${b.board_re_lev * 2}" step="1">
							&nbsp;
						</c:forEach>
					</c:if>
					<c:if test="${b.board_re_lev == 0}">
						&nbsp;
					</c:if>
					<img src="./resources/images/AnswerLine.gif">
					
					<a href="./board_cont.nhn?board_num=${b.board_num}&page=${page}&state=cont">
						${b.board_subject}
					</a>
				</div>
			</td>
			<td>
				<div>${b.board_name}</div>
			</td>
			<td>
				<div>${b.board_date}</div>
			</td>
			<td>
				<div>${b.board_readcount}</div>
			</td>
		</tr>
		</c:forEach>
		<tr class="h30 lime center btn">
			<td colspan=5>
				<c:if test="${page <= 1}">
					이전&nbsp;
				</c:if>
				<c:if test="${page > 1}">
					<a href="./board_list.nhn?page=${page-1}">이전</a>
				</c:if>
				<c:forEach var ="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page}">
						${a}
					</c:if>
					<c:if test="${a != page}">
						<a href="./board_list.nhn?page=${a}">${a}</a>
					</c:if>
				</c:forEach>
				<c:if test="${page >= maxpage}">
					&nbsp;다음
				</c:if>
				<c:if test="${page < maxpage}">
					<a href="./board_list.nhn?page=${page + 1}">&nbsp;다음</a>
				</c:if>
			</td>
		</tr>
			<!-- 레코드가 없으면 -->
		<c:if test="${listcount == 0}">
			<tr>
				<td colspan="3">MVC 게시판</td>
				<td style="text-align:right">
					<font size=2>등록된 글이 없습니다.	</font></td>
			</tr>
		</c:if>
			<tr>
				<td colspan="5" style="text-align:right">
					<a href="./board_write.nhn">[글쓰기]</a>
				</td>
			</tr>
	</table>
</body>
</html>