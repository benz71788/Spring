<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function(){
		var data_check = -1; //아이디 중복 확인 버튼 클릭 유무를 확인하기 위한 변수
							//아이디 존재하여 사용 불가능을 의미
		var sid, sid2;
		$("#message").css('diplay', 'none');//영역을 숨깁니다.
		$("#idcheck").click(function(){
			if($("input[name=id]").val()==''){
				alert('아이디를 입력하세요');
				$("input[name=id]").focus();
				return false;
			}
			sid=$("input[name=id]").val();
			$.ajax({
				type : "GET",
				url : "IdcheckAjax.net",
				data : {"id" : $("input[name=id]").val()},
				success : function(response){
					data_check = response;
					$("#message").empty();
					$("#message").show();
					if(response ==1){
						$("#message").append("사용 가능한 아이디 입니다.");
						$("#message").css('color','red');
					}else{
						$("#message").append("중복된 아이디 입니다.");
						$("#message").css('color','red');
						$("input[name=id]").val('');
						$("input[name=id]").focus();
					}
				},
					error:function(){
						alert("data error2");
					}
			})//ajax end
		})//click end

	$('form').submit(function(){
		sid2 = $("input[name=id]").val();//사용 가능한 아이디인데 변경한 경우
		//중복체크 후 사용 가능한 아이디락 메시지 나왔지만 아이디 변경하고 중복 체크하지 않은 경우
		if(data_check == -1 || sid != sid2){
			alert("ID 중복 체크 하세요");
			return false;
		}
		if(!$.isNumeric($("input[name='age']").val())){
			alert("나이는 숫자로 입력하세요");
			$("input[name='age']").val('');
			$("input[name='age']").focus();
			return false;
		}
	})//submit end
})//ready end
	
</script>
</head>
<script src = "http://code.jquery.com/jquery-3.3.1.js"></script>
	<script src = "./member/join.js"></script>
	<link href ="css/join.css" rel="stylesheet">
<body>
	<form name="joinform" action="joinProcess.net" method="post">
	<h1>회원가입 페이지</h1>
	<hr>
	<b>아이디&nbsp;<input type="button" id="idcheck" value="중복 검사" required>
	<b id="message"></b></b>
	<input type="text" name="id" id="id" placeholder="Enter id" required><br>
	<b>비밀번호</b>
	<input type="password" name="pass" id="pass" placeholder="Enter password" required><br>
	<b>이름</b>
	<input type="text" name ="name" id="name" placeholder="Enter name" required><br>
	<b>나이</b>
	<input type="text" name="age" id="age" maxlength=2 placeholder="Enter age"><br>
	<b>성별</b>
	<div>
		<input type="radio" name="gender" value="남" checked><span>남자</span>
		<input type="radio" name="gender" value="여" ><span>여자</span>
	</div><br>
	<b>이메일 주소</b>
	<input type="text" name="email" id="email" placeholder="Enter email" ><br><br>
	<div class="clearfix">
		<button type="submit" class="submitbtn">회원가입</button>
		<button type="reset" class="cancelbtn">다시작성</button>
	</div>
	</form>
</body>
</html>