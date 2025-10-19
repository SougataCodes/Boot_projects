package com.storecapv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storecapv1.entity.Orders;

public interface IOrderRepositroy extends JpaRepository<Orders, String> {

}
