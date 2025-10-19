package com.storecapv1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.storecapv1.dto.ProductDto;
import com.storecapv1.entity.Products;
import com.storecapv1.service.ProductService;
import com.storecapv1.utils.APIResponse;

@Controller
@RequestMapping("/api/storecap/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/registerProduct")
	public ResponseEntity<APIResponse<List<Products>>> addProduct(@RequestBody List<ProductDto> productDto) {
		
		List<Products> registerProduct = productService.registerProduct(productDto);
		
		APIResponse<List<Products>> response = new APIResponse<>();
		response.setMessage("Product registered successfull");
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setData(registerProduct);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<APIResponse<Page<Products>>> fetchProducts(@RequestParam(required = false) String category, Pageable pageable) {
		
		Page<Products> showProduct = productService.getProducts(category, pageable);
		
		APIResponse<Page<Products>> response = new APIResponse<>();
		response.setMessage("Product registered successfull");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(showProduct);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
