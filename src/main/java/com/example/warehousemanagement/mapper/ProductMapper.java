package com.example.warehousemanagement.mapper;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper extends ResourceEntityTransformer<ProductDto, ProductEntity> {

    ProductDto transformToResource(ProductEntity entity);

    ProductEntity transformToEntity(ProductDto resource);

}
