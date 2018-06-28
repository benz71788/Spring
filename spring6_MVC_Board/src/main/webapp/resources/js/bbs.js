function check(){
	if($.trim($("#bbs_name").val()) == ""){
		alert("글쓴이를 입력하세요!");
		$("#bbs_name").val("").focus();
		return false;
	}
	
	if($.trim($('#bbs_pass').val()) == ""){
		alert("비밀번호를 입력하세요!");
		$("#bbs_pass").val("").focus();
		return false;
	}
	
	if($.trim($('#bbs_subject').val()) == ""){
		alert("글제목을 입력하세요!");
		$("#bbs_subject").val("").focus();
		return false;
	}
	
	if($.trim($('#bbs_content').val()) == ""){
		alert("글내용을 입력하세요!");
		$("#bbs_content").val("").focus();
		return false;
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
		$(this).css('display', 'none');
	})
	
})
	