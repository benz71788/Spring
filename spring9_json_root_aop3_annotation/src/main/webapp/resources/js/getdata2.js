	$(document).ready(function(){
		//데이터를 보여주는 함수
		
		// #output 내부의 내용물을 제거합니다.
		$('#output').empty();
		function selectData(url, senddata) {
			//Ajax를 수행합니다.
			$.ajax({
				url : url,
				data : senddata,
				dataType : "json",
				cache : false,
				headers : {"cache-control" : "no-cache",
					"pragma" : "no-cache"},
				success : function(responsedata){
					$(responsedata).each(function(index, item){
						var output = '';
						output += "<tr>"
						output += '	<td>' + item.id	+ '</td>';
						output += '	<td>' + item.name	+ '</td>';
						output += '	<td>' + item.modelnumber + '</td>';
						output += '	<td>' + item.series	+ '</td>';
						output += '</tr>';
						$('#output').append(output);
					});
				},
				error : function(data){
					alert("오류 발생");
				}
			});
			
		}	//function end
		// 초기 화면에 데이터를 표시합니다.
		selectData("./jsontest3", null);
		
		$('#insert_form').submit(function(event){
			if($("#name").val() == ""){
				alert("name을 입력하세요");
				return false;
			}
			
			if($("#modelnumber").val() == ""){
				alert("modelnumber를 입력하세요");
				return false;
			}
			
			if($("#series").val() == ""){
				alert("series를 입력하세요");
				return false;
			}
			
			//전송할 데이터를 직렬화 합니다.
			//입력 양식에 입력한 데이터를 쿼리 스트림으로 바꿉니다.
			//name=" "&modelnumber=" "&series=" "
			var senddata = $(this).serialize();
			
			selectData('./jsontest2', senddata);
			
			event.preventDefault();
		})
	})	//ready end