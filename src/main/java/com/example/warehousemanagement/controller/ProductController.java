package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/warehouse")
public interface ProductController extends CrudController<ProductDto, ProductEntity> {


}
