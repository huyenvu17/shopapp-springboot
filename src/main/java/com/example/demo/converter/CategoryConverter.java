package com.example.demo.converter;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;


public interface CategoryConverter {
    CategoryDTO convertEntity(CategoryEntity categoryEntity);
    CategoryEntity converts(CategoryDTO categoryDTO);
}
