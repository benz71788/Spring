<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<form name=myform method=post action="./login_ok3.do">
		<table border=1>
			<tr>
				<td>ID</td>
				<td><input type=text name=id></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type=password name=pass></td>
			</tr>
			<tr>
				<td colspan=2 style="background:lightgreen">
					<input type="submit" value="LOGIN">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>