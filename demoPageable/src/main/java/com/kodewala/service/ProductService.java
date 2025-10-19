package com.kodewala.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodewala.dto.ProductDto;
import com.kodewala.entity.Products;
import com.kodewala.repository.IProductsRepository;

@Service
public class ProductService {

	@Autowired
	private IProductsRepository productsRepository;
	
	
	public Page<Products> fetchProductByCategory(String category, Pageable pageable) {
		
		if(category!=null && !category.isEmpty())
			return productsRepository.findAllByCategoryContainingIgnoreCase(category, pageable);
		
		return productsRepository.findAll(pageable);
	}
	
	
	public Products addProduct(ProductDto productDto) {
		Products product = new Products();
		BeanUtils.copyProperties(productDto, product);
		
		productsRepository.save(product);
		return product;
	}
}
