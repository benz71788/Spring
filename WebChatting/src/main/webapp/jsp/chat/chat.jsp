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
		가격 : <input type="text" name="messageinput" id="messageinput">
	</div>

	<div>
		<button type="button" onclick="openSocket();" class="openbtn">Open</button>
		<button type="button" onclick="send();" class="sendbtn">Send</button>
		<button type="button" onclick="closeSocket();" class="closebtn">Close</button>
	</div>
	<!-- Server response get written here -->
	<div id="messages"></div>
<script src="./resources/js/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function(){
		$(".sendbtn").click(function(){
			$("#messageinput").val("");
		})
	})
</script>
	<!-- websocket javascript -->
<script type="text/javascript">
	var ws;
	var beforePrice = 0;
	var regNumber = /^[0-9]*$/;
	var message=document.getElementById("message");
	var messageinput = document.getElementById("messageinput");
	var priceinput = document.getElementById("priceinput");
	;
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
			var messageinfo = event.data.split(":")
			if(event.data.indexOf(":") > -1){
				if(regNumber.test(Number(messageinfo[1])) && messageinfo[1] > beforePrice){
					writeResponse(messageinfo[0] + " : " + messageinfo[1] + "원을 응찰했습니다.");
					writePrice(messageinfo[1] + "원");
					beforePrice = messageinfo[1];
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