<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link href="${pageContext.request.contextPath}/style/product_detail.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/style/etc_item.css" rel="stylesheet" />
<title>Detail_Products</title>
<style type="text/css">
.book-image {
	width: auto;
	height: auto;
	border: 2px solid #1d3c6a;
	padding: 10px;
	border-radius: 5px;
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-4 book-image">
				<img
					src="${pageContext.request.contextPath}/book/image/${book.bookId}"
					class="bordered-image w-75 h-75" alt="Ảnh bìa sách">
			</div>
			<%  String path = request.getServletPath();
 						    String actionUrl = path.substring(path.lastIndexOf("/") + 1).replaceAll("\\.jsp$", "");
							%>
							
			<div class="col-md-4 ms-2">
				<h2>${book.bookName}</h2>
				<div>
					<span class="star-rating">
    					<c:forEach var="i" begin="1" end="5">
 					       <c:choose>
 					           <c:when test="${i <= avgStar}">
 					               <span style="color: gold;">★</span>
 					           </c:when>
 					           <c:when test="${i - 0.5 == avgStar}">
          					      <i class="bi bi-star-half text-warning"></i> <!-- ✦ Half Star -->
          					  </c:when>
 					           <c:otherwise>
 					               <span style="color: gray;">☆</span>
 					           </c:otherwise>
 					       </c:choose>
 					   </c:forEach>
					</span> <a href="#reviews-tab" onclick="getReviewsTrue()">${book.totalRating}
						khách hàng <br> Nhận xét
					</a>
				</div>
				<p>
					<strong>Tác giả:</strong> 
					<c:forEach items="${listAuthorDetail}" var="item" varStatus="status">${item.authorName}${status.last ? '.' : ', '}</c:forEach>
				</p>
				<p>
					<strong>Ngày xuất bản:</strong> ${book.publishedDate}
				</p>
				<p>
					<strong>Số lượng:</strong> ${book.quantity} sách
				</p>
				<p class="price fw-bold">${book.price}</p>

				<c:choose>
					<c:when test="${book.quantity > 0}">
						<div class="quantity">
							<%-- <input type="number" id="quantity" value="1" min="1"
								max="${book.quantity}" step="1"> --%>
								<a href="${pageContext.request.contextPath}/cart/add/${book.bookId}?actionUrl=${actionUrl}&quantity=1&buyNow=true">
									<button class="btn btn-secondary"<%--  onclick = "sendRedirectToPayment( ${pageContext.request.contextPath}, ${book.bookId}, ${actionUrl})" --%>>Mua ngay</button>	
								</a>				
						</div>
					</c:when>
					<c:otherwise>
						<div class="quantity" style="pointer-events: none; opacity: 0.5;">
							<%-- <input type="number" id="quantity" value="0" min="1"
								max="${book.quantity}" step="1"> --%>
								<%-- <a href="${pageContext.request.contextPath}/cart/add/${book.bookId}?actionUrl=${actionUrl}&quantity=1"> --%>
									<button class="btn btn-secondary" onclick = "sendRedirectToPayment( ${pageContext.request.contextPath}, ${book.bookId}, ${actionUrl})">Mua ngay</button>	
								<!-- </a> -->
							
							<br>
						</div>
						<p class="text-danger fs-3">Đã hết hàng!!!</p>
					</c:otherwise>
				</c:choose>
				<button class="btn btn-secondary">Đánh giá Sách</button>

				<!-- <h3>Ưu đãi liên quan <a href="#">Xem thêm =>></a> </h3>
                <div class="promotions">
                  <button class="badge bg-success"> 50% OFF </></button>
                  <button class="badge bg-info"> Miễn phí vận chuyển </button>
                  <button class="badge bg-danger"> 40% OFF </button>
                </div> -->

				<h3>Hình ảnh mô tả</h3>
				<div class="related-products">
					<div class="row g-3">
						<c:forEach items="${bookImages}" var="image">
							<img class="btn btn-col-4"
								src="${pageContext.request.contextPath}/book/imageDetail/${image.bookId}?bookImageId=${image.imageId}"
								alt="Hình ảnh mô tả" class="img-fluid">
						</c:forEach>

					</div>
				</div>
			</div>
			<div class="mt-4">
				<ul class="nav nav-tabs" id="productTabs" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="description-tab"
							data-bs-toggle="tab" data-bs-target="#description" type="button"
							role="tab" aria-controls="description" aria-selected="true">
							Mô tả</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="reviews-tab" data-bs-toggle="tab"
							data-bs-target="#reviews" type="button" role="tab"
							aria-controls="reviews" aria-selected="false">Nhận xét</button>
					</li>
				</ul>
				<div class="tab-content" id="productTabsContent">
					<div class="tab-pane fade show active p-3 border" id="description"
						role="tabpanel" aria-labelledby="description-tab">

						<p>${empty book.discription ? "Không có mô tả" : book.discription}</p>
					</div>
					<div class="tab-pane fade p-3 border" id="reviews" role="tabpanel"
						aria-labelledby="reviews-tab">
						<div class="reviews" id="review_items">

							<div class="review mt-3">
								<c:choose>
									<c:when test="${book.totalRating > 0}">
										<h4>${book.totalRating} nhậnxét</h4>
										<c:forEach items="${ratings}" var="rating">
											<div class="d-flex">
												<img src="${usersMap.get(rating.userId).getAvatar() }"
													alt="Avatar" class="rounded-circle me-2" width="50"
													height="50">
												<div>
													<div class="review-header">
														<div>
															<span class="fw-bold">${usersMap[rating.userId].username}</span>
															<div>
																<span class="review-date">${rating.createdAt}</span>
															</div>
														<div class="review-stars star-rating">
    														<c:forEach var="i" begin="1" end="5">
 														       <c:choose>
 														           <c:when test="${i <= rating.starNumber}">
 														               <span style="color: gold;">★</span>
 														           </c:when>
 														           <c:otherwise>
 														               <span style="color: gray;">☆</span>
 														           </c:otherwise>
 														       </c:choose>
 														   </c:forEach>
														</div>
														</div>
														<p class="review-text">${rating.content}</p>
													</div>
												</div>
											</div>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p>Chưa có nhận xét nào.</p>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="col-md-12">
			<div class="row g-4">
				<h3 class="text-center mb-4 pt-3">Sản phẩm khác</h3>
				<div class="cart-list d-flex justify-content-between gap-2 mx-auto">
					<!-- item_cart -->
					
					<c:forEach items="${books}" var="book_item">
						<div
								class="cart_item d-grid ms-3 pb-2 px-3 col-md-4 col-ms-6 border rounded-3" id = "${book_item.bookId}"
								style="width: 23%;">
								<a href="${pageContext.request.contextPath}/cart/add/${book_item.bookId}?actionUrl=&quantity=1&buyNow=false" class="mt-3 order-1 ms-auto mb-2">
								<button type="button"
								class="btn btn-warning text-center ">
								
								<i class="fa-solid fa-cart-shopping d-flex justify-content-center align-items-center text-white" style="height: 24px; width: 18px;"></i>
									
							</button>
								</a>
								<a href="${pageContext.request.contextPath}/product_detail/${book_item.bookId}" class="m-auto order-2">
									<img 
									style="width: 145px; height: 210px"
									src="${pageContext.request.contextPath}/book/image/${book_item.bookId}"
									class="card-img-top" alt="${book_item.bookName}" />
								</a>
								
								<div class="card-body order-3">
									<h5 class="card-title etc">${book_item.bookName}</h5>
									<span class="fw-bold fs-5 etc">${book_item.price}đ</span>
									
								</div>
							
								
							</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="modal fade" id="errorSessionLogin" tabindex="-1"
		aria-labelledby="addUserModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg"
			style="margin-top: 15vh; max-width: 450px;">
			<div class="modal-content">
				<div class="modal-header ms-auto border-0">
					<a href="${pageContext.request.contextPath}/product_detail/${book.bookId}">
					<button type="button" class="btn" data-bs-dismiss="modal"
						aria-label="Close">
					<i class="fa-solid fa-x" style="color: #cc9600"></i>
					</button>
					</a>
					
				</div>
				<h3 class="modal-title text-center w-100" id="addBookModalLabel">
					Thêm giỏ hàng thất bại</h3>
				
				<div class="modal-body">
					<form class="w-100">
						<div class="d-flex justify-content-center mb-4">
							<div class="position-relative">
								<div
									class="d-flex align-items-center justify-content-center bg-danger rounded-circle"
									style="width: 100px; height: 100px;">
									<i class="fas fa-times text-white" style="font-size: 48px;"></i>
								</div>
							</div>
						</div>
						<p class="text-center text-muted">Người dùng chưa đăng nhập.</p>
						<div class="text-center d-flex  justify-content-center">
							<a href="${pageContext.request.contextPath}/LoginPage" class= "btn btn-warning w-auto text-white fw-bold">Login Now</a>
						
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
 <c:if test="${not empty errorSessionLogin}">
		<script>
			document.addEventListener("DOMContentLoaded", function() {
				var displayError = new bootstrap.Modal(document
						.getElementById('errorSessionLogin'));
				displayError.show();
			});
		</script>
	</c:if>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/handle_event_listener.js"
	defer></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous">
  </script>
 
