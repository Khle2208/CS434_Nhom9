<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/Layout.css">
  </head>
  <body>
    <footer class="footer pb-5">
        <div class="container text-center">
            <div class="row">
                <div class="col-md-4 mt-3">
                    <div class="logo_footer">
                        <img src="${pageContext.request.contextPath}\image\LogoImage.png" height="90" width="88" alt="">
                        BOOKZONE
                    </div>
                    <p>Hãy ghé thăm các chi nhánh của chúng tôi ở Đà Nẵng, Huế và Hà Nội và đăng ký nền tảng trực tuyến của chúng tôi để tận hưởng những lợi ích tối đa!</p>
                    <div class="footer-social">
                        <div class=" fab border-frame">
                            <i class="fa-facebook"></i>
                        </div>
                        <div class="fab border-frame">
                            <i class="fa-instagram"></i>
                        </div>
                        <div class="fab border-frame">
                            <i class="fa-linkedin"></i>
                        </div>
                        <div class="fab border-frame">
                            <i class="fa-twitter"></i>
                        </div>
                        <div class="fab border-frame">
                            <i class="fa-youtube"></i>
                        </div>
                        
                    </div>
                </div>
                <div class="col-md-2 text-start my-5 d-grid h-25 ps-5 ">
                    <h5>BOOKZONE</h5>
                    <a href="${pageContext.request.contextPath}/home" class="text-decoration-none text-white mb-3">Home</a>
                    <a href="${pageContext.request.contextPath}/about" class="text-decoration-none text-white mb-3">About</a>
                    <a href="${pageContext.request.contextPath}/BookList" class="text-decoration-none text-white mb-3">Shop</a>
                </div>
                <div class="col-md-3 my-5 text-start ps-3">
                    <h5>ADDRESS</h5>
                    <p>Hoà Hải, Ngũ Hành Sơn, Đà Nẵng</p>
                    <p>Hương Trà, Thành Phố Huế</p>
                    <p>Cầu Giấy, Hà Nội</p>
                </div>
                <div class="col-md-3 my-5 text-start  ps-3">
                    <h5>CONTACT</h5>
                    <p>Email góp ý</p>
                    <p><a href="mailto:bookstore@gmail.com" class="text-decoration-none text-white">bookstore@gmail.com</a></p>
                    <p>Hotline</p>
                    <p>0861274621</p>
                </div>
            </div>
        </div>
    </footer> 
  </body>