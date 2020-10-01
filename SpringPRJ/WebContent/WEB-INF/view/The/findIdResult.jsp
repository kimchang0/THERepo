<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
가입하신 아이디는 <%=session.getAttribute("user_id") %>입니다.
<form action="/The/login.do">
	<button type="button" onclick="location.href='/The/login.do'">로그인창으로가기</button>
</form>
</body>
</html>