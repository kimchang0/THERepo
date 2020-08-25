<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="poly.dto.UserDTO" %>
<%@ page import="static poly.util.CmmUtil.nvl" %>
<%
 
	String[] interest = (String[])request.getAttribute("interest");
	
 
%>
<%@page import="poly.util.CmmUtil"%>
<!DOCTYPE html>
<html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/Mypage/interestSettingProc.do">
	
		<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll();"><label for="checkAll">전체 선택</label> <br><hr> 
		<input type="checkbox" name="interest" id="interest1" value="toeic" <%=CmmUtil.checked(interest, "toeic") %>><label for="interest1">토익</label> <br>
		<input type="checkbox" name="interest" id="interest2" value="academic" <%=CmmUtil.checked(interest, "academic") %>><label for="interest2">학술/논문</label> <br>
		<input type="checkbox" name="interest" id="interest3" value="business" <%=CmmUtil.checked(interest, "business") %>><label for="interest3">비즈니스</label> <br>
	
		<button type="submit">완료</button>
		<button type="button" onclick="location.href='/Setting/TheMypage.do'">뒤로</button>
	
	</form>
</body>
<script type="text/javascript">
$(function(){ //전체선택 체크박스 클릭
	$("#checkAll").click(function(){ //만약 전체 선택 체크박스가 체크된상태일경우 
		if($("#checkAll").prop("checked")) { //해당화면에 전체 checkbox들을 체크해준다 
			$("input[type=checkbox]").prop("checked",true); // 전체선택 체크박스가 해제된 경우 
		} else { //해당화면에 모든 checkbox들의 체크를해제시킨다. 
			$("input[type=checkbox]").prop("checked",false); 
		} 
	}) 
})
</script>
</html>