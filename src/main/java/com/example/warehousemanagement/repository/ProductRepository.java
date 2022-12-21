package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

    Page<ProductEntity> getByCode(Pageable pageable, String code);
}
