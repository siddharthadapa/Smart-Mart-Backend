package com.vcube.smart_mart.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vcube.smart_mart.entity.Address;
import com.vcube.smart_mart.entity.Cart;
import com.vcube.smart_mart.entity.CartItem;
import com.vcube.smart_mart.entity.Order;
import com.vcube.smart_mart.entity.OrderItem;
import com.vcube.smart_mart.entity.Product;
import com.vcube.smart_mart.entity.User;
import com.vcube.smart_mart.repository.AddressRepository;
import com.vcube.smart_mart.repository.CartItemRepository;
import com.vcube.smart_mart.repository.CartRepository;
import com.vcube.smart_mart.repository.OrderRepository;
import com.vcube.smart_mart.repository.ProductRepository;
import com.vcube.smart_mart.repository.UserRepository;
import com.vcube.smart_mart.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final ProductRepository productRepository;

	@Override
	@Transactional
	public Order placeOrder(Long userId, Long addressId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new RuntimeException("Address Not Found"));

		Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart Not Found"));

		if (cart.getItems() == null || cart.getItems().isEmpty()) {
			throw new RuntimeException("Cart Empty");
		}

		Order order = new Order();

		order.setUser(user);
		order.setAddress(address);
		order.setOrderDate(LocalDateTime.now());
		order.setStatus("PLACED");

		List<OrderItem> orderItems = new ArrayList<>();

		double totalAmount = 0;

		for (CartItem cartItem : cart.getItems()) {

			Product product = cartItem.getProduct();

			if (product.getStock() < cartItem.getQuantity()) {

				throw new RuntimeException("Out Of Stock : " + product.getName());
			}

			product.setStock(product.getStock() - cartItem.getQuantity());

			productRepository.save(product);

			OrderItem orderItem = new OrderItem();

			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(product.getPrice());

			orderItems.add(orderItem);

			totalAmount += product.getPrice() * cartItem.getQuantity();
		}

		order.setOrderItems(orderItems);

		order.setTotalAmount(totalAmount);

		Order savedOrder = orderRepository.save(order);

		cartItemRepository.deleteByCartId(cart.getId());

		return savedOrder;
	}

	@Override
	public List<Order> getOrdersByUser(Long userId) {

		return orderRepository.findByUserId(userId);
	}

	@Override
	public Order getOrderById(Long orderId) {

		return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
	}

	@Override
	@Transactional
	public String cancelOrder(Long orderId) {

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));

		if ("CANCELLED".equals(order.getStatus())) {

			return "Already Cancelled";
		}

		for (OrderItem item : order.getOrderItems()) {

			Product product = item.getProduct();

			product.setStock(product.getStock() + item.getQuantity());

			productRepository.save(product);
		}

		order.setStatus("CANCELLED");

		orderRepository.save(order);

		return "Order Cancelled Successfully";
	}
}