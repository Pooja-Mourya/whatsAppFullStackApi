package com.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panel.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
