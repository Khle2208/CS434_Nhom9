<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Payment</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
  </head>
  
  <body class="bg-gray-100">
  <jsp:include page="../layout/header.jsp" ></jsp:include>
   <div class=" p-4 max-w-6xl mx-auto p-4">
   	<div class="bg-white p-4 rounded shadow-md mb-4">
      <h2 class="text-lg font-semibold mb-2 border-b">ĐỊA CHỈ GIAO HÀNG</h2>
      <div class="border-b border-gray-300 pb-2 mb-2">
      <c:forEach var="address" items="${listAddress}">
        <label class="flex items-center mb-2">
          <input
            type="radio"
            name="address"
            class="form-radio text-red-600 h-4 w-4"
            value="${address.addressID}" 
          />
          <span class="ml-2"
            >${user.name} | ${address.street}, ${address.ward}, ${address.district}, ${address.province}, VN
            | ${user.phone}</span
          >
          <a href="#" id="updateAddress_${address.addressID}" class="ml-auto text-blue-600">Sửa</a>
          <a href="/MockProject/delete?address_id=${address.addressID}" class="ml-2 text-blue-600">Xóa</a>
        </label>
       </c:forEach>
      </div>
      <div class="flex items-center">
        <i class="fas fa-plus text-red-600"></i>
        <button class="ml-2 text-red-600" id="otherAddressBtn" >Giao hàng đến địa chỉ khác</button>
      </div>
    </div>

    <div class="bg-white p-4 rounded shadow-md mb-4">
      <h2 class="text-lg font-semibold mb-2 border-b">
        PHƯƠNG THỨC VẬN CHUYỂN
      </h2>
      <div class="border-b border-gray-300 pb-2 mb-2">
        <label class="flex items-center">
          <input
            type="radio"
            name="shipping"
            class="form-radio text-red-600 h-4 w-4"
            checked
          />
          <span class="ml-2">
            <span class="font-semibold">Giao hàng tiêu chuẩn: 32.000 đ</span
            ><br />
            <span class="text-sm text-gray-600"
              >Dự kiến giao: Thứ Hai - 10/03</span
            >
          </span>
        </label>
      </div>
    </div>

    <div class="bg-white p-4 rounded shadow-md mb-4">
      <h2 class="text-lg font-bold mb-4">PHƯƠNG THỨC THANH TOÁN</h2>
      <div class="space-y-4">
        <div class="flex items-center">
          <input  class="form-radio text-red-600 h-4 w-4" name="payment" type="radio" checked/>
          <img
            alt="Cash on delivery logo"
            class="ml-2"
            height="20"
            src="https://storage.googleapis.com/a1aa/image/K_RAGxM1INmDZZH8yjhxpMy8c761gXY4CKWwpiSm4t8.jpg"
            width="35"
          />
          <span class="ml-2"> Thanh toán bằng tiền mặt khi nhận hàng </span>
        </div>
        <div class="flex items-center">
          <input  class="form-radio text-red-600 h-4 w-4" name="payment" type="radio"/>
          <img
            alt="Cash on delivery logo"
            class="ml-2"
            height="20"
            src="https://sandbox.vnpayment.vn/paymentv2/Images/brands/logo.svg"
            width="35"
          />
          <span class="ml-2">VNPay</span>
        </div>
      </div>
    </div>

    <div class="bg-white p-4 rounded shadow-md mb-4">
      <h2 class="text-lg font-semibold mb-4">MÃ KHUYẾN MÃI/MÃ QUÀ TẶNG</h2>
      <div class="flex items-center mb-4">
        <label for="promo-code" class="w-1/5 text-gray-700"
          >Mã KM/Quà tặng</label
        >
        <input
          id="promo-code"
          type="text"
          placeholder="Nhập mã khuyến mãi/Quà tặng"
          class="w-1/2 p-2 border border-gray-300 rounded"
        />
        <button class="ml-4 px-4 py-2 bg-blue-500 text-white rounded">
          Áp dụng
        </button>
        <a href="#" class="ml-4 text-blue-500">Chọn mã khuyến mãi</a>
      </div>
      <!-- <div class="flex items-center mb-4">
        <span
          class="bg-yellow-200 text-yellow-800 px-2 py-1 rounded-full text-sm font-semibold ml-56"
          >Mã giảm 10k</span
        >
        <button class="ml-2 text-gray-500 hover:text-gray-700">
          <i class="fas fa-times"></i>
        </button>
      </div> -->
      <p class="text-gray-500 text-sm ml-56">
        Chỉ có thể áp dụng được 1 mã <i class="fas fa-info-circle"></i>
      </p>
    </div>

    <div class="bg-white p-4 rounded shadow-md mb-4">
      <h1 class="text-xl font-semibold mb-4">Kiểm tra lại đơn hàng</h1>
      
      <div class="border-t border-gray-200">
        <!-- Product 1 -->
        <c:forEach var="Item" items="${orderDetails}">
        <div class="flex items-center p-4 border-b border-gray-200">
          <img
            alt="${Item.bookName}"
            class="w-16 h-24 object-cover mr-4"
            height="90"
            src="${pageContext.request.contextPath}/book/image/${Item.bookId}"
            width="60"
          />
          <div class="flex-1">
            <h2 class="text-lg font-semibold"><c:out value="${Item.bookName}"/></h2>
            <p class="text-red-500 font-semibold originalPrice">
              <c:out value="${Item.discount} đ"/>
              <span class="line-through text-gray-500"><c:out value="${Item.price} đ"/> </span>
            </p>
          </div>
          <div class="flex items-center w-1/6">
            <span class="px-4 quantity"><c:out value="${Item.quantityOrder}"/> </span>
          </div>
          <p class="text-red-500 font-semibold ml-4 w-1/6"><c:out value="${Item.totalPrice} đ"/></p>
        </div>
        </c:forEach>
      </div>
      
    </div>

    <div class="bg-white p-4 rounded shadow-md mb-4">
      <div class="flex justify-between items-center border-b pb-4">
        <div>
          <p class="text-gray-600">Thành tiền</p>
          <p class="text-gray-600">Phí vận chuyển (Giao hàng tiêu chuẩn)</p>
          <p class="font-bold text-lg">Tổng Số Tiền (gồm VAT)</p>
        </div>
        <div class="text-right">
          <p class="text-gray-600">${order.price} đ</p>
          <p class="text-gray-600">32000 đ</p>
          <p class="font-bold text-lg text-red-600 totalPrice"><fmt:formatNumber value="${totalPrice}" pattern="#,##0" /> đ</p>
        </div>
      </div>
      <div class="flex items-center mt-4">
        <input
          type="checkbox"
          class="form-checkbox h-5 w-5 text-red-600"
          checked
        />
        <span class="ml-2 text-gray-600"
          >Bằng việc tiến hành Mua hàng, Bạn đã đồng ý với</span
        >
        <a href="#" class="ml-1 text-blue-600"
          >Điều khoản & Điều kiện của BookZone</a
        >
      </div>
      <div class="flex justify-between items-center mt-4">
        <div class="flex items-center text-red-600">
          <i class="fas fa-gift"></i>
          <span class="ml-2">Bạn chưa chọn món quà kèm theo đơn hàng</span>
          <i class="fas fa-chevron-right ml-2"></i>
        </div>
        <button class="bg-red-600 text-white px-6 py-2 rounded paymentButton">
          Xác nhận thanh toán
        </button>
      </div>
    </div>
   </div>
    <jsp:include page="../layout/footer.jsp"></jsp:include>

  </body>   
  <div id="overlay" class="hidden fixed inset-0 bg-black opacity-50 z-40"></div>
<div id="addressForm" class="hidden fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white p-8 rounded shadow-lg z-50 w-96">
    <h2 class="text-lg font-semibold mb-4">Thêm Địa Chỉ Giao Hàng Mới</h2>
    <form id="newAddressForm" method="post" action="add">
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="name">Họ và tên người nhận</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="name" placeholder="Nhập họ và tên người nhận" value="${user.name}" readonly />
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="phone">Số điện thoại</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="phone" placeholder="Ví dụ: 0979123xxx (10 ký tự số)" readonly value="${user.phone}" />
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="country">Quốc gia</label>
            <select class="w-full px-3 py-2 border rounded-lg" id="country">
                <option>Việt Nam</option>
            </select>
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="city">Tỉnh/Thành Phố</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="city" name="city" placeholder="Nhập địa chỉ Tỉnh/Thành Phố" />
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="district">Quận/Huyện</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="district" name="district" placeholder="Nhập địa chỉ Quận/Huyện" />
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="ward">Phường/Xã</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="ward" name="ward" placeholder="Nhập địa chỉ Phường/Xã" />
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="street">Địa chỉ nhận hàng</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="street" name="street" placeholder="Nhập địa chỉ nhận hàng" />
        </div>
        <div class="flex justify-end">
            <button type="button" id="cancelBtn" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded mr-2">Hủy</button>
            <button type="submit" id="submitBtn" class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Thêm địa chỉ</button>
        </div>
    </form>
</div>

<div id="overlay_update" class="hidden fixed inset-0 bg-black opacity-50 z-40"></div>
<div id="addressForm_update" class="hidden fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white p-8 rounded shadow-lg z-50 w-96">
    <h2 class="text-lg font-semibold mb-4">Cập nhật địa Chỉ Giao Hàng</h2>
    <form id="newAddressForm_update" method="post" action="update">
    <input type="hidden" id="addressId_update" name="address_id">
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="name">Họ và tên người nhận</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="name" placeholder="Nhập họ và tên người nhận" value="${user.name}" readonly />
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="phone">Số điện thoại</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="phone" placeholder="Ví dụ: 0979123xxx (10 ký tự số)" readonly value="${user.phone}" />
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="country">Quốc gia</label>
            <select class="w-full px-3 py-2 border rounded-lg" id="country">
                <option>Việt Nam</option>
            </select>
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="city_update">Tỉnh/Thành Phố</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="city_update" name="city_update" placeholder="Nhập địa chỉ Tỉnh/Thành Phố"/>
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="district_update">Quận/Huyện</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="district_update" name="district_update" placeholder="Nhập địa chỉ Quận/Huyện"/>
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="ward_update">Phường/Xã</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="ward_update" name="ward_update" placeholder="Nhập địa chỉ Phường/Xã"/>
        </div>
        <div class="mb-2">
            <label class="block text-gray-700 mb-2" for="street_update">Địa chỉ nhận hàng</label>
            <input class="w-full px-3 py-2 border rounded-lg" type="text" id="street_update" name="street_update" placeholder="Nhập địa chỉ nhận hàng"/>
        </div>
        <div class="flex justify-end">
            <button type="button" id="cancelBtn_update" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded mr-2">Hủy</button>
            <button type="submit" id="submitBtn_update" class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Cập nhật địa chỉ</button>
        </div>
    </form>
</div>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
   
   <script>
    document.addEventListener('DOMContentLoaded', function() {
        const addAddressBtn = document.querySelector('.fa-plus').parentElement;
        const addressForm = document.getElementById('addressForm');
        const submitBtn = document.getElementById('submitBtn');
        const cancelBtn = document.getElementById('cancelBtn');
        const overlay = document.getElementById('overlay');
        const newAddressForm = document.getElementById('newAddressForm');

        addAddressBtn.addEventListener('click', function() {
        	console.log("Nút đã được nhấn!");
            overlay.classList.remove('hidden');
            addressForm.classList.remove('hidden');
        });

        newAddressForm.addEventListener('submit', function(event) {
            // Lấy giá trị từ các trường nhập liệu
            const city = document.getElementById('city').value;
            const district = document.getElementById('district').value;
            const ward = document.getElementById('ward').value;
            const street = document.getElementById('street').value;
            // Kiểm tra xem các trường nhập liệu có trống không
            console.log(city);
            if (!city || !district || !ward || !street) {
                alert('Vui lòng nhập đầy đủ thông tin địa chỉ.');
                event.preventDefault(); // Ngăn chặn gửi form nếu có trường trống
                return;
            }
        });

        cancelBtn.addEventListener('click', function() {
            overlay.classList.add('hidden');
            addressForm.classList.add('hidden');
        });

        overlay.addEventListener('click', function() {
            overlay.classList.add('hidden');
            addressForm.classList.add('hidden');
        });
    });
    
    document.addEventListener('DOMContentLoaded', function() {
        const addressForm = document.getElementById('addressForm_update');
        const submitBtn = document.getElementById('submitBtn_update');
        const cancelBtn = document.getElementById('cancelBtn_update');
        const overlay = document.getElementById('overlay_update');
        const newAddressForm = document.getElementById('newAddressForm_update');

        document.querySelectorAll('[id^="updateAddress_"]').forEach(function(link) {
            link.addEventListener('click', function(event) {
                event.preventDefault(); // Ngăn chặn chuyển hướng mặc định

                
                
                const addressId = this.id.replace('updateAddress_', '');
				
                // Tìm thẻ label chứa thông tin địa chỉ
                const label = this.parentElement; // Lấy thẻ label cha
                const span = label.querySelector('span'); 
                if (span) {
                    // Lấy nội dung của thẻ span
                    const addressInfo = span.textContent;

                    // Tách thông tin địa chỉ
                    const parts = addressInfo.split('|')[1].split(',');
                    if (parts.length >= 4) {
                        const street = parts[0].trim();
                        const ward = parts[1].trim();
                        const district = parts[2].trim();
                        const province = parts[3].trim();
                        console.log(addressId);
                        // In thông tin địa chỉ ra console
                        document.getElementById('city_update').value = province;
                        document.getElementById('district_update').value = district;
                        document.getElementById('ward_update').value = ward;
                        document.getElementById('street_update').value = street;
                        document.getElementById('addressId_update').value = addressId;
                     // Hiển thị overlay_update
                        document.getElementById('overlay_update').classList.remove('hidden');
                        document.getElementById('addressForm_update').classList.remove('hidden');
                        
                    } else {
                        console.error("Không thể phân tích thông tin địa chỉ.");
                    }
                } else {
                    console.error("Không tìm thấy thẻ span chứa thông tin địa chỉ.");
                }
            });
        });

        newAddressForm.addEventListener('submit', function(event) {
            // Lấy giá trị từ các trường nhập liệu
            const city = document.getElementById('city_update').value;
            const district = document.getElementById('district_update').value;
            const ward = document.getElementById('ward_update').value;
            const street = document.getElementById('street_update').value;
            // Kiểm tra xem các trường nhập liệu có trống không
            console.log(city);
            if (!city || !district || !ward || !street) {
                alert('Vui lòng nhập đầy đủ thông tin địa chỉ.');
                event.preventDefault(); // Ngăn chặn gửi form nếu có trường trống
                return;
            }
        });

        cancelBtn.addEventListener('click', function() {
            overlay.classList.add('hidden');
            addressForm.classList.add('hidden');
        });

        overlay.addEventListener('click', function() {
            overlay.classList.add('hidden');
            addressForm.classList.add('hidden');
        });
    });
    document.addEventListener('DOMContentLoaded', function() {
    	  const confirmPaymentButton = document.querySelector('.paymentButton');
    	  const addressRadios = document.querySelectorAll('input[name="address"]');
    	  const totalPriceElement = document.querySelector('.totalPrice');
    	  const paymentRadios = document.querySelectorAll('input[name="payment"]');

    	const totalPriceText = totalPriceElement.textContent;
    	const totalPriceValue = totalPriceText.replace(' đ', '').replace(/,/g, '');
    	const totalPriceNumber = parseFloat(totalPriceValue);
    	  confirmPaymentButton.addEventListener('click', function() {
    	    let addressSelected = false;
    	    let selectedAddressId = null;
    	    let paymentMethod = null;
    	    addressRadios.forEach(radio => {
    	      if (radio.checked) {
    	        addressSelected = true;
    	        selectedAddressId = radio.value;
    	      }
    	    });
    	    
    	    paymentRadios.forEach(radio => {
                if (radio.checked) {
                    paymentMethod = radio.nextElementSibling.nextElementSibling.textContent.trim(); // Get the text next to the image
                }
            });

    	    if (!addressSelected) {
                alert('Vui lòng chọn địa chỉ giao hàng.');
            } else {
                if (selectedAddressId) {
                	if (paymentMethod === 'VNPay') {
                        // Redirect to VNPay payment page
                        sendDataToControllerVNPay(selectedAddressId, totalPriceNumber);
                        window.location.href = "/MockProject/create_payment"; // Replace with your VNPay payment URL
                    } else if (paymentMethod === 'Thanh toán bằng tiền mặt khi nhận hàng') {
                        // Redirect to home page for cash on delivery
                        sendDataToController(selectedAddressId, totalPriceNumber);
                        window.location.href = "/MockProject/home";
                        alert('Thanh toán đơn hàng thành công!');
                    } else {
                        alert('Phương thức thanh toán không hợp lệ.');
                    }
                } else {
                    alert('Không tìm thấy ID địa chỉ.');
                }
            }
    	  });
    	  
		  	
		    function sendDataToController(AddressId, totalPrice) {
		        const params = new URLSearchParams();
		        params.append('AddressId', AddressId);
		        params.append('totalPrice', totalPrice);

		        fetch("complete", {
		            method: "POST",
		            headers: { "Content-Type": "application/x-www-form-urlencoded" },
		            body: params.toString()
		        })
		        .then(response => {
		            if (!response.ok) throw new Error("Lỗi mạng: " + response.statusText);
		            return response.text();
		        })
		        .then(data => console.log("Thành công:", data))
		        .catch(error => console.error("Lỗi:", error));
		    }

		    function sendDataToControllerVNPay(AddressId, totalPrice) {
		        const params = new URLSearchParams();
		        params.append('AddressId', AddressId);
		        params.append('totalPrice', totalPrice);

		        const xhr = new XMLHttpRequest();
		        xhr.open("POST", "complete_VNPay", true);
		        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		        xhr.onreadystatechange = function() {
		            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
		                console.log("Thành công:", xhr.responseText);
		            }
		        };

		        xhr.onerror = function() {
		            console.error("Lỗi mạng!");
		        };

		        xhr.send(params.toString());
		    }
		}); // Đóng đúng sự kiện DOMContentLoaded


</script>
