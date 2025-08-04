package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Order;
import model.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUser(User user);

	@Query("SELECT SUM(o.totalPrice) FROM Order o")	
	Integer sumTotalPrice();

}
