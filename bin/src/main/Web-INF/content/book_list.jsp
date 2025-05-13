<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>Book's List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/style/book_list.css"
	rel="stylesheet">
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>

	<div class="container">
		<h1 style="display: none;">BOOKZONE_BookStore</h1>
		<div class="container my-5">
			<div class="row">
				<form action="${pageContext.request.contextPath}/book_list/search"
					method="GET" class="col-md-3">
					<div
						class="d-flex justify-content-between m-auto ps-3 border rounded-3 mb-4">
						<input type="text" onfocus="this.style.outline='none';"
							class="border-0 d-inline-block" placeholder="Tìm kiếm sách..."
							name="search_value"
							value="<%= (request.getParameter("search_value") != null) ? request.getParameter("search_value") : "" %>" />
						<button type="submit" class="border-0 bg-transparent me-2"><i class="fa-solid fa-magnifying-glass text-body-tertiary my-auto"></i></button>

					</div>
					<div class="border p-3 rounded shadow">
						<h5>Mức giá</h5>
						<div class="pb-4 border-bottom">
							<div class="input-group">
								<span class="input-group-text">đ</span> <input type="number"
									id="minPrice" name="minPrice" class="form-control price-input"
									placeholder="Min" min="0" max="99999999">
							</div>
							<span>-</span>
							<div class="input-group">
								<span class="input-group-text">đ</span> 
								<input type="number" name="maxPrice"
									id="maxPrice" class="form-control price-input"
									placeholder="Max" min="0" max="99999999">
							</div>
						</div>

						<h5 class="mt-4">Thể loại</h5>
						<div>
							<c:forEach var="genre" items="${genres}">
								<input type="checkbox" class = "genre_checked" id="tl1" name="option"  value="${genre.genreId }"/> 
								<label for="tl1">${genre.genreName}</label><br /> 
							</c:forEach>
							<input type="checkbox" class = "genre_checked" id="tl5" name="all"/> 
								<label for="tl5">Tất cả</label>
						</div>
					</div>
				</form>

				<div class="col-md-9">
					<div class="row g-4">
						<h3 class="text-center mb-4">Sản phẩm trưng bày</h3>

						<c:if test="${totalBooksFound >= 0}">
							<p><b>Kết quả tìm kiếm:</b> ${totalBooksFound} sách</p>
							<c:if test="${totalBooksFound == 0}">
								<p class="text-center my-5">Sản phẩm không tồn tại!</p>
								<p class="fw-bold text-center">Sản phẩm tương tự</p>
							</c:if>
						</c:if>
						
						<!-- card -->
						<c:forEach items="${books}" var="book">
							<div
								class="cart_item d-grid ms-3 pb-2 px-3 col-md-4 col-ms-6 border rounded-3" id = "${book.bookId}"
								style="width: 30%;">
								<button type="button"
									class="btn btn-warning text-center mt-3 order-1 ms-auto"
									style="max-width: 45px">
									<i class="fa-solid fa-cart-shopping text-white"></i>
								</button>
								<img class="m-auto order-2"
									style="width: 145px; height: 210px"
									src="${pageContext.request.contextPath}/book/image/${book.bookId}"
									class="card-img-top" alt="${book.bookName}" />
								<div class="card-body order-3">
									<h5 class="card-title">${book.bookName}</h5>
									<span class="fw-bold fs-5">${book.price}đ</span>
									
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>	
	<script>
    document.addEventListener("DOMContentLoaded", function () {
        const allCheckbox = document.querySelector('input[name="all"]');
        const genreCheckboxes = document.querySelectorAll('input[name="option"]');

        allCheckbox.addEventListener("change", function () {
            if (this.checked) {
                genreCheckboxes.forEach(cb => cb.checked = false);
            }
        });

        genreCheckboxes.forEach(cb => {
            cb.addEventListener("change", function () {
                if (this.checked) {
                    allCheckbox.checked = false;
                }
            });
        });
    });
</script>
	
	<script>
        const minPrice = document.getElementById("minPrice");
        const maxPrice = document.getElementById("maxPrice");

        function validatePrice() {
            let minValue = parseInt(minPrice.value) || 0;
            let maxValue = parseInt(maxPrice.value) || 99999999;

            if (minValue >= maxValue) {
                minPrice.value = maxValue - 1;
            }

            if (minValue > 99999999) minPrice.value = 99999998;
            if (maxValue > 99999999) maxPrice.value = 99999999;
        }

        minPrice.addEventListener("input", validatePrice);
        maxPrice.addEventListener("input", validatePrice);
    </script>
	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

