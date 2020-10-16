<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>TheSignup</title>
</head>
<body>
	<div>
		가입하기
		<form action="/The/TheSignUpProc.do" method="post"
			onsubmit="return check()">

			아이디 : <input type="text" id="userId" name="id" placeholder="id"
				required><br> <span class="msg">사용하실 아이디를 입력하세요</span><br>

			비밀번호 : <input type="password" name="pwd" required id="password_1"
				class="pw" placeholder="8~15자 영문,숫자,특수문자"><br> 비밀번호 확인
			: <input type="password" required id="password_2" class="pw"
				placeholder="비밀번호 확인"> <span class="alert-success"
				style="display: none;">비밀번호가 일치합니다.</span> <span id="alert-danger"
				style="display: none; color: #d92742; font-weight: bold;">비밀번호가
				일치하지 않습니다.</span> <br> <br> 
				
			나이: <select name="age">
				<option value="10">10대</option>
				<option value="20" selected>20대</option>
				<option value="30">30대</option>
				<option value="40">40대</option>
				<option value="50">50대</option>
				<option value="60">60대</option>
			</select><br> 성별 : <input id="male" type="radio" name="gender" value="m"
				checked> <label for="man">male</label> <input id="female"
				type="radio" name="gender" value="w"> <label for="woman">female</label><br>

			Interest : <input type="checkbox" name="interest" id="interest1"
				value="toeic"><label for="interest1">토익</label> <input
				type="checkbox" name="interest" id="interest2" value="academic"><label
				for="interest2">학술/논문</label> <input type="checkbox" name="interest"
				id="interest3" value="business"><label for="interest3">비즈니스</label>

			<br>
			<button id="btn" type="submit" class="alert-success">Sign Up</button>
		</form>
	</div>
</body>
<script>
	//ID 중복확인
	var doCheck = 'N'

	$("#userId").keyup(function() {
		var query = {
			userId : $("#userId").val()
		};

		$.ajax({
			url : "idCheck.do",
			type : "post",
			data : query,
			success : function(data) {
				if (data == 1) {
					$(".msg").text("이미 사용하고 있는 아이디입니다. 아이디를 확인해주세요.");
					$(".msg").attr("style", "color:#f00");
					doCheck = 'N'
				} else {
					$(".msg").text("사용 가능한 아이디입니다. 회원가입을 계속 진행해주세요.");
					$(".msg").attr("style", "color:#00f");
					//$('#userId').attr("disabled", true);
					doCheck = 'Y'
				}
			}
		}); // ajax 끝
	});
	function check() {
		if (doCheck == 'N') {
			alert("사용중인 아이디입니다. 아이디를 확인해주세요.")
			return false;
		}
	}

	// password 일치 불일치
	$('.pw').keyup(function() {
		var pwd1 = $("#password_1").val();
		var pwd2 = $("#password_2").val();

		if (pwd1 != '' && pwd2 == '') {
			null;
		} else if (pwd1 != "" || pwd2 != "") {
			if (pwd1 == pwd2) {
				$(".alert-success").css('display', 'inline-block');
				$("#alert-danger").css('display', 'none');
			} else {
				$(".alert-success").css('display', 'none');
				$("#alert-danger").css('display', 'inline-block');
			}
		}
	});
</script>
</html>