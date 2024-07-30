package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryEntity createCategory(CategoryDTO categoryDTO) {
        CategoryEntity newCategory = CategoryEntity
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public CategoryEntity getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryEntity> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity updateCategory(long categoryId,
                                   CategoryDTO categoryDTO) {
        CategoryEntity existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}