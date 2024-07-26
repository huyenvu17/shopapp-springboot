package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
//@Validated
public class CategoryController {
    private final CategoryService categoryService;
    // http://localhost:8085/api/v1/categories?page=1&limit=10
    @GetMapping("")
    public ResponseEntity<List<CategoryEntity>> getCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<CategoryEntity> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategory(@PathVariable Long id) {
        CategoryEntity categoryItem = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryItem);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        categoryService.createCatetory(categoryDTO);
        return ResponseEntity.ok("Insert category successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryEntity updatedCategory = categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("deleted product " + id + "successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
