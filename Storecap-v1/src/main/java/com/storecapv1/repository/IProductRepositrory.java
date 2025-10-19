package com.storecapv1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.storecapv1.entity.Products;


public interface IProductRepositrory extends JpaRepository<Products, Long> {

	Page<Products> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
}