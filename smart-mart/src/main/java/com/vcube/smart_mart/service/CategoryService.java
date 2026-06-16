package com.vcube.smart_mart.service;

import java.util.List;

import com.vcube.smart_mart.entity.Category;

public interface CategoryService {

	Category addCategory(Category category);

	List<Category> getAllCategories();

}
