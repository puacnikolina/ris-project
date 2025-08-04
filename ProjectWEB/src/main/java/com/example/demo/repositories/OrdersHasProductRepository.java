package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.OrdersHasProduct;
import model.OrdersHasProductPK;

public interface OrdersHasProductRepository extends JpaRepository<OrdersHasProduct, OrdersHasProductPK>{

}
