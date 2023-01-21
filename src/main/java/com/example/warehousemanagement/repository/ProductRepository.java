package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

    List<ProductEntity> getByCode(String code);

    List<ProductEntity> getByCategory(Category category);

    //    @Query(value = "SELECT p FROM ProductEntity  p WHERE p.category = :category AND p.productName  LIKE :productName")
    List<ProductEntity> findAllByCategoryAndProductNameStartsWith(
        @Param("category") Category category,
        @Param("productName") Optional<String> productName);
}
