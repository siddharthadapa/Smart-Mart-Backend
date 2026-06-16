package com.vcube.smart_mart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.smart_mart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findByUserId(Long userId);
}