package com.example.demo.controllers;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.OrdersHasProductRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import model.Order;
import model.OrdersHasProduct;
import model.OrdersHasProductPK;
import model.Product;
import model.User;
import model.Wishlist;

@Controller
@RequestMapping("/order/")
public class OrderController {

	@Autowired
	ProductRepository pr;

	@Autowired
	OrderRepository or;

	@Autowired
	OrdersHasProductRepository ohpr;

	@Autowired
	UserRepository ur;

	@GetMapping("getCartPage")
	public String getCartPage(HttpSession session, Model model) {
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}

		List<Product> products = pr.findAllById(cart.keySet());
		Map<Product, Integer> cartItems = new LinkedHashMap<>();

		for (Product p : products) {
			cartItems.put(p, cart.get(p.getIdProduct()));
		}

		model.addAttribute("cartItems", cartItems);

		return "user/cartPage";
	}

	@GetMapping("/getOrdersPage")
	public String getOrdersPage(Model model, Principal principal) {
		User user = ur.findByUsername(principal.getName());
		List<Order> orders = or.findByUser(user);
		model.addAttribute("orders", orders);
		return "user/ordersPage";
	}
	
	@GetMapping("getOrdersOverview")
	public String getOrdersOverview(Model model) {
	    List<Order> orders = or.findAll();
	    model.addAttribute("orders", orders);
	    return "admin/ordersOverview";
	}


	@PostMapping("addToCart")
	public String addToCart(@RequestParam int idProduct, @RequestParam(defaultValue = "1") int quantity,
			HttpSession session, RedirectAttributes redirectAttributes) {

		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}

		Product product = pr.findById(idProduct).orElse(null);
		int stock = product.getQuantity();
		int currentQty = cart.getOrDefault(idProduct, 0);
		int newQty = currentQty + quantity;

		if (newQty > stock) {
			redirectAttributes.addFlashAttribute("error","There are not enough products in stock." + stock + " Right now in your cart u have: " + currentQty);
		} else {
			cart.put(idProduct, newQty);
			redirectAttributes.addFlashAttribute("success", "Product is added to cart");
		}
		return "redirect:/product/getProductsDetails?id=" + idProduct;
	}

	@PostMapping("/removeFromCart")
	public String removeFromCart(@RequestParam int productId, HttpSession session) {
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

		if (cart != null) {
			cart.remove(productId);
			session.setAttribute("cart", cart);
		}

		return "redirect:/order/getCartPage";
	}

	@PostMapping("/checkout")
	public String checkout(HttpSession session, Principal principal) {
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

		User user = ur.findByUsername(principal.getName());

		Order order = new Order();
		Date today = new Date();

		order.setUser(user);
		order.setOrderDate(today);
		order.setStatus("ORDERED");
		order.setDeliveryDate(calculateDeliveryDate(today));
		or.save(order);

		int totalPrice = 0;
		List<Product> products = pr.findAllById(cart.keySet());

		for (Product product : products) {
			int qty = cart.get(product.getIdProduct());

			int newStock = product.getQuantity() - qty;
			product.setQuantity(newStock);
			pr.save(product);

			OrdersHasProduct oProduct = new OrdersHasProduct();
			OrdersHasProductPK pk = new OrdersHasProductPK();
			pk.setOrders_idOrder(order.getIdOrder());
			pk.setProduct_idProduct(product.getIdProduct());

			oProduct.setId(pk);
			oProduct.setOrder(order);
			oProduct.setProduct(product);
			oProduct.setQuantity(qty);

			ohpr.save(oProduct);

			totalPrice += product.getPrice() * qty;
		}

		order.setTotalPrice(totalPrice);
		or.save(order);

		session.removeAttribute("cart");

		return "redirect:/order/getOrdersPage";
	}

	private Date calculateDeliveryDate(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		Date deliveryDate = calendar.getTime();
		return deliveryDate;
	}
	
	@PostMapping("/updateStatus")
	public String updateOrderStatus(@RequestParam int orderId, @RequestParam String newStatus) {
	    Order order = or.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
	    order.setStatus(newStatus);
	    or.save(order);
	    return "redirect:/order/getOrdersOverview";
	}


}
