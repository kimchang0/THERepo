<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>TheSignup</title>
</head>
<body>
	<input type="text" id="text" />
	<button id="btns">hello</button>
</body>
<script>
	var doCheck = 'N'
	
		$("#btns").click(function() {
			
			var id = document.getElementById("text").value;
			
			console.log(id);
			
			$.ajax({ 
				  url: '/hello/hello.do?id=' + id,
				  type: 'get', 
				  success: function(res){ 
				      if(res) {
				    	  document.getElementById("btns").innerHTML = "<h1>안녕!</h1>";
				      }
				  } 
				}); 
		}
				)

</script>
</html>