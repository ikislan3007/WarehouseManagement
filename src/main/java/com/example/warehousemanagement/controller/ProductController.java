package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/warehouse/product")
@Tag(name = "Product")
public interface ProductController extends CrudController<ProductDto, ProductEntity> {

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    default ResponseEntity<ProductDto> findById(Long id) {
        return CrudController.super.findById(id);
    }

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    default ResponseEntity<List<ProductDto>> findAll() {
        return CrudController.super.findAll();
    }


    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    default ResponseEntity<ProductDto> delete(Long id) {
        return CrudController.super.delete(id);
    }


    @GetMapping("/code")
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<List<ProductDto>> getByCode(@RequestParam String code);

    @GetMapping("/category")
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<List<ProductDto>> getByCategoryAndProductName(@RequestParam Category category, @RequestParam Optional<String> productName);


    @PostMapping(value = "/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ProductDto> save(ProductDto dto, @RequestParam("image") Optional<MultipartFile> file) throws IOException;


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, name = "edit")
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ProductDto> edit(ProductDto dto, @RequestParam("image") Optional<MultipartFile> file) throws IOException;


}

