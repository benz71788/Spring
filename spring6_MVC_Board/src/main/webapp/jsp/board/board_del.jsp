<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/board.css" rel="stylesheet">
<script src="./resources/js/jquery-3.3.1.js"></script>
<script src="./resources/js/board.js"></script>
</head>
<body>
<%@ include file="../member/header.jsp" %>
	<div id="boardwrite_wrap">
		<h2 class="boardwrite_title">�Խù� ����</h2>
		<form name="deleteForm" action="./board_del_ok.nhn" method="post">
			<input type="hidden" name="board_num" value="${bcont.board_num}">
			<input type="hidden" name="page" value="${page}">
			<table id="boarddel_t">
				<tr>
					<th>�� ��й�ȣ</th>
					<td>
						<input type="password" name="board_pass" id="pwd">
					</td>
				</tr>
			</table>
			<div id="boarddel_menu">
				<input type="submit" value="����">
				<input type="reset" value="���" onclick="$('#pwd').focus();">
			</div>
		</form>
	</div>
</body>
</html>