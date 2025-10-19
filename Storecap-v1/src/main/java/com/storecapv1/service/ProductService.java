package com.storecapv1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.storecapv1.dto.ProductDto;
import com.storecapv1.entity.Products;
import com.storecapv1.repository.IProductRepositrory;

@Service
public class ProductService {

	@Autowired
	private IProductRepositrory productRepositrory;
	
	
	public List<Products> registerProduct(List<ProductDto> productDto) {
		
		List<Products> finalProducts = new ArrayList<>();
		
		for(ProductDto products : productDto) {
			Products product = new Products();
			BeanUtils.copyProperties(products, product);
			
			finalProducts.add(product);
		}
		
		productRepositrory.saveAll(finalProducts);
		return finalProducts;
	}


	public Page<Products> getProducts(String category, Pageable pageable) {
		if(category!=null && !category.isEmpty()) {
			return productRepositrory.findByCategoryContainingIgnoreCase(category, pageable);
		}
		
		return productRepositrory.findAll(pageable);
	}
	
	
	
}
