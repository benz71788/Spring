$(document).ready(function(){
  $("#viewcount").change(function(){
	   var limit = $('#viewcount option:selected').val();
	   $.ajax({
	    type : "POST",
	    data : {"limit" : limit, "state":"ajax"},
	    url : "./BoardList.bo",
	    cache: false, 
	    headers : {"cache-control" : "no-cache", 
	    	       "pragma" : "no-cache"},
	    success : function(data){
	        $("table:first").empty().prepend(data);
	    },
	    error : function(){
	     alert("에러");
	    }
	   })//ajax end
	  });//change end
})//ready end