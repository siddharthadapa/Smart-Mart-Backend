package com.vcube.smart_mart.service;

import java.util.List;

import com.vcube.smart_mart.entity.Product;

public interface ProductService {

	Product addProduct(Product product);

	List<Product> getAllProducts();

	Product getProductById(Long id);

	Product updateProduct(Long id, Product product);

	void deleteProduct(Long id);

}
