<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/bbs.css" rel="stylesheet">
</head>
<body>
	<div id="bbscont_wrap" style="width:600px">
		<h2 class="bbscont_title" style="width:370px">
			게시물 내용보기</h2>
		<table id="bbscont_t">
			<tr>
				<td>글쓴이</td>
				<td>${bcont.bbs_name}</td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td>${bcont.bbs_subject}</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<%-- <pre>태그로 입력한 그대로 출력되게 합니다. 엔터도 잘 나옵니다. --%>
					<%-- <pre>${bcont.bbs_content} --%>
					<textarea rows="8" cols="50">${bcont.bbs_content}</textarea>
				</td>
			</tr>
		</table>
		<div id="bbscont_menu" style="margin-left:200px">
			<input type="button" value="수정" class="input_button"
				onclick="location='bbs_cont.nhn?bbs_num=${bcont.bbs_num}&page=${page}&state=edit'">
			
			<input type="button" value="삭제" class="input_button"
				onclick="location='bbs_cont.nhn?bbs_num=${bcont.bbs_num}&page=${page}&state=delete'">
			
			<input type="button" value="답변" class="input_button"
				onclick="location='bbs_cont.nhn?bbs_num=${bcont.bbs_num}&page=${page}&state=reply'">
				
			<input type="button" value="목록" class="input_button"
				onclick="location='bbs_list.nhn?bbs_num=${bcont.bbs_num}&page=${page}'">
		</div>
	</div>
</body>
</html>