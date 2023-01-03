package com.example.warehousemanagement.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_entity")

public class ProductEntity extends BaseEntity {

    String productName;
    String description;

    String uploadFile;

    double purchasePrice;
    double sellingPrice;
    int quantity;
    @Enumerated(EnumType.STRING)
    Category category;
    String code;

    public enum Category {
        FOOD,
        OFFICE,
        BUILDING
    }
}


