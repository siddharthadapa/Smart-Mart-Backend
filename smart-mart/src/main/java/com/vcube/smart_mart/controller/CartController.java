package com.vcube.smart_mart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.smart_mart.entity.Cart;
import com.vcube.smart_mart.service.impl.CartServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

	private final CartServiceImpl cartService;

	@GetMapping("/{userId}")
	public Cart getCart(@PathVariable Long userId) {

		return cartService.getCartByUser(userId);
	}

	@PostMapping("/add")
	public String addToCart(

			@RequestParam Long userId,

			@RequestParam Long productId,

			@RequestParam int quantity) {

		return cartService.addToCart(userId, productId, quantity);
	}
}