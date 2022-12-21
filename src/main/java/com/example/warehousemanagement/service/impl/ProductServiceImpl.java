package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.mapper.ProductMapper;
import com.example.warehousemanagement.mapper.ResourceEntityTransformer;
import com.example.warehousemanagement.repository.BaseRepository;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
}
