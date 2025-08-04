package com.example.demo.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SongRepository;
import com.example.demo.repositories.WishlistRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Artist;
import model.Category;
import model.Product;
import model.Wishlist;

@Controller
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	ProductRepository pr;
	
	@Autowired
	CategoryRepository cr;
	
	@Autowired
	ArtistRepository ar;
	
	@Autowired
	SongRepository sr;
	
	@Autowired
	WishlistRepository wr;
	
	@ModelAttribute("newProduct")
	public Product getNewProduct() {
		return new Product();
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return cr.findAll();
	}
	
	@ModelAttribute("artists")
	public List<Artist> getArtists(){
		return ar.findAll();
	}
	
	@GetMapping("getProductsUser")
	public String getProductsUser(Model model) {
	    List<Product> products = pr.findAll();
	    model.addAttribute("products", products);
	    return "user/productOverviewUser";
	}

	@GetMapping("getProductsUnregistered")
	public String getProductsUnregistered(Model model) {
	    List<Product> products = pr.findAll();
	    model.addAttribute("products", products);
	    return "productOverview";
	}

	@GetMapping("getProductsAdmin")
	public String getProductsAdmin(Model model) {
	    List<Product> products = pr.findAll();
	    model.addAttribute("products", products);
	    return "admin/productOverviewAdmin";
	}

	@GetMapping("getProductsDetails")
	public String getProductsDetails(@RequestParam int id, Model model) {
	    Product p = pr.findById(id).orElse(null);

	    if (p != null) {
	        model.addAttribute("productDetails", p);
	    }

	    return "user/productDetails";
	}

	
	@GetMapping("getEditPage")
	public String getEditPage(@RequestParam int id, Model model) {
		Product product = pr.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		model.addAttribute("product", product);
		return "admin/editProduct";
	}
	
	
	@GetMapping("getAddProductsPage")
	public String getAddProductsPage() {
	    return "admin/addProduct";
	}

	@PostMapping("/update")
	public String updateProduct(@ModelAttribute("product") Product updatedProduct, @RequestParam("image") MultipartFile file) throws IOException  {
		Product product = pr.findById(updatedProduct.getIdProduct()).orElseThrow(() -> new RuntimeException("Product not found"));
		
		product.setName(updatedProduct.getName());
	    product.setPrice(updatedProduct.getPrice());
	    product.setQuantity(updatedProduct.getQuantity());
	    product.setCategory(updatedProduct.getCategory());
	    product.setArtist(updatedProduct.getArtist());
	    product.setDescription(updatedProduct.getDescription());
	    product.setRating(updatedProduct.getRating());
	    product.setReleaseDate(updatedProduct.getReleaseDate());
	    
	    if (file != null && !file.isEmpty()) {
	        product.setImg(file.getBytes());
	    }
	    
	    pr.save(product);
	   
	    return "redirect:/product/getProductsAdmin"; 
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@PostMapping("/delete")
	public String deleteProduct(@RequestParam int id) {
		Product p = pr.findById(id).orElse(null);
		
		List<Wishlist> wishlistsContainingProduct = wr.findByProductsContaining(p);
		for (Wishlist wishlist : wishlistsContainingProduct) {
            wishlist.getProducts().remove(p);
            wr.save(wishlist); 
        }
		
		
		pr.deleteById(id);
		
	    return "redirect:/product/getProductsAdmin"; 
	}

	@PostMapping("save")
	public String saveProduct(@ModelAttribute("newProduct") Product product,
	                          @RequestParam("image") MultipartFile file) throws IOException {
	    if (file != null && !file.isEmpty()) {
	        product.setImg(file.getBytes());
	    }

	    pr.save(product);
	    return "redirect:/product/getAddProductsPage";   
	}
	
	@GetMapping("/image/{id}")
	public void showImage(@PathVariable int id, HttpServletResponse response) throws IOException {
	    Product product = pr.findById(id).orElse(null);

	    if (product != null && product.getImg() != null) {
	        response.setContentType("image/jpeg");
	        response.getOutputStream().write(product.getImg());
	        response.getOutputStream().close();
	    }
	}



}
