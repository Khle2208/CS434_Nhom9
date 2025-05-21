<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/Layout.css" />
  </head>
<header class="header position-sticky top-0" >
      <!-- LOGO -->
      <div class="logo d-flex align-items-center">
        <img src="${pageContext.request.contextPath}/image/LogoImage.png" height="90" width="88" alt="" />
        <p class="m-0">BOOKZONE</p>
      </div>
      <!-- nav bar -->
      <nav class="nav-links">
        <a href="/${pageContext.request.contextPath}/home">Home</a>
        <a href="/${pageContext.request.contextPath}/about">About</a>
        <a href="/${pageContext.request.contextPath}/BookList">Shop</a>
      </nav>
      <!-- icon, notify, avatar -->
      <div class="icons">
        <div class="dropdown position-relative">
          <button
            class="btn dropdown"
            onfocus="this.style.border='none';"
            type="button"
            id="notificationDropdown"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <i class="fa-solid fa-bell"></i>
          </button>
          <!-- Dropdown Content -->
          <ul
            class="dropdown-menu dropdown-menu-end position-absolute z-3"
            aria-labelledby="notificationDropdown" 
          >
            <!-- Header -->
            <li class="dropdown-header">
              <h5>Notifications</h5>
            </li>

            <!-- Notifications -->
            <li class="notification-item w-auto">
              <img src="${pageContext.request.contextPath}/image/klee.jpg" class="avatar" alt="User" />
              <div class="notification-text">
                <strong>Zone Hand</strong>
                <p>Sản phẩm mới đã được thêm vào</p>
                <span>30p trước</span>
              </div>
              <div class="status-container">
                <span class="status-dot red"></span>
                <i class="fa-solid fa-chevron-right arrow"></i>
              </div>
            </li>
            <!-- notification_item -->
            <li class="notification-item">
              <img
                src="${pageContext.request.contextPath}/image/klee.jpg"
                class="avatar"
                alt="User"
              />
              <div class="notification-text">
                <strong>Zone Hand</strong>
                <p>5 sản phẩm đã bị xóa</p>
                <span>30p trước</span>
              </div>
              <div class="status-container">
                <span class="status-dot red"></span>
                <i class="fa-solid fa-chevron-right arrow"></i>
              </div>
            </li>
            <li class="notification-item">
              <img
                src="${pageContext.request.contextPath}/image/klee.jpg"
                class="avatar"
                alt="User"
              />
              <div class="notification-text">
                <strong>Zone Hand</strong>
                <p>Sản phẩm A đã được cập nhật thông tin</p>
                <span>30p trước</span>
              </div>
              <div class="status-container">
                <span class="status-dot green"></span>
                <i class="fa-solid fa-chevron-right arrow"></i>
              </div>
            </li>
          </ul>
        </div>
		<a href="/${pageContext.request.contextPath}/cart" class="link-dark" ><i class="fas fa-shopping-cart"></i></a>
        
        <!-- profile_pop-up -->
        <div class="dropdown">
          <button
            class="btn dropdown-toggle d-flex align-items-center"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <img src="${pageContext.request.contextPath}/image/klee.jpg" class="avatar" alt="User" />
          </button>
          <ul class="dropdown-menu">
            <li>
              <a href="#" class="dropdown-item"
                ><i class="fa-solid fa-gear me-2"></i>
                Thông tin người dùng
              </a>
            </li>
            <li>
              <a href="#" class="dropdown-item">
                <i class="fa-solid fa-right-from-bracket me-2"></i>
                Đăng xuất
              </a>
            </li>
          </ul>
        </div>
      </div>
    </header>