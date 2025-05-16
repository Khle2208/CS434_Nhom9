package com.nhom9.controller;

import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fa.mockproject.model.*;
import fa.mockproject.service.*;
@Controller
public class PaymentController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("payment")
    @ResponseBody
    public String handleCartRequest(
            @RequestParam("total_price") BigDecimal total_price,
            @RequestParam("selectedProductIds") String selectedProductIds,
            HttpSession session){ 
		String id_User = (String) session.getAttribute("userid");
		if(userService.findByUserId(UUID.fromString(id_User)) != null) {
			System.out.println("get UserId successful");
		}
		System.out.println("check productId: " + selectedProductIds );
		String[] productIdsArray = selectedProductIds.split(",");
		List<UUID> productUuidList = new ArrayList<>();
		for (String productId : productIdsArray) {
			try {
				UUID uuid = UUID.fromString(productId.trim()); // Trim to remove leading/trailing spaces
				productUuidList.add(uuid);
			} catch (IllegalArgumentException e) {
				System.err.println("Invalid UUID format: " + productId);
			}
		}
		
		System.out.println("check userId: " + session.getAttribute("userid") );
		UUID cartID = cartService.getCartIDbyUserID(id_User);
		UUID orderID = orderService.addOrderbyId(id_User, total_price, productUuidList, cartID);
		System.out.println(orderID);
		session.setAttribute("orderID", orderID);
	    return "content/payment";
    }
	@GetMapping("payment")
	public String viewPayment(Model model, HttpSession session) {
		UUID orderID = (UUID) session.getAttribute("orderID");
		System.out.println(orderID);
		ArrayList<OrderDetail> listItems = orderDetailService.getItemsByOrderID(orderID);
		model.addAttribute("orderDetails", listItems); 
		
		Order order = orderService.getOrderByID(orderID);
	    BigDecimal shippingFee = new BigDecimal("32000"); // Phí vận chuyển
	    BigDecimal totalPrice = order.getPrice().add(shippingFee); // Tính tổng tiền

	    model.addAttribute("order", order);
	    model.addAttribute("totalPrice", totalPrice);
	    session.setAttribute("totalPrice", totalPrice);
	    
	    String id_user = (String)session.getAttribute("userid");

		System.out.println("check userId: " + session.getAttribute("userid") );
	    UUID uuid_id_user = UUID.fromString(id_user.trim());
	    ArrayList<Address> listAddress = addressService.getAllAddressByUserId(uuid_id_user);
	    model.addAttribute("listAddress", listAddress); 
	    
	    User user = userService.getUserByID(uuid_id_user);
	    model.addAttribute("user", user); 
	    
	    
		return "content/payment";
	}
	
	@PostMapping("/add")
	@ResponseBody
    public ModelAndView addAddress(
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam("ward") String ward,
            @RequestParam("street") String street,
            HttpSession session) {

            String id_user = (String)session.getAttribute("userid");
    	    UUID uuid_id_user = UUID.fromString(id_user.trim());
    	    addressService.addAddressByUserId(uuid_id_user, city, district, ward, street);
    	    
            return new ModelAndView(new RedirectView("payment")); 
    }
	
	@GetMapping("/delete")
	@ResponseBody
    public ModelAndView deleteAddress(@RequestParam("address_id") UUID addressId) {
		addressService.deleteAddressById(addressId);
		return new ModelAndView(new RedirectView("payment")); 
    }
	
	@PostMapping("/update")
	@ResponseBody
    public ModelAndView updateAddress(@RequestParam("address_id") UUID addressId,
    		@RequestParam("city_update") String city,
            @RequestParam("district_update") String district,
            @RequestParam("ward_update") String ward,
            @RequestParam("street_update") String street) {
        
		addressService.updateAddressById(addressId, city, district, ward, street);
		return new ModelAndView(new RedirectView("payment")); 
    }
	
	@PostMapping("complete")
    @ResponseBody
    public String handleCartRequest(
            @RequestParam("AddressId") UUID AddressId,
            @RequestParam("totalPrice") BigDecimal totalPrice,
            HttpSession session) { // Sử dụng Double
		
		String id_user = (String)session.getAttribute("userid");
	    UUID uuid_id_user = UUID.fromString(id_user.trim());
	    UUID orderID = (UUID) session.getAttribute("orderID");
	    
	    String payment_method = "Cash";
	    paymentService.completePayment(uuid_id_user, orderID, totalPrice, AddressId,payment_method);
	    String status = "Đang giao";
	    orderService.updateStatus(orderID, status);
        return "content/home";
    }
}
