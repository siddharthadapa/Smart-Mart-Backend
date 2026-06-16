package com.vcube.smart_mart.service;

import com.vcube.smart_mart.entity.Cart;

public interface CartService {

	Cart getCartByUser(Long userId);

	String addToCart(Long userId, Long productId, int quantity);

}
