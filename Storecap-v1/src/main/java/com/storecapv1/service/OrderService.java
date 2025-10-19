package com.storecapv1.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.storecapv1.dto.OrderDto;
import com.storecapv1.entity.OrderSummary;
import com.storecapv1.entity.Orders;
import com.storecapv1.entity.Products;
import com.storecapv1.entity.Users;
import com.storecapv1.enums.OrderStatus;
import com.storecapv1.repository.IOrderRepositroy;
import com.storecapv1.repository.IOrderSummaryRepository;
import com.storecapv1.repository.IProductRepositrory;
import com.storecapv1.repository.IUserRepository;

@Service
public class OrderService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IOrderRepositroy orderRepositroy;
	
	@Autowired
	private IProductRepositrory productRepositrory;
	
	@Autowired
	private IOrderSummaryRepository orderSummaryRepository;
	
	@Transactional
	public Orders createOrder(OrderDto orderDto) {
		
		Users user = userRepository.findByUniqueIdContainingIgnoreCase(orderDto.getUserId());
		
		if(user == null)
			throw new RuntimeException("User not found");
		
		Products product = productRepositrory.findById(
					orderDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
		
		if (orderDto.getQuantity() > product.getAvailableQuantity())
	        throw new RuntimeException("Insufficient Stock");

		
		Orders order = new Orders();
		String uniqueId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7).toUpperCase();
		
		BeanUtils.copyProperties(orderDto, order);
		order.setOrderId(uniqueId);
		order.setUsers(user);
		order.setBillAmount(orderDto.getQuantity()*product.getPrice());
		order.setStatus(OrderStatus.pending);
		
		int finalQty = product.getAvailableQuantity() - orderDto.getQuantity();
		product.setAvailableQuantity(finalQty);
		
		orderRepositroy.save(order);
		return order;		
	}


	@Transactional
	public OrderSummary confirmOrder(String orderId) {

		Orders order = orderRepositroy.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		
		if (!OrderStatus.pending.equalsIgnoreCase(order.getStatus())) {
	        throw new RuntimeException("Order already confirmed or invalid state");
	    }
		
		Products product = productRepositrory.findById(order.getProductId())
	            .orElseThrow(() -> new RuntimeException("Product not found"));
		
		int remainingQuantity = product.getAvailableQuantity() - order.getQuantity();;
		if (remainingQuantity < 0) {
	        throw new RuntimeException("Stock Out!!");
	    }
		
		product.setAvailableQuantity(remainingQuantity);
		
		order.setStatus(OrderStatus.confirm);
		
		productRepositrory.save(product);
		orderRepositroy.save(order);
		
		
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setOrder(order);
		orderSummary.setStatus(OrderStatus.confirm);
		orderSummary.setMessage("Order will be shipped soon!");
		
		orderSummaryRepository.save(orderSummary);
		
		return orderSummary;
	}
}
