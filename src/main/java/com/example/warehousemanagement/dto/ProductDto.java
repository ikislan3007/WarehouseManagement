package com.example.warehousemanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto extends BaseDto {

    @Size(max = 50)
    @NotBlank
    String productName;

    @Size(max = 2000)
    String description;

    String uploadFile;

    @NotNull
    double purchasePrice;

    @NotNull
    double sellingPrice;

    @NotNull
    int quantity;

    @NotBlank
    String category;

    @NotBlank
    String code;
}
