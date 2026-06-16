package com.vcube.smart_mart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.smart_mart.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(Long userId);

}
