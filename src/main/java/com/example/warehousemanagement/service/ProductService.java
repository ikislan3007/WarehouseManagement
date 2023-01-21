package com.example.warehousemanagement.service;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService extends CrudService<ProductDto, ProductEntity> {

    List<ProductDto> getByCode(String code);

    List<ProductDto> getByCategory(Category category);

    List<ProductDto> getByCategoryAndProductName(Category category, Optional<String> productName);

    ProductDto save(ProductDto dto, Optional<MultipartFile> file) throws IOException;

    ProductDto edit(ProductDto dto, Optional<MultipartFile> file) throws IOException;
}
