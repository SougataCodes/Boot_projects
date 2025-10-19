package com.storecapv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecapv1.dto.OrderDto;
import com.storecapv1.entity.OrderSummary;
import com.storecapv1.entity.Orders;
import com.storecapv1.service.OrderService;
import com.storecapv1.utils.APIResponse;

@Controller
@RequestMapping("/api/storecap/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	@PostMapping("/createOrder")
	public ResponseEntity<APIResponse<Orders>> newOrder(@RequestBody OrderDto orderDto) {
		
		Orders order = orderService.createOrder(orderDto);
		
		APIResponse<Orders> response = new APIResponse<Orders>();
		if(order!=null) {
			response.setMessage("Order placed successfully");
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(order);
			
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
		
		response.setMessage("Order failed");
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setData(order);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	
	@PutMapping("{orderId}/confirm")
	public ResponseEntity<APIResponse<OrderSummary>> confirmOrder(@PathVariable String orderId) {
		
		OrderSummary confirmedOrder = orderService.confirmOrder(orderId);
		
		APIResponse<OrderSummary> response = new APIResponse<OrderSummary>();
		response.setMessage("Order confirmed");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(confirmedOrder);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
