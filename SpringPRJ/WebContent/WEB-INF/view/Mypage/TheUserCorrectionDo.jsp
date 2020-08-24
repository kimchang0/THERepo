<%@page import="poly.util.CmmUtil"%>
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
	<form action="/Mypage/correectionProc.do">
		Email: <span>이메일은 수정이 불가능합니다.</span> <br>
		Gender: <input id="male" type="radio" name="gender" value="m" <%=CmmUtil.checked("m", res.getUser_gender()) %>> <label for="male">male</label> 
				<input id="female" type="radio" name="gender" value="w" <%=CmmUtil.checked("w", res.getUser_gender()) %>> <label for="female">female</label><br>
		Age: <select name="age">
				<option value="10" <%=CmmUtil.select("10", res.getUser_age()) %>>10대</option>
				<option value="20" <%=CmmUtil.select("20", res.getUser_age()) %>>20대</option>
				<option value="30" <%=CmmUtil.select("30", res.getUser_age()) %>>30대</option>
				<option value="40" <%=CmmUtil.select("40", res.getUser_age()) %>>40대</option>
				<option value="50" <%=CmmUtil.select("50", res.getUser_age()) %>>50대</option>
				<option value="60" <%=CmmUtil.select("60", res.getUser_age()) %>>60대</option>
			</select> <br>
			
			
			<button type="submit">수정하기</button>
			<button type="button" onclick="location.href='/Mypage/TheUserCorrection.do'">뒤로</button>
			
	</form>
</body>

<script>
	
	
</script>
</html>