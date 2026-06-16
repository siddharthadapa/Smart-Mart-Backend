package com.vcube.smart_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.smart_mart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
