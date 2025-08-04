package com.example.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WishlistRepository;

import model.Product;
import model.User;
import model.Wishlist;

@Controller
@RequestMapping("/wishlist/")
public class WishlistController {

	@Autowired
	private WishlistRepository wr;

	@Autowired
	private ProductRepository pr;

	@Autowired
	private UserRepository ur;
	
	
	@GetMapping("/getWishlistPage")
	public String getWishlistPage(Model model, Principal principal) {
		User user = ur.findByUsername(principal.getName());
		model.addAttribute("products", user.getWishlist().getProducts());
		return "user/wishlistPage";
	}

	@PostMapping("/addToWishlist")
	public String addToWishlist(@RequestParam int idProduct, Principal principal, RedirectAttributes redirectAttributes) {
		User user = ur.findByUsername(principal.getName());
		Wishlist wishlist = user.getWishlist();
		Product product = pr.findById(idProduct).orElse(null);
		
		if (wishlist.getProducts().contains(product)) {
	        redirectAttributes.addFlashAttribute("info", "Product is already in wishlist.");
	    } else {
	        wishlist.getProducts().add(product);
	        wr.save(wishlist);
	        redirectAttributes.addFlashAttribute("success", "Product added to wishlist.");
	    }
		 return "redirect:/product/getProductsDetails?id=" + idProduct;
	}
	
	@PostMapping("/removeFromWishlist")
	public String removeFromWishlist(@RequestParam int productId, Principal principal) {
	    User user = ur.findByUsername(principal.getName());
	    Wishlist wishlist = user.getWishlist();

	    if (wishlist != null && wishlist.getProducts() != null) {
	        wishlist.getProducts().removeIf(p -> p.getIdProduct() == productId);
	        wr.save(wishlist);
	    }

	    return "redirect:/wishlist/getWishlistPage";
	}
	
	
}
