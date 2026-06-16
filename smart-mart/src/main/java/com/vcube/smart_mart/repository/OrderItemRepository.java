package com.vcube.smart_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.smart_mart.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
