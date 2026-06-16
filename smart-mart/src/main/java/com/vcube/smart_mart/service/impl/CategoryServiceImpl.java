package com.vcube.smart_mart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vcube.smart_mart.entity.Category;
import com.vcube.smart_mart.repository.CategoryRepository;
import com.vcube.smart_mart.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {

		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {

		return categoryRepository.findAll();
	}

}
