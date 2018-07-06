<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="./resources/css/zipcode2.css" rel="stylesheet">
<script src="./resources/js/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function(){
		$("#inputdong").focus();
		
		$("#post_list").change(function(){
			var zip = $("#post_list").val();
			var zip1 = zip.substring(0, 5);
			var addr2 = zip.substring(5, (zip.length));	//5번째부터 끝문자까지 추출 - 나머지 주소
			
			// id 뒤에 opener.document 또는 parent.document를 사용하여 부모창의 문서 객체를 제어합니다.
			$("#join_zip", opener.document).val(zip1);
			$("#join_addr1", opener.document).val(addr2);
			
			/* 팝업 윈도우 창을 닫아줍니다. */
			window.close();
		});
		
		$("#zipform").submit(function(){
			
			if($("#inputdong").val() == ""){
				alert("도로를 입력해 주세요!");
				$("#inputdong").focus();
				return false;
			}
			
			$.ajax({
				url : "zipcode_find_ok.nhn",
				data : $(this).serialize(),
				type: "POST",
				dataType : "json",
				cache : false,
				headers : {"cache-control" : "no-cache",
					"pragma" : "no-cache"},
				success : function(result){
					$('.center30').empty();
					var output = '';
					if(result.length > 0){
						output += '<select name="post_list" id="post_list">';
						output += '<option value="">---주소를 선택해주세요---</option>';
						$(result).each(function(index, item){
							output += '<option value="' + item.zipcode + item.addr + '">';
							output += '[' + item.zipcode + ']&nbsp;' + item.addr;
							output += '</option>';
							
						});
						output += '</select>';
						
					} else {
						output += '<font color="#466d1b"><span class="style1">검색 결과가 없습니다.</span></font>';
					}
					$('.center30').append(output);
				},
				error : function(data){
					alert("오류 발생")
				}
			});
			return false;
		});
	});
</script>
</head>
<body>
	<form method="post" id="zipform">
		<table class="bg">
			<tr>
				<td colspan=2 class="center">
					<input type="image" src="./resources/images/ZipCode_img01.gif"
						width="413" height="58">
				</td>
			</tr>
			<tr>
				<td colspan=2 class="center">
					<strong><font color="#466d1b">
						<span class="style1">[거주지의 대로명을 입력하고 '찾기'버튼을 누르세요!!!]</span>
					</font></strong>
				</td>
			</tr>
			<tr height="30">
				<td class="right">
					<input type="text" name="dong" id="inputdong" size="10"
						maxlength="10" height="19">&nbsp;</td>			
				<td class="image02">
					<input type="image" src="./resources/images/m-i02.gif"
						width="69" height="19">
				</td>
			</tr>
			
				<tr>
					<td colspan=2 class="center30">
					</td>
				</tr>
			<tr>
				<td class="bar" colspan="2" height="3"></td>
			</tr>
		</table>
	</form>
</body>
</html>