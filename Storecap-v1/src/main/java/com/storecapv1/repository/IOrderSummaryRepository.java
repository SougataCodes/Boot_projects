package com.storecapv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecapv1.entity.OrderSummary;

public interface IOrderSummaryRepository extends JpaRepository<OrderSummary, Long> {

}
