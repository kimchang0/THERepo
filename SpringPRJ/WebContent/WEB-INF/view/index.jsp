<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	table, th, td{
		border : 1px solid black;
	}
</style>
<script type="text/javascript">
	function innerHTMLTest() {
		
			 var randValNode = document.getElementById("rand_val");
			 randValNode.innerHTML = "<b><font color='blue'>"+Math.random()+"</font></b>";
	}

</script>
</head>
<body>
<!-- top start -->
	<div>
	
		<%@ include file="/WEB-INF/view/The/Top.jsp" %>
		
	</div>
	
	<div id="rand_val">Let's generate random Value</div>
	<button onclick="innerHTMLTest()">Generate</button>
	
	<!-- top end -->
	
Today Highlight English
</body>
</html>