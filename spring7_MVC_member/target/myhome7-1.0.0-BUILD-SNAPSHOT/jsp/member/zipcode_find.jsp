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
		
		//도을 입력하지 않을 경우 체크 합니다.
		$("#zipform").submit(function(){
			if($("#inputdong").val() == ""){
				alert("도로를 입력해 주세요!");
				$("#inputdong").focus();
				return false;
			}
		});
	});
	
</script>
</head>
<body>
	<form method="post" action="zipcode_find_ok.nhn" id="zipform">
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
			
			<!-- 동을 입력했다면 실행되는 JSTL if문 -->
			<c:if test="${!empty dong}">
				<!-- 검색결과 주소값이 있을 경우만 실행되는 JSTL if문 -->
				<c:if test="${!empty zipcodelist}">
				<tr>
					<td colspan=2 class="center30">
						<select name="post_list" id="post_list">
							<option value="">---주소를 선택하세요---</option>
							<!-- items 속성에는 검색 결과의 주소값을 담고 있는 키값을 적습니다.
								 addr2 변수에는 주소값을 받아서 저장합니다. -->
								<c:forEach var="addr2" items="${zipcodelist}">
									<%-- addr2.zipcode에는 ZipcodeBean 클래스의 getZipcode()메서드에서 구해온 우편번호,
										 addr2.addr에는 ZipcodeBean 클래스의 getAddr()메서드를 가져와 시도 구군 도로를 합친
										 변수 totaladdr에 저장합니다. 우편번호 시도 구군 도로가 저정됩니다.
										 예)[06267] 서울특별시 강남구 강남대로 238~246 --%>
									<c:set var="totaladdr" value="${addr2.zipcode}${addr2.addr}"/>
									
									<%-- 결과 받아온 대로 option을 만듭니다. --%>
									<option value="${totaladdr}">[${addr2.zipcode}]&nbsp;${addr2.addr}</option>
								</c:forEach>
						</select>
					</td>
				</tr>
				</c:if>
				<!-- JSTL에서 검색 주소값이 없을 경우 실행되는 if문, -->
				<c:if test="${empty zipcodelist}">
					<tr>
						<td colspan=2 class="center30">
							<font color="#466d1b"><span class="style1">검색 결과가 없습니다.</span></font>
						</td>
					</tr>
				</c:if>
			</c:if>
			<tr>
				<td class="bar" colspan="2" height="3"></td>
			</tr>
		</table>
	</form>
</body>
</html>