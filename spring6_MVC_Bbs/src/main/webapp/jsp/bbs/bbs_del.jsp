<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/bbs.css" rel="stylesheet">
<script src="./resources/js/jquery-3.3.1.js"></script>
<script src="./resources/js/bbs.js"></script>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">�Խù� ����</h2>
		<form name="deleteForm" action="./bbs_del_ok.nhn" method="post">
			<input type="hidden" name="bbs_num" value="${bcont.bbs_num}">
			<input type="hidden" name="page" value="${page}">
			<table id="bbsdel_t">
				<tr>
					<th>�� ��й�ȣ</th>
					<td>
						<input type="password" name="bbs_pass" id="pwd">
					</td>
				</tr>
			</table>
			<div id="bbsdel_menu">
				<input type="submit" value="����">
				<input type="reset" value="���" onclick="$('#pwd').focus();">
			</div>
		</form>
	</div>
</body>
</html>