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

 	<form action="/Mypage/passWordChangeProc.do" onsubmit="return check()">
 	
 		현재 비밀번호: <input type="password" id="current_Password" required> <br>
 		<span id="past">현재 비밀번호를 입력해주세요.</span> <br>
 		바꿀 비밀번호: <input type="password" id="newPassWord" name="pwd" required> <br>
 		<span class="new">바꾸실 비밀번호를 입력해주세요.</span> <br>
 		비밀번호 확인: <input type="password" id="passWordCheck" required> <br>
 		<span class="new">비밀번호 확인을 위해 한 번 더 입력해주세요.</span> <br>
 		
 		<button type="submit">수정하기</button>
 		<button type="button" onclick="location.href='/Setting/TheMypage.do'">뒤로</button>
 		
 	</form>

</body>
<script>

var pastPwd = 'N';
var newPwd = 'N';

function check(){
	if(pastPwd == 'Y' && newPwd == 'Y') {
		return true;
	} else if(pastPwd == 'N' && newPwd == 'N') {
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if(pastPwd == 'N') {
		alert("현재 비밀번호가 다릅니다.");
		return false;
	} else if($("#newPassWord").val() != $("#passWordCheck").val()){
		alert("바꾸실 비밀번호가 일치하지 않습니다. 다시 한 번 확인해주세요.");
		return false;
	} else if(newPwd == 'N') {
		alert("바꾸실 비밀번호를 입력해주세요.");
		return false;
	}
}

$("#current_Password").keyup(function() {
	var query = {
		pwd : $("#current_Password").val()
	};

	$.ajax({
		url : "passWordCheck.do",
		type : "post",
		data : query,
		success : function(data) {
		
			if (data == 1) {
				$("#past").text("비밀번호가 일치합니다.");
				$("#past").attr("style", "color:#00f");
				pastPwd = 'Y';
			} else if(data == 0) {
				$("#past").text("비밀번호가 다릅니다. 비밀번호를 확인해주세요.");
				$("#past").attr("style", "color:#f00");
				pastPwd = 'N';
			}
		}
	}); // ajax 끝
});
$('#newPassWord').keyup(function() {
	var pwd1 = $("#newPassWord").val();
	var pwd2 = $("#passWordCheck").val();

	if (pwd1 != '' && pwd2 == '') {
		null;
	} else if (pwd1 != "" || pwd2 != "") {
		if (pwd1 == pwd2) {
			$(".new").text("비밀번호가 일치합니다.");
			$(".new").css("color", "#00f");
			newPwd = 'Y';
		} else {
			$(".new").text("비밀번호가 다릅니다. 비밀번호를 확인해주세요.");
			$(".new").css("color", "#f00");
			newPwd = 'N';
		}
	}
});

$('#passWordCheck').keyup(function() {
		var pwd1 = $("#newPassWord").val();
		var pwd2 = $("#passWordCheck").val();

		if (pwd1 != '' && pwd2 == '') {
			null;
		} else if (pwd1 != "" || pwd2 != "") {
			if (pwd1 == pwd2) {
				$(".new").text("비밀번호가 일치합니다.");
				$(".new").css("color", "#00f");
				newPwd = 'Y';
			} else {
				$(".new").text("비밀번호가 다릅니다. 비밀번호를 확인해주세요.");
				$(".new").css("color", "#f00");
				newPwd = 'N';
			}
		}
	});
</script>
</html>