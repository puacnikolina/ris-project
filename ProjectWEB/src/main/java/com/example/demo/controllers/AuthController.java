package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WishlistRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;
import model.User;
import model.Wishlist;

@Controller
@RequestMapping("/auth/")
public class AuthController {

	@Autowired
	UserRepository ur;

	@Autowired
	RoleRepository rr;

	@Autowired
	WishlistRepository wr;

	@Autowired
	PasswordEncoder passwordEncoder;

	@ModelAttribute("roles")
	public List<Role> getRoles() {
		return rr.findAll();
	}

	@ModelAttribute("user")
	public User newUser() {
		return new User();
	}

	@RequestMapping(value = "loginPage", method = RequestMethod.GET)
	public String loginPage() {
		return "loginPage";
	}

	@RequestMapping(value = "registerPage", method = RequestMethod.GET)
	public String registerPage() {
		return "registerPage";
	}

	@PostMapping("saveUser")
	public String saveUser(@ModelAttribute("user") User u, Model model) {
		
		if (ur.existsByUsername(u.getUsername())) {
	        model.addAttribute("errorMessage", "Username already taken, please choose another.");
	        return "registerPage"; 
	    }
		
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		u.setRole(rr.findByName("USER"));
		try {
			User user = ur.save(u);
			createWishlist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginPage";
	}
	
	private void createWishlist(User user) {
		if (user.getRole().getName().equalsIgnoreCase("USER")) {
	        Wishlist wl = new Wishlist();
	        wl.setUser(user);
	        wl.setProducts(new ArrayList<>());

	        user.setWishlist(wl); 
	        wr.save(wl); 
	    }
	}

	

	@GetMapping("/default")
	public String defaultAfterLogin(HttpServletRequest request) {

		if (request.isUserInRole("ADMIN")) {
			return "admin/adminPage";
		}
		return "user/userPage";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	    request.logout();
	    request.getSession().invalidate(); 
	    return "redirect:/index.jsp"; 
	}
}
