<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/bbs.css" rel="stylesheet">
<script src="./resources/js/jquery-3.3.1.js"></script>
<script src="./resources/js/bbs.js"></script>
</head>
<body>
<!-- 게시판 리스트 -->
	<div id="bbslist_wrap">
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
		<c:forEach var="b" items="${bbslist}">
		<tr>
			<td>
				<c:out value="${num}"/>	<%-- num 출력 --%>
				<c:set var="num" value="${num-1}"/>	<%-- num = num - 1 --%>
			</td>
			<td>
				<div>
					<%-- 답변글 제목앞에 여백 처리 부분
						bbs_RE_LEV, bbs_RE_SEQ, bbs_NUM,
						bbs_SUBJECT, bbs_NAME, bbs_DATE,
						bbs_READCOUNT : property 이름 --%>
					<c:if test="${b.bbs_re_lev != 0}">
						<c:forEach var="a" begin="0"
									end="${b.bbs_re_lev * 2}" step="1">
							&nbsp;
						</c:forEach>
					</c:if>
					<c:if test="${b.bbs_re_lev == 0}">
						&nbsp;
					</c:if>
					<img src="./resources/images/AnswerLine.gif">
					
					<a href="./bbs_cont.nhn?bbs_num=${b.bbs_num}&page=${page}&state=cont">
						${b.bbs_subject}
					</a>
				</div>
			</td>
			<td>
				<div>${b.bbs_name}</div>
			</td>
			<td>
				<div>${b.bbs_date}</div>
			</td>
			<td>
				<div>${b.bbs_readcount}</div>
			</td>
		</tr>
		</c:forEach>
		<tr class="h30 lime center btn">
			<td colspan=5>
				<c:if test="${page <= 1}">
					이전&nbsp;
				</c:if>
				<c:if test="${page > 1}">
					<a href="./bbs_find_ok.nhn?page=${page-1}&find_name=${find_name}&find_field=${find_field}">이전</a>
				</c:if>
				<c:forEach var ="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page}">
						${a}
					</c:if>
					<c:if test="${a != page}">
						<a href="./bbs_find_ok.nhn?page=${a}&find_name=${find_name}&find_field=${find_field}">${a}</a>
					</c:if>
				</c:forEach>
				<c:if test="${page >= maxpage}">
					&nbsp;다음
				</c:if>
				<c:if test="${page < maxpage}">
					<a href="./bbs_find_ok.nhn?page=${page + 1}&find_name=${find_name}&find_field=${find_field}">&nbsp;다음</a>
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
					<a href="./bbs_write.nhn">[글쓰기]</a>
				</td>
			</tr>
			<tr>
				<td>
					<select name="line_size">
						<option value=""></option>
					</select>
				</td>
			</tr>
	</table>
	<div id="bbsfind">
		<script src="./resources/js/jquery.js"></script>
		<script>
			function find_check(){
				if($.trim($("#find_name").val()) == ""){
					alert("검색어를 입력하세요!");
					$("#find_name").val("").focus();
					return false;
				}
			}
		</script>
		<form method="get" action="bbs_find_ok.nhn"
			onsubmit="return find_check()">
			<table>
				<tr>
					<th>
						<select name="find_field">
							<option value="bbs_name">작성자</option>
							<option value="bbs_subject">제목</option>
							<option value="bbs_content">내용</option>
						</select>
					</th>
					<td>
						<input name="find_name" id="find_name" size="18"
							class="input_box">
						<input type="submit" value="검색" class="input_button">
					</td>
				</tr>
			</table>
		</form>
	</div>
	</div>
</body>
</html>