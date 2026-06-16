package com.vcube.smart_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.smart_mart.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
