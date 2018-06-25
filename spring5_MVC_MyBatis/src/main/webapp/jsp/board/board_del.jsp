<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div id="bbswrite_wrap">
		<h2 class="bbswrite_title">게시물 삭제</h2>
		<form name="deleteForm" action="./board_del_ok.nhn" method="post">
			<input type="hidden" name="board_num" value="${bcont.board_num}">
			<input type="hidden" name="page" value="${page}">
			<table id="boarddel_t">
				<tr>
					<th>글 비밀번호</th>
					<td>
						<input type="password" name="board_pass" id="pwd">
					</td>
				</tr>
			</table>
			<div id="boarddel_menu">
				<input type="submit" value="삭제">
				<input type="reset" value="취소" onclick="$('#pwd').focus();">
			</div>
		</form>
	</div>
</body>
</html>