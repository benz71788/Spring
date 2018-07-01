<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("sender", "hello");
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div id="priceInfo">0원</div>
	<div>
		<input type="text" name="sender" id="sender" value="hello" style="display: none;">
		가격 : <input type="text" name="priceinput" id="priceinput"><br>
		문자 : <input type="text" name="messageinput" id="messageinput">
	</div>

	<div>
		<button type="button" onclick="openSocket();">Open</button>
		<button type="button" onclick="send();">Send</button>
		<button type="button" onclick="closeSocket();">Close</button>
	</div>
	<!-- Server response get written here -->
	<div id="messages"></div>
	<!-- websocket javascript -->
	<script type="text/javascript">
		var ws;
		var beforePrice = 0;
		var regNumber = /^[0-9]*$/;
		var message=document.getElementById("message");
		var priceInfo = document.getElementById("priceInfo");
		var messageinput = document.getElementById("messageinput");
		var priceinput = document.getElementById("priceinput");
		
		function openSocket(){
			if(ws !== undefined && ws.readyState !== WebSocket.CLOSED){
				writeResponse("WebSocket is already opened.");
				return;
			}
			//웹소켓 객체 만드는 코드
			ws = new WebSocket("ws://localhost:8181/chatting/echo.do");
			
			ws.onopen = function(event){
				if(event.data === undefined){
					return;
				}
				
				writeResponse(event.data);
			}
			
			ws.onmessage = function(event){
				if(event.data.indexOf(":") > -1){
					if(!regNumber.test(Number(event.data.split(":")[1]))){
						writeResponse(event.data);
					} else {
						if(event.data.split(":")[1] > beforePrice){
							writeResponse(event.data.split(":")[0] + " : " + event.data.split(":")[1] + "원을 응찰했습니다.");
							writePrice(event.data.split(":")[1] + "원");
							beforePrice = event.data.split(":")[1];
							
						}
					}
				} else{
					writeResponse(event.data);
				}
			}
			
			ws.onclose = function(event){
				writeResponse("Connection closed");
			}
		}
		
		function send(){
			if(messageinput != null || messageinput != ""){
				var text = document.getElementById("messageinput").value + "," + document.getElementById("sender").value;
				ws.send(text);
				text="";
			} 
			
			if(priceinput != null || priceinput != ""){
				var price = document.getElementById("priceinput").value + "," + document.getElementById("sender").value;
				ws.send(price);
				price="";
			}
			
			
		}
		
		function closeSocket(){
			ws.close();
		}
		
		function writeResponse(text){
			messages.innerHTML += "<br/>" + text;
		}
		
		function writePrice(text){
			priceInfo.innerHTML = text;
		}
	</script>
</body>
</html>