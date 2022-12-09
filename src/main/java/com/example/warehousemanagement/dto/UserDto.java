package com.example.warehousemanagement.dto;

import com.example.warehousemanagement.base.BaseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends BaseDto {

    String username;

    String password;

    String email;

    String phone;
}
