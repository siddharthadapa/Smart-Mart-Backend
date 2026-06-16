package com.vcube.smart_mart.service;

import java.util.List;

import com.vcube.smart_mart.entity.Order;

public interface OrderService {

	Order placeOrder(Long userId, Long addressId);

	List<Order> getOrdersByUser(Long userId);

	Order getOrderById(Long orderId);

	String cancelOrder(Long orderId);
}