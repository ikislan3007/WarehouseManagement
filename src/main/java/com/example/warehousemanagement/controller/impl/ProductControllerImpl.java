package com.example.warehousemanagement.controller.impl;

import com.example.warehousemanagement.controller.ProductController;
import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import com.example.warehousemanagement.service.CrudService;
import com.example.warehousemanagement.service.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<List<ProductDto>> getByCode(String code) {
        return ResponseEntity.ok().body(productService.getByCode(code));
    }

    @Override
    public ResponseEntity<List<ProductDto>> getByCategoryAndProductName(Category category, Optional<String> productName) {
        if (productName.isPresent()) {
            return ResponseEntity.ok().body(productService.getByCategoryAndProductName(category, productName));
        } else {
            return ResponseEntity.ok().body(productService.getByCategory(category));
        }
    }


    @Override
    public ResponseEntity<ProductDto> save(ProductDto dto, @RequestParam("image") Optional<MultipartFile> file) throws IOException {
        return ResponseEntity.ok().body(productService.save(dto, file));
    }

    @Override
    public ResponseEntity<ProductDto> edit(ProductDto dto, Optional<MultipartFile> file) throws IOException {
        return ResponseEntity.ok().body(productService.edit(dto, file));
    }


}



