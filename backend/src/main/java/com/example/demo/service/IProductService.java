package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductImageDTO;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.ProductImageEntity;
import com.example.demo.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    ProductEntity createProduct(ProductDTO productDTO) throws Exception;
    ProductEntity getProductById(long id) throws Exception;
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);
    ProductEntity updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImageEntity createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;
}
