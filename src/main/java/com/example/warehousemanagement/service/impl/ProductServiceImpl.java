package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import com.example.warehousemanagement.mapper.ProductMapper;
import com.example.warehousemanagement.mapper.ResourceEntityTransformer;
import com.example.warehousemanagement.repository.BaseRepository;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.service.ProductService;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    final ProductRepository repository;
    final ProductMapper mapper;

    @Override
    public BaseRepository<ProductEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ProductDto, ProductEntity> resourceTransformer() {
        return mapper;
    }

    @Override
    public Page<ProductDto> getByCode(Pageable pageable, String code) {
        Page<ProductEntity> products = repository.getByCode(pageable, code);
        return products.map(mapper::transformToResource);
    }

    @Override
    public Page<ProductDto> getByCategory(Pageable pageable, Category category) {
        Page<ProductEntity> products = repository.getByCategory(pageable, category);
        return products.map(mapper::transformToResource);
    }

    @Override
    public Page<ProductDto> getByCategoryAndProductName(Pageable pageable, Category category, Optional<String> productName) {

        Page<ProductEntity> products = repository.findAllByCategoryAndAndProductName(pageable, category, productName);

        return products.map(mapper::transformToResource);

    }
}
