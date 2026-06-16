package com.vcube.smart_mart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vcube.smart_mart.entity.Category;
import com.vcube.smart_mart.entity.Product;
import com.vcube.smart_mart.repository.CategoryRepository; // Added import
import com.vcube.smart_mart.repository.ProductRepository;
import com.vcube.smart_mart.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	// 1. Declare CategoryRepository here so it can be resolved and injected
	private final CategoryRepository categoryRepository;

	@Override
	public Product addProduct(Product product) {
		// 2. Fetch the category safely using the repository instance
		if (product.getCategory() != null && product.getCategory().getId() != null) {
			Category category = categoryRepository.findById(product.getCategory().getId())
					.orElseThrow(() -> new RuntimeException("Category Not Found"));
			product.setCategory(category);
		}
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Product existing = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product Not Found inside system logs for ID: " + id));

		existing.setName(product.getName());
		existing.setDescription(product.getDescription());
		existing.setPrice(product.getPrice());
		existing.setStock(product.getStock());
		existing.setImageUrl(product.getImageUrl());

		// Safety check: Resolves category maps cleanly without overriding your database
		// columns with null elements
		if (product.getCategory() != null && product.getCategory().getId() != null) {
			Category category = categoryRepository.findById(product.getCategory().getId())
					.orElseThrow(() -> new RuntimeException("Target Category Entity Validation Fault"));
			existing.setCategory(category);
		}

		return productRepository.save(existing);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}