package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

    Page<ProductEntity> getByCode(Pageable pageable, String code);

    Page<ProductEntity> getByCategory(Pageable pageable, Category category);

    Page<ProductEntity> findAllByCategoryAndAndProductName(Pageable pageable,
        @Param("category") Category category,
        @Param("productName") Optional<String> productName);
}
