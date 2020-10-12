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

	<form action="/The/TheLogin.do" method="post" onsubmit="return check()">
	이메일 : <input type="email" id="userEmail" name="email" placeholder="email" required> <button type="button" id="send">인증코드 전송</button>
		  <span class="msg2">사용하실 이메일을 입력하세요</span> <br> 
	인증코드 입력 : <input type="text" name="auth" id="auth" numberOnly="true" maxlength="6"> <button type="button" id="complete">인증하기</button>
			<span class="msg">인증코드를 입력하시고 인증하기를 눌러주세요.</span> <br>
	<button type="submit">완료</button>

	</form>

</body>
<script>

var emailCheck = 'N'
var authCheck = 'N'


$("#userEmail").keyup(function() {
		var query = {
			userEmail : $("#userEmail").val()
		};

		$.ajax({
			url : "emailCheck.do",
			type : "post",
			data : query,
			success : function(data) {
			
				if (data == 1) {
					$(".msg2").text("이미 사용하고 있는 이메일입니다.");
					$(".msg2").attr("style", "color:#f00");
				} else {
					$(".msg2").text("사용 가능한 이메일입니다. 인증하기를 눌러 이메일 인증을 진행해주세요.");
					$(".msg2").attr("style", "color:#00f");
					//$('#userId').attr("disabled", true);
				}
			}
		}); // ajax 끝
	});
	
$('#send').click(function() {
	var query = {
			email : $("#userEmail").val()
		};

		$.ajax({
			url : "TheEmailCertifyProc.do",
			type : "post",
			data : query,
			success : function(data) {
				if(data == 1) {
					$("#auth").attr("able", "ture");
					alert("이메일 전송이 완료되었습니다. 10분 내에 인증을 완료해주세요.");
					emailCheck = 'Y'
				} else {
					$("#auth").attr("disabled", "true");
					alert("이미 사용중이거나 사용하실 수 없는 이메일 주소입니다.");
					emailCheck = 'N'
				}
			}
		}); // ajax 끝
});

$(document).on("keyup", "input:text[numberOnly]", function() {
	$(this).val( $(this).val().replace(/[^0-9]/gi,"") );
	});

$('#complete').click(function() {
	var query = {
			auth : $("#auth").val()
		};

		$.ajax({
			url : "authNumCheck.do",
			type : "post",
			data : query,
			success : function(data) {
				if (data == 1) {
					$(".msg").text("인증 완료.");
					$(".msg").attr("style", "color:#00f");
					authCheck = 'Y'
				} else {
					$(".msg").text("인증 코드가 다릅니다. 다시 시도해주세요.");
					$(".msg").attr("style", "color:#f00");
					authCheck = 'N'
				}
			}}); // ajax 끝
});

	function check() {
	if(emailCheck == 'Y' && authCheck == 'Y') {
		alert("회원 가입이 완료되었습니다. 가입하신 아이디/비밀번호로 로그인 해주세요.");
		return true;
	} else if (emailCheck == 'Y'&& authCheck == 'N'){
		alert("인증 번호가 다릅니다. 인증 번호를 확인해주세요.")
		return false;
	} else if (emailCheck == 'N' && authCheck == 'N'){
		alert("이메일 인증을 해주세요.");
		return false;
	}
	
}
</script>
</html>