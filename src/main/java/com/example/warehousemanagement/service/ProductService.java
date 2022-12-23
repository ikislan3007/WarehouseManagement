package com.example.warehousemanagement.service;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends CrudService<ProductDto, ProductEntity> {

    Page<ProductDto> getByCode(Pageable pageable, String code);

    Page<ProductDto> getByCategory(Pageable pageable, Category category);

    Page<ProductDto> getByCategoryAndProductName( Pageable pageable, Category category, Optional<String> productName);

}
