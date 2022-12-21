package com.example.warehousemanagement.service;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends CrudService<ProductDto, ProductEntity> {

    Page<ProductDto> getByCode(Pageable pageable, String code);

}
