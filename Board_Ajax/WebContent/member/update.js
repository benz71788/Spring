$(document).ready(function(){


	   $("form").submit(function (){     
	    	     
	     //비밀번호 공백 유효성 검사
	     if($.trim($("#pass").val()) == ""){
	      alert("비밀번호를 입력 하세요");
	      $("#pass").focus();
	      return false;
	     }
	     
	     //이름 공백 유효성 검사
	     if($.trim($("#name").val()) == ""){
	          alert("이름을 입력 하세요");
	          $("#name").focus();
	          return false;
	      }
	     
	     //라디오 버튼 선택 유효성 검사
	     /*if($("#gender1")is(":checked") == false &&
	     $("#gender2").is(":checked") == false){
	        alert("남, 여 중에서 1개를 선택하세요");
	          return false;}*/
	     
	     if($('input:radio:checked').length==0){
	    	 alert("남자, 여자중에서 선택하세요");
	    	 return false;
	     }
	     
	     //나이 공백 유효성 검사
	     if($.trim($("#age").val()) == ""){
	          alert("나이를 입력 하세요");
	          $("#age").focus();
	          return false;
	      }
	     if(!$.isNumeric($("#age").val())){ //숫자가 아닌 경우
	      alert("나이는 숫자만 입력 가능합니다.");
	      $("#age").val("");
	      $("#age").focus();
	      return false;      
	     }
	     if($.trim($("#age").val().length) >=4){
	          alert("나이는 최대 3자리 입니다.");
	          $("#age").val("");
	          $("#age").focus();
	          return false;
	      }
	     //첫자리에 0을 제외한 숫자가 오고[0-9]가 1개 또는 2개 오도록 합니다.
	     var pattern = /^[1-9][0-9]{1,2}$/;
	     				
	     if(!pattern.test($.trim($("#age").val()))){
	    	 alert("2-3 자리의 숫자를 입력하세요[0으로 시작하지마세요]");
	    	 $("#age").val("");
	         $("#age").focus();
	         return false;
	     }
	   
	     //email 공백 유효성 검사
	     if($.trim($("#email").val()) ==""){
	      alert("E-mail을 입력하세요");
	      $("#email").focus();
	      return false;
	     }

	   });  
	  
 });
  