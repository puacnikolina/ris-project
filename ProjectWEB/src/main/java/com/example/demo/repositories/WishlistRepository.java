package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Product;
import model.User;
import model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
	Wishlist findByUser(User user);

	List<Wishlist> findByProductsContaining(Product p);
}
	