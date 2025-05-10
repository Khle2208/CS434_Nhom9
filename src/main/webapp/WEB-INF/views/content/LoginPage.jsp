<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/login.css ">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/base.css">
<title>BookZone</title>
<style>
.error-box {
	display: none;
	position: fixed;
	top: 10%;
	left: 50%;
	transform: translateX(-50%);
	background-color: #ff4444;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	z-index: 1000;
	font-size: 16px;
	text-align: center;
}

.error-container {
	position: relative;
	min-height: 50px; /* Đảm bảo có không gian cho lỗi */
}

.text-danger {
	font-size: 14px;
	color: red;
	position: absolute;
	bottom: -20px;
	left: 0;
	border: 2px;
}

.toggle-password {
	position: absolute;
	right: 15px;
	top: 50%;
	transform: translateY(-50%);
	cursor: pointer;
	width: 25px;
	height: 25px;
}
</style>
</head>
<body>

	<div class="main-page">
		<div class="left-side">
			<img
				src="${pageContext.request.contextPath}/image/background-book.png"
				alt="Illustration of books on a shelf">
		</div>
		<div class="rigth-side">
			<div class="login-form">
				<h1 class="login-form_text">Chào mừng đến với BookZone</h1>
				<div class="login-form_img">
					<img
						src="${pageContext.request.contextPath}/image/BookZone-logo.png"
						alt="BookZone logo">
				</div>
				<h1 class="login-form_text">Đăng nhập với BookZone</h1>
				<div class="login-form_textfiled">
					<div id="error-popup" class="error-box"
						style="<%=request.getAttribute("error") != null ? "display: block;" : "display: none;"%>">
						<span id="error-message">${error}</span>
					</div>

					<form id="loginForm"
						action="${pageContext.request.contextPath}/Home" method="POST">

						<!-- Email -->
						<div class="form-floating mb-4 error-container">
							<input type="email" class="form-control" id="email" name="email"
								placeholder="email" oninput="hideError('email')"> <label
								for="email">Email</label> <span id="email-error"
								class="text-danger"></span>
						</div>

						<!-- Mật khẩu -->
						<div class="form-floating mb-5 error-container">
							<input type="password" class="form-control mt-2" id="password"
								name="password" placeholder="password"
								oninput="hideError('password')"> <label for="password"
								class="mt-2">Mật khẩu</label> <img class="toggle-password"
								id="togglePassword"
								src="${pageContext.request.contextPath}/image/Eye.png" alt="eye">
							<span id="password-error" class="text-danger"></span>
						</div>

						<div class="login-form_register">
							<a class="login-form_register_click flex-grow-1"
								href="ForgotPassword">Quên mật khẩu?</a> <a
								class="login-form_register_click"
								href="${pageContext.request.contextPath}/RegisterEmail">Tạo
								tài khoản</a>
						</div>

						<div class="login-form_line mb-3"></div>
						<button type="submit" class="btn btn-warning btn-lg">Đăng
							Nhập</button>
					</form>
				</div>

			</div>
		</div>
	</div>
	<script>
		// Ẩn lỗi popup sau 2 giây
		document.addEventListener("DOMContentLoaded", function() {
			var errorPopup = document.getElementById("error-popup");
			if (errorPopup.style.display === "block") {
				setTimeout(function() {
					errorPopup.style.display = "none";
				}, 2000);
			}
		});

		// Kiểm tra dữ liệu form trước khi submit
		document
				.getElementById("loginForm")
				.addEventListener(
						"submit",
						function(event) {
							var email = document.getElementById("email").value
									.trim();
							var password = document.getElementById("password").value;

							var emailError = document
									.getElementById("email-error");
							var passwordError = document
									.getElementById("password-error");

							var isValid = true;
							emailError.innerHTML = "";
							passwordError.innerHTML = "";

							// Kiểm tra email
							if (email === "") {
								emailError.innerHTML = "Vui lòng nhập email.";
								isValid = false;
							} else if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
									.test(email)) {
								emailError.innerHTML = "Email không hợp lệ.";
								isValid = false;
							}

							// Kiểm tra mật khẩu
							if (password === "") {
								passwordError.innerHTML = "Vui lòng nhập mật khẩu.";
								isValid = false;
							}
							// Ẩn lỗi khi nhập lại
							function hideError(fieldId) {
								document.getElementById(fieldId + "-error").style.display = "none";
								document.getElementById(fieldId).classList
										.remove("input-error");
							}
							if (!isValid) {
								event.preventDefault();
							}
						});

		// Ẩn lỗi khi nhập lại
		function hideError(fieldId) {
			document.getElementById(fieldId + "-error").style.display = "none";
			document.getElementById(fieldId).classList.remove("input-error");
		}

		// Xử lý hiện/ẩn mật khẩu khi click vào icon mắt
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							var toggleIcon = document
									.getElementById("togglePassword");
							var passwordInput = document
									.getElementById("password");

							toggleIcon
									.addEventListener(
											"click",
											function() {
												if (passwordInput.type === "password") {
													passwordInput.type = "text";
													toggleIcon.src = "${pageContext.request.contextPath}/image/hidden.jpg";
												} else {
													passwordInput.type = "password";
													toggleIcon.src = "${pageContext.request.contextPath}/image/Eye.png";
												}
											});
						});
	</script>
</body>
</html>