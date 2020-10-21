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

	<form action="/Mypage/TheUserDeleteProc.do" onsubmit="return check()">
		<input type="text" id="deleteCheck" placeholder="탈퇴를 확인합니다."> <br>
		<span id="msg">회원탈퇴를 하면 다시 되돌릴 수 없으며, 모든 정보가 삭제됩니다.<br> 탈퇴를 계속 진행하시려면 '탈퇴를 확인합니다.'를 입력해주세요.</span> <br>
		<button type="submit">확인</button> <br>
		<button type="button" onclick="location.href='/Setting/TheMypage.do'" >뒤로</button> <br>
	</form>

</body>
<script>
var userDeleteCheck = 'N'


	$("#deleteCheck").keyup(function() {
			var query = {
				DeleteCheck : $("#deleteCheck").val()
			};

			$.ajax({
				url : "TheUserDeleteCheck.do",
				type : "post",
				data : query,
				success : function(data) {
				
					if (data == 1) {
						userDeleteCheck = 'Y'
					} else if(data == 0) {
						userDeleteCheck = 'N'
					}
				}
			}); // ajax 끝
		});

		function check() {
		if(userDeleteCheck == 'Y') {
			return true;
		} else if (userDeleteCheck == 'N'){
			alert("탈퇴 확인 문장이 틀렸습니다. 다시 입력해주세요.");
			$("#deleteCheck").val(''); 
			return false;
		} 
		
	}
</script>
</html>