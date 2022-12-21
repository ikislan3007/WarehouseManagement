package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

}
