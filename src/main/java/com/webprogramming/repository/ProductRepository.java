package com.webprogramming.repository;

import com.webprogramming.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByProductName(String productName);
}
