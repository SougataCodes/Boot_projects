package com.kodewala.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kodewala.entity.Products;

public interface IProductsRepository extends JpaRepository<Products, Long> {

	Page<Products> findAllByCategoryContainingIgnoreCase(String category, Pageable pageable);
}
