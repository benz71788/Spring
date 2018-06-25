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
		<h2 class="bbswrite_title">�Խ��� �Է���</h2>
		<form method="post" action="./board_edit_ok.nhn">
		<input type="hidden" name="board_num" value="${bcont.board_num}">
		<input type="hidden" name="page" value="${page}">
			<table id="bbswrite_t">
				<tr>
					<th>�۾���</th>
					<td>
						<input type="text" name="board_name" id="board_name" 
							size="14" class="input_box" value="${bcont.board_name}" readonly>
					</td>
				</tr>
				<tr>
					<th>��й�ȣ</th>
					<td>
						<input type="password" name="board_pass" id="board_pass" 
							size="14" class="input_box">
					</td>
				</tr>
				<tr>
					<th>������</th>
					<td>
						<input type="text" name="board_subject" id="board_subject" 
							size="40" class="input_box" value="${bcont.board_subject}">
					</td>
				</tr>
				<tr>
					<th>�۳���</th>
					<td>
						<textarea name="board_content" id="board_content" rows="8" 
							cols="50" class="input_box">${bcont.board_content}</textarea>
					</td>
				</tr>
			</table>
			
			<div id="bbswrite_menu">
				<input type="submit" value="���" class="input_button">
				<input type="reset" value="���" class="input_button" onclick="$('#board_name').focus();">
			</div>
		</form>
	</div>
</body>
</html>