<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/Setting/TheMypage.do" onsubmit="return check()">

	<input type="password" id="pwdCheck"> <br>
	<span id="msg">비밀번호를 입력해주세요.</span> <br>
	<button type="submit">확인</button> <br>
	<button type="button" onclick="location.href='/The/setting.do'" >뒤로</button> <br>


</form>

</body>
<script>

var PasswordCheck = 'N'


$("#pwdCheck").keyup(function() {
		var query = {
			pwd : $("#pwdCheck").val()
		};

		$.ajax({
			url : "TheMypageCheckProc.do",
			type : "post",
			data : query,
			success : function(data) {
			
				if (data == 1) {
					$("#msg").text("비밀번호가 일치합니다.");
					$("#msg").attr("style", "color:#00f");
					PasswordCheck = 'Y'
				} else if(data == 0) {
					$("#msg").text("비밀번호가 다릅니다. 비밀번호를 확인해주세요.");
					$("#msg").attr("style", "color:#f00");
					PasswordCheck = 'N'
				}
			}
		}); // ajax 끝
	});

	function check() {
	if(PasswordCheck == 'Y') {
		return true;
	} else if (PasswordCheck == 'N'){
		alert("비밀번호를 확인해 주세요.");
		return false;
	} 
	
}
</script>

</html>