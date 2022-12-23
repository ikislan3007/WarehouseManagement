package com.example.warehousemanagement.entity;

import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


