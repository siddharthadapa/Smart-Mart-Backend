package com.vcube.smart_mart.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.smart_mart.entity.Order;
import com.vcube.smart_mart.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/place/{userId}/{addressId}")
	public Order placeOrder(@PathVariable Long userId, @PathVariable Long addressId) {

		return orderService.placeOrder(userId, addressId);
	}

	@GetMapping("/user/{userId}")
	public List<Order> getOrdersByUser(@PathVariable Long userId) {

		return orderService.getOrdersByUser(userId);
	}

	@GetMapping("/{orderId}")
	public Order getOrderById(@PathVariable Long orderId) {

		return orderService.getOrderById(orderId);
	}

	@PutMapping("/cancel/{orderId}")
	public String cancelOrder(@PathVariable Long orderId) {

		return orderService.cancelOrder(orderId);
	}
}