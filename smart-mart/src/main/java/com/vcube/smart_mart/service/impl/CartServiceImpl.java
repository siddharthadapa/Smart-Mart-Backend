package com.vcube.smart_mart.service.impl;

import org.springframework.stereotype.Service;

import com.vcube.smart_mart.entity.Cart;
import com.vcube.smart_mart.entity.CartItem;
import com.vcube.smart_mart.entity.Product;
import com.vcube.smart_mart.repository.CartItemRepository;
import com.vcube.smart_mart.repository.CartRepository;
import com.vcube.smart_mart.repository.ProductRepository;
import com.vcube.smart_mart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public Cart getCartByUser(Long userId) {

		return cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart Not Found"));
	}

	public String addToCart(Long userId, Long productId, int quantity) {

		userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Not Found"));

		Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {

			Cart c = new Cart();
			c.setUserId(userId);

			return cartRepository.save(c);
		});

		CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId).orElse(null);

		if (item != null) {

			item.setQuantity(item.getQuantity() + quantity);

		} else {

			item = new CartItem();

			item.setCart(cart);

			item.setProduct(product);

			item.setQuantity(quantity);
		}

		cartItemRepository.save(item);

		return "Added To Cart";
	}
}