package com.example.warehousemanagement.controller.impl;

import com.example.warehousemanagement.controller.ProductController;
import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import com.example.warehousemanagement.service.CrudService;
import com.example.warehousemanagement.service.ProductService;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductControllerImpl implements ProductController {

    final ProductService productService;

    @Override
    public CrudService<ProductDto, ProductEntity> crudService() {
        return productService;
    }

    @Override
    public ResponseEntity<Page<ProductDto>> getByCode(String code, Pageable pageable) {
        return ResponseEntity.ok().body(productService.getByCode(pageable, code));
    }

    @Override
    public ResponseEntity<Page<ProductDto>> getByCategoryAndProductName(Pageable pageable, Category category, Optional<String> productName) {
        if (productName.isPresent()) {
            return ResponseEntity.ok().body(productService.getByCategoryAndProductName(pageable, category, productName));
        } else {
            return ResponseEntity.ok().body(productService.getByCategory(pageable, category));
        }
    }
}
