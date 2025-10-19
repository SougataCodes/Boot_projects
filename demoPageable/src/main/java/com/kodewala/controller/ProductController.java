package com.kodewala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodewala.dto.ProductDto;
import com.kodewala.entity.Products;
import com.kodewala.service.ProductService;
import com.kodewala.utils.APIResponse;

@RestController
@RequestMapping("/demo/api/")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<APIResponse<Products>> addProduct(@RequestBody ProductDto productDto) {
		
		Products product = productService.addProduct(productDto);
		
		APIResponse<Products> response = new APIResponse<>();
		response.setMessage("Product successfully added");
		response.setStatusCode(201);
		response.setData(product);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping
	public ResponseEntity<APIResponse<Page<Products>>> fetchByCategory(@RequestParam(required = false) String category, Pageable pageable){
		
		Page<Products> page = productService.fetchProductByCategory(category, pageable);
		
		APIResponse<Page<Products>> response = new APIResponse<>();
		response.setMessage("Product page");
		response.setStatusCode(200);
		response.setData(page);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
