package com.clicknshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicknshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
