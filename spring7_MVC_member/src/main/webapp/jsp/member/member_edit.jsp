<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/admin.css" rel="stylesheet">
<script src="./resources/js/jquery-3.3.1.js"></script>
<script src="./resources/js/member3.js"></script>
<script>
	$(document).ready(function(){
		$("#join_tel1").val("${member.join_tel1}");
		$("#join_phone1").val("${member.join_phone1}");
		$("#mail_list").val("${member.join_maildomain}");
		
		//document.form_edit.join_tel1.value = '${member.join_tel1}'
		//document.form_edit.join_phone1.value = '${member.join_phone1}'
		//document.form_edit.mail_list.value = '${member.join_maildomain}'
	});
</script>
</head>
<body>
	<div id="join_wrap">
		<h2 class="join_title">회원가입</h2>
		<form name="form_edit" method="post" action="member_edit_ok.nhn"
			onsubmit="return check()" enctype="multipart/form-data">
		
			<!-- 이진파일을 업로드 할려면 enctype 속성을 지정 -->	
			<table id="join_t">
				<tr>
					<th>회원아이디</th>
					<td>
						<input name="join_id" id="join_id" size="14" class="input_box" value="${id}">
						
					</td>
				</tr>
				<tr>
					<th>회원비번</th>
					<td>
						<input type="password" name="join_pwd" id="join_pwd1" size="14" class="input_box">
					</td>
				</tr>
				<tr>
					<th>회원비번확인</th>
					<td>
						<input type="password" name="join_pwd2" id="join_pwd2" size="14" class="input_box">
					</td>
				</tr>
				<tr>
					<th>회원이름</th>
					<td>
						<input name="join_name" id="join_name" size="14" class="input_box" value="${member.join_name}">
					</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
						<input name="join_zip" id="join_zip" size="3" class="input_box"
							readonly onclick="post_search()" value="${member.join_zip}">
						<input type="button" value="우편번호검색" class="input_button"
							onclick="post_check()">
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input name="join_addr1" id="join_addr1" size="50" class="input_box"
							readonly onclick="post_search()" value="${member.join_addr1}">
					</td>
				</tr>
				<tr>
					<th>나머지 주소</th>
					<td>
						<input name="join_addr2" id="join_addr2" size="37" class="input_box" value="${member.join_addr2}">
					</td>
				</tr>
				<tr>
					<th>집전화번호</th>
					<td>
						<%@ include file="../../jsp/include/tel_number.jsp" %>
						<select name="join_tel1" id="join_tel1">
							<c:forEach var="t" items="${tel}">
								<option value="${t}" selected>${t}</option>
							</c:forEach>
						</select>
						-<input name="join_tel2" id="join_tel2" size="4"
							maxlength="4" class="input_box" value="${member.join_tel2}">
						-<input name="join_tel3" id="join_tel3" size="4"
							maxlength="4" class="input_box" value="${member.join_tel3}">
					</td>
				</tr>
				<tr>
					<th>휴대전화번호</th>
					<td>
						<%@ include file="../../jsp/include/phone_number.jsp" %>
						<select name="join_phone1" id="join_phone1">
							<c:forEach var="p" items="${phone}">
								<option value="${p}" selected>${p}</option>
							</c:forEach>
						</select>
						-<input name="join_phone2" id="join_phone2" size="4"
								maxlength="4" class="input_box" value="${member.join_phone2}">
						-<input name="join_phone3" id="join_phone3" size="4" 
								maxlength="4" class="input_box" value="${member.join_phone3}">
					</td>
				</tr>
				<tr>
					<th>전자우편</th>
					<td>
						<input name="join_mailid" id="join_mailid" size="10" class="input_box" value="${member.join_mailid}">
						@<input name="join_maildomain" id="join_maildomain" size="20" class="input_box"
								value="${member.join_maildomain}" readonly>
						<!-- readonly는 쓰기, 수정이 불가능하고 읽기만 가능합니다. -->
						<select name="mail_list" id="mail_list" onchange="domain_list()">
							<option value="">=이메일 선택=</option>
							<option value="daum.net">daum.net</option>
							<option value="nate.com">nate.com</option>
							<option value="naver.com">naver.com</option>
							<option value="hotmail.com">hotmail.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="0">직접입력</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>프로필사진</th>
					<td>
						<!-- <input type="file" name="uploadfile"> -->
						<label for="upfile"><img src="./resources/images/file_open.png" alt="파일열기"></label>
						<input type="file" id="upfile" name="join_profile" style="display:none;">
						<span id="filevalue">${member.join_original}</span>&nbsp;
						<span id="close"><img src="./resources/images/cancel.png"></span>
					</td>
				</tr>
			</table>
			
			<div id="join_menu">
				<input type="submit" value="수정확인" class="input_button">
				<input type="reset" value="수정취소" class="input_button"
					onclick="$('#join_id').focus();">
			</div>
		</form>
	</div>
</body>
</html>