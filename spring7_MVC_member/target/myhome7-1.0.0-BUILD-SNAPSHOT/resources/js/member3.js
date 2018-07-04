var checkconfirm = false;
var checkid = "";

function check(){
	if($.trim($("#join_id").val()) == ""){
		alert("회원아이디를 입력하세요!");
		$("#join_id").val("").focus();
		return false;
	}
	
	if($.trim($("#join_pwd1").val()) == ""){
		alert("회원비번을 입력하세요!");
		$("#join_pwd1").val("").focus();
		return false;
	}
	
	if($.trim($("#join_pwd2").val()) == ""){
		alert("회원비번확인을 입력하세요!")
		$("#join_pwd2").val("").focus();
		return false;
	}
	
	if($.trim($("#join_pwd1").val()) != $.trim($("#join_pwd2").val())){
		//!=같지 않다 연산. 비번이 다른 경우
		alert("비번이 다릅니다!");
		$("#join_pwd1").val("");
		$("#join_pwd2").val("");
		$("#join_pwd1").focus();
		return false;
	}
	
	if($.trim($("#join_name").val()) == ""){
		alert("회원이름을 입력하세요!");
		$("#join_name").val("").focus();
		return false;
	}
	
	if($.trim($("#join_zip").val()) == ""){
		alert("우편번호를 입력하세요!");
		$("#join_zip").val("").focus();
		return false;
	}
}

function post_check(){
	window.open("zipcode_find.nhn", "우편번호검색", 
			"width=420, height=500, scrollbars=yes");
	//폭이 420이고 높이가 200, 스크롤바가 생성되는 새로운 공지창을 만듬
}

function post_search(){
	alert("우편번호 검색 버튼을 클릭하세요");
}

function domain_list(){
	/*리스트에서 직접입력을 선택했을때*/
	if($("#mail_list").val() == "0"){
		
		//@뒤의 도메인입력만을 공백처리
		$("#join_maildomain").val("");
		
		//@뒤의 도메인입력란을 쓰기 기능
		$("#join_maildomain").attr("readOnly", false);
		
		//도메인입력란으로 입력대기상태
		$("#join_maildomain").focus();
	} else {
		//리스트목록을 선택했을때
		$("#join_maildomain").val($("#mail_list").val());
		$("#join_maildomain").attr("readOnly", true);
	}
}

function fileshow(){
	if($("#filevalue").text() == ""){
		$('#close').css('display', "none");
	} else {
		$('#close').css('display', "inline-block");
	}
	
}

$(document).ready(function(){
	fileshow();
	$('#upfile').change(function(){
		$('#filevalue').text('');
		//$(this).val() =>	C:\fakepath\image1.png
		var inputfile = $(this).val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length - 1]);
		fileshow();
	})
	
	$('#close').click(function(){
		$('#filevalue').text('');
		$('#upfile').val("");
		$(this).css('display', 'none');
		$('#join_original').remove();
	})
	
})

