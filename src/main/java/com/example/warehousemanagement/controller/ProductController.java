package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/v1/warehouse")
public interface ProductController extends CrudController<ProductDto, ProductEntity> {


    @GetMapping("/code")
    ResponseEntity<Page<ProductDto>> getByCode(@RequestParam String code, Pageable pageable);

    @GetMapping("/category")
    ResponseEntity<Page<ProductDto>> getByCategoryAndProductName(Pageable pageable, @RequestParam Category category, @RequestParam Optional<String> productName);

}
