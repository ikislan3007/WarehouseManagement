package com.example.warehousemanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class ImageEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "type")
    String type;

    @Column(name = "image", nullable = false, length = 100000)
    byte[] image;
}
