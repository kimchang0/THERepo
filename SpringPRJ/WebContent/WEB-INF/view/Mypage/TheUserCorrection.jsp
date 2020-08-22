<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="poly.dto.UserDTO" %>
<%@ page import="static poly.util.CmmUtil.nvl" %>
 <%
 
 	UserDTO res = (UserDTO)request.getAttribute("res");
 
 %>
<!DOCTYPE html>
<html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	Email: <span><%=nvl(res.getUser_email()) %></span> <br>
	Gender: <span id="gender"><%=nvl(res.getUser_gender()) %></span> <br>
	Age: <span><%=nvl(res.getUser_age()) %>대</span>
	<form action="/Mypage/TheUserCorrectionDo.do">
		<button type="submit">수정하기</button> <button type="button" onclick="location.href='/Setting/TheMypage.do'">취소</button>
	</form>
</body>

<script>


const gender = $("#gender").text();
	switch(gender){
	case 'm':
	$("#gender").text("male");
	break;
	case 'w':
	$("#gender").text("female");
	break;
	}
</script>
</html>