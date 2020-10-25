<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>TheLogin</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="/resources/images/icons/favicon.ico" >
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/resources/css/util.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<!--===============================================================================================-->
<style>
img {
display: block; margin: 0px auto;  
}
form{
display: block; margin: 0px auto; 
}
.limiter2{
text-align: center;
}

</style>
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			
			<div class="wrap-login100 p-l-55 p-r-55 p-t-20 p-b-50" >
			<img src="/resources/images/The.png" width="200" ><br>
				<form class="login100-form validate-form" action="/The/TheLoginProc.do" method="post">
					<span class="login100-form-title p-b-33"> Account Login </span>
					<span class="txt1"> Please login to your account. </span>
					 	<br>
						<span class="txt1"> Forgot </span>
						<a href="/The/findId.do" class="txt2 hov1"> ID & Password? </a><hr>
					

					<div class="wrap-input100 validate-input" data-validate="ID is required">
					
						<input class="input100" type="text" name="id" placeholder="ID" style="height: 60px;">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>
					
					<div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="pwd" placeholder="Password" style="height: 60px;">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>
				장
					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn" type="submit" style="height: 50px">Sign in</button>
					</div><br>
					<div>
					<span class="txt1">Don't have an account?</span>
					<a href="/The/TheSignUp.do" class="txt2 hov1"> Sign up </a>
					</div>
				</form>

			</div>
		</div>
	</div>
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>