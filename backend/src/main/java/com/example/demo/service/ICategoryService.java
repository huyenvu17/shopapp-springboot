package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    CategoryEntity createCatetory(CategoryDTO category);
    List<CategoryEntity> getAllCategories();
    CategoryEntity getCategoryById(long id);
    CategoryEntity updateCategory(long categoryId, CategoryDTO category);
    void deleteCategory(long id);
}
