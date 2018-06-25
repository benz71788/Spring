<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/bbs.css" rel="stylesheet">
<script src="./resources/js/jquery-3.3.1.js"></script>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">게시판 입력폼</h2>
		<form method="post" action="./board_edit_ok.nhn">
		<input type="hidden" name="board_num" value="${bcont.board_num}">
		<input type="hidden" name="page" value="${page}">
			<table id="bbswrite_t">
				<tr>
					<th>글쓴이</th>
					<td>
						<input type="text" name="board_name" id="board_name" 
							size="14" class="input_box" value="${bcont.board_name}" readonly>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="board_pass" id="board_pass" 
							size="14" class="input_box">
					</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>
						<input type="text" name="board_subject" id="board_subject" 
							size="40" class="input_box" value="${bcont.board_subject}">
					</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td>
						<textarea name="board_content" id="board_content" rows="8" 
							cols="50" class="input_box">${bcont.board_content}</textarea>
					</td>
				</tr>
			</table>
			
			<div id="bbswrite_menu">
				<input type="submit" value="등록" class="input_button">
				<input type="reset" value="취소" class="input_button" onclick="$('#board_name').focus();">
			</div>
		</form>
	</div>
</body>
</html>