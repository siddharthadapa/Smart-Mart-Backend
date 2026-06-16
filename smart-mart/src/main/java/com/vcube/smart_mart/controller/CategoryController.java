package com.vcube.smart_mart.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.smart_mart.entity.Category;
import com.vcube.smart_mart.service.impl.CategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class CategoryController {

	private final CategoryServiceImpl categoryServiceImpl;

	@PostMapping
	public Category addCategory(@RequestBody Category category) {

		return categoryServiceImpl.addCategory(category);
	}

	@GetMapping
	public List<Category> getAllCategories() {

		return categoryServiceImpl.getAllCategories();
	}
}
