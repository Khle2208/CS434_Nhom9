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
	href="${pageContext.request.contextPath}/style/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/base.css">
<title>BookZone</title>
<style>
.error-container {
	position: relative;
	min-height: 20px;
}

.toggle-password {
	position: absolute;
	right: 15px;
	top: 50%;
	transform: translateY(-50%);
	width: 20px;
	height: 20px;
	cursor: pointer;
	opacity: 0.7;
}

.toggle-password:hover {
	opacity: 1;
}

.input-error {
	border-color: red !important;
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
				<h1 class="login-form_text">Đăng ký với BookZone</h1>
				<%
				HttpSession sessionn = request.getSession(false);
				String email = (sessionn != null) ? (String) sessionn.getAttribute("email") : null;
				String otp = (sessionn != null) ? (String) sessionn.getAttribute("otp") : null;
				if (email != null && email.contains(",")) {
					email = email.split(",")[0];
				}
				%>

				<div class="login-form_textfiled">
					<form id="registerForm"
						action="${pageContext.request.contextPath}/LoginPage"
						method="POST">

						<input type="hidden" name="email" value="<%=email%>"> <input
							type="hidden" name="otp" value="<%=otp%>">

						<!-- Username -->
						<div class="form-floating mb-3 error-container">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="User Name"
								oninput="hideError('username')"> <label for="username">User
								Name</label>

						</div>
						<span id="username-error" class="text-danger"></span>

						<!-- Password -->
						<div class="form-floating mb-3 error-container position-relative">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="Mật khẩu" oninput="hideError('password')"> <label
								for="password">Mật khẩu</label> <img class="toggle-password"
								id="togglePassword1"
								src="${pageContext.request.contextPath}/image/Eye.png"
								alt="eye">

						</div>
						<span id="password-error" class="text-danger"></span>

						<!-- Confirm Password -->
						<div class="form-floating mb-3 error-container position-relative">
							<input type="password" class="form-control" id="repassword"
								name="confirmPassword" oninput="hideError('repassword')" placeholder="Nhập lại mật khẩu">
							<label for="repassword">Nhập lại mật khẩu</label> <img
								class="toggle-password" id="togglePassword2"
								src="${pageContext.request.contextPath}/image/Eye.png"
								alt="eye">

						</div>

						<span id="repassword-error" class="text-danger"></span> <br>
						<button type="submit" class="btn btn-warning btn-lg">Đăng
							ký</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		document
				.getElementById("registerForm")
				.addEventListener(
						"submit",
						function(event) {
							var username = document.getElementById("username").value
									.trim();
							var password = document.getElementById("password").value
									.trim();
							var repassword = document
									.getElementById("repassword").value.trim();

							var usernameError = document
									.getElementById("username-error");
							var passwordError = document
									.getElementById("password-error");
							var repasswordError = document
									.getElementById("repassword-error");

							var isValid = true;

							// Kiểm tra username
							if (username === "") {
								usernameError.innerText = "Vui lòng nhập tên người dùng.";
								usernameError.style.display = "block";
								document.getElementById("username").classList
										.add("input-error");
								isValid = false;
							} else if (/\s/.test(username)) {
								usernameError.innerText = "Tên người dùng không được chứa khoảng cách.";
								usernameError.style.display = "block";
								isValid = false;
							} else if (!/^[a-zA-Z0-9]+$/.test(username)) {
								usernameError.innerText = "Tên người dùng chỉ được chứa chữ cái và số, không chứa ký tự đặc biệt.";
								usernameError.style.display = "block";
								isValid = false;
							}

							// Kiểm tra mật khẩu
							if (password === "") {
								passwordError.innerText = "Vui lòng nhập mật khẩu.";
								passwordError.style.display = "block";
								isValid = false;
							} else if (password.length < 6
									|| !/\d/.test(password)
									|| !/[a-zA-Z]/.test(password)) {
								passwordError.innerText = "Mật khẩu phải có ít nhất 6 ký tự, chứa chữ cái và số.";
								passwordError.style.display = "block";
								isValid = false;
							}
							// Kiểm tra email

							// Kiểm tra xác nhận mật khẩu
							if (repassword === "") {
								repasswordError.innerText = "Vui lòng nhập lại mật khẩu.";
								repasswordError.style.display = "block";
								isValid = false;
							} else if (password !== repassword) {
								repasswordError.innerText = "Mật khẩu xác nhận không khớp.";
								repasswordError.style.display = "block";
								isValid = false;
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

		// Ẩn/hiện mật khẩu
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							function togglePassword(iconId, inputId) {
								var toggleIcon = document
										.getElementById(iconId);
								var passwordInput = document
										.getElementById(inputId);

								if (toggleIcon && passwordInput) {
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
								}
							}

							togglePassword("togglePassword1", "password");
							togglePassword("togglePassword2", "repassword");
						});
	</script>

</body>
</html>