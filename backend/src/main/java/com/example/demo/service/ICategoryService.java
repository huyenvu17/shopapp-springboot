package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    CategoryEntity createCategory(CategoryDTO category);
    CategoryEntity getCategoryById(long id);
    List<CategoryEntity> getAllCategories();
    CategoryEntity updateCategory(long categoryId, CategoryDTO category);
    void deleteCategory(long id);
}
